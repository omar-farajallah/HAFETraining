package com.carnival.platform.servlets;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.oak.commons.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.constants.ImageSize;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.commons.AbstractImageServlet;
import com.day.cq.wcm.foundation.AdaptiveImageHelper;
import com.day.cq.wcm.foundation.Image;
import com.day.image.Layer;

/**
 * Renders the image in a variety of dimensions and qualities. Quality is
 * configurable and it must be a double value between 0.0 and 1.0. Accepts only
 * pre-configured dimensions
 *
 *
 */
@Component(immediate = true, metatype = true, label = "Image Rendition Servlet", description = "Renders the image in a variety of dimensions and qualities")
@Service(value = Servlet.class)
@Properties({ @Property(name = "sling.servlet.resourceTypes", value = { "dam:Asset", NameConstants.NT_PAGE }, propertyPrivate = true),
        @Property(name = "sling.servlet.selectors", value = { "image" }, propertyPrivate = true),
        @Property(name = "sling.servlet.extensions", value = { "jpg", "jpeg", "eps", "png", "gif" }, propertyPrivate = true) })
public class ImageRenditionsServlet extends AbstractImageServlet {

    /** serialVersionUID. */
    private static final long serialVersionUID = -8367181169853302686L;

    /**
     * LOGGER - For LibraryTag.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRenditionsServlet.class);

    /** The Constant PROPERTY_IMAGE_QUALITY. */
    @Property(label = "Default Image Quality", description = "Quality must be a double between 0.0 and 1.0", doubleValue = 0.82)
    private static final String DEFAULT_IMAGE_QUALITY = "default.image.quality";

    /** The Constant HIGH_IMAGE_QUALITY. */
    @Property(label = "High Image Quality", description = "Quality must be a double between 0.0 and 1.0", doubleValue = 0.6)
    private static final String HIGH_IMAGE_QUALITY = "high.image.quality";

    /** The Constant MEDIUM_IMAGE_QUALITY. */
    @Property(label = "Medium Image Quality", description = "Quality must be a double between 0.0 and 1.0", doubleValue = 0.45)
    private static final String MEDIUM_IMAGE_QUALITY = "medium.image.quality";

    /** The Constant LOW_IMAGE_QUALITY. */
    @Property(label = "Low Image Quality", description = "Quality must be a double between 0.0 and 1.0", doubleValue = 0.3)
    private static final String LOW_IMAGE_QUALITY = "low.image.quality";

    /** The default image quality from osgi config. */
    private double defaultImageQuality;

    /** The high image quality from osgi config. */
    private double highImageQuality;

    /** The medium image quality from osgi config. */
    private double mediumImageQuality;

    /** The low image quality from osgi config. */
    private double lowImageQuality;

    /** The supported dimensions. */
    private Set<Dimension> supportedDimensions = new HashSet<>();

    /**
     * Method called on activation of the servlet. Reads the configured quality
     * and dimensions and holds it in member fields.
     *
     * @param componentContext
     *            the component context
     */
    protected void activate(ComponentContext componentContext) {
        Dictionary<?, ?> properties = componentContext.getProperties();

        setImageQualityFromOsgiConfig(properties);
        ImageSize[] imageSizeArray = ImageSize.values();

        for (ImageSize resolution : imageSizeArray) {
            this.supportedDimensions.add(new Dimension(resolution.getRetinaWidth(), resolution.getRetinaHeight()));
            this.supportedDimensions.add(new Dimension(resolution.getNormalWidth(), resolution.getNormalHeight()));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.day.cq.wcm.commons.AbstractImageServlet#createLayer(com.day.cq.wcm
     * .commons.AbstractImageServlet.ImageContext)
     */
    @Override
    protected Layer createLayer(AbstractImageServlet.ImageContext imageContext) throws RepositoryException, IOException {

        SlingHttpServletRequest request = imageContext.request;
        Dimension validDimension = getValidDimension(request);
        if (validDimension == null) {
            LOGGER.error("Invalid width and height selector.");
            return null;
        }

        Resource imgResource = imageContext.resource;
        String ext = request.getRequestPathInfo().getExtension();
        Asset imageResAsset = imgResource.adaptTo(Asset.class);
        if (imageResAsset == null) {
            return null;
        } else {
            String[] selectors = request.getRequestPathInfo().getSelectors();
            String rendName = StringUtils.join(selectors, ".").concat(".").concat(ext);
            Rendition rend = imageResAsset.getRendition(rendName);
            if (rend != null) {
                request.setAttribute(ApplicationConstants.RENDITION, rend);
                return null;
            } else if (StringUtils.equals(getImageType(ext), StringUtils.trimToEmpty(ext))) {
                return null;
            }
        }

        Image image = new Image(imgResource);
        image.setFileReference(request.getRequestPathInfo().getResourcePath());

        AdaptiveImageHelper adaptiveAdaptiveImageComponent = new AdaptiveImageHelper();
        // If width if 0 send the original size image with only the style
        // applied
        if ((int) validDimension.getWidth() == 0) {
            return adaptiveAdaptiveImageComponent.applyStyleDataToImage(image, imageContext.style);
        }
        return adaptiveAdaptiveImageComponent.scaleThisImage(image, (int) validDimension.getWidth(), (int) validDimension.getHeight(),
                imageContext.style);
    }

    /**
     * Validates the request selectors and returns a valid dimension object
     * 
     * @param request
     *            sling request
     * @return a valid dimension object by taking the width and height from
     *         selectors and validating against ImageSize
     */
    private Dimension getValidDimension(SlingHttpServletRequest request) {
        LOGGER.info("Serving image {} {}", request.getRequestURI(), request.getRequestPathInfo().getExtension());
        String[] selectors = request.getRequestPathInfo().getSelectors();
        int width = -1;
        int height = -1;
        if (selectors.length == ApplicationConstants.INT_2) {
            width = 0;
            height = 0;
        } else if (selectors.length == ApplicationConstants.INT_3) {
            width = NumberUtils.toInt(selectors[ApplicationConstants.INT_1], -1);
            height = 0;
        } else if (selectors.length == ApplicationConstants.INT_4) {
            width = NumberUtils.toInt(selectors[ApplicationConstants.INT_1], -1);
            height = NumberUtils.toInt(selectors[ApplicationConstants.INT_2], -1);
        }
        if (width > -1 && height > -1) {
            Dimension dimension = new Dimension(width, height);
            if (supportedDimensions.contains(dimension)) {
                return dimension;
            }
        }
        LOGGER.error("Unsupported dimensions requested: {} x {}.", Integer.valueOf(width), Integer.valueOf(height));
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.day.cq.wcm.commons.AbstractImageServlet#writeLayer(org.apache.sling
     * .api.SlingHttpServletRequest,
     * org.apache.sling.api.SlingHttpServletResponse,
     * com.day.cq.wcm.commons.AbstractImageServlet.ImageContext,
     * com.day.image.Layer)
     */
    @Override
    protected void writeLayer(SlingHttpServletRequest request, SlingHttpServletResponse response, AbstractImageServlet.ImageContext context,
            Layer layer) throws IOException, RepositoryException {

        RequestPathInfo pathInfo = request.getRequestPathInfo();
        String extn = pathInfo.getExtension();
        String requestUrI = request.getRequestURI();
        if (layer == null) {
            if (context.request.getAttribute(ApplicationConstants.RENDITION) != null) {
                LOGGER.info("Serving image rendition from DAM");
                IOUtils.copy(((Rendition) context.request.getAttribute(ApplicationConstants.RENDITION)).getStream(),
                        response.getOutputStream());
            } else if (!StringUtils.equals(getImageType(extn), StringUtils.trimToEmpty(extn))) {
                String error = "Invalid image path requested: " + StringEscapeUtils.escapeHtml3(requestUrI);
                LOGGER.error(error);
                response.sendError(ApplicationConstants.ERROR_404, error);
            } else {
                Asset image = context.resource.adaptTo(Asset.class);
                LOGGER.debug("Serving original image from DAM");
                if (null != image && image.getOriginal() != null && image.getOriginal().getStream() != null) {
                    IOUtils.copy(image.getOriginal().getStream(), response.getOutputStream());
                }
            }
            return;
        }

        String[] selectors = pathInfo.getSelectors();
        String imageQualitySelector = null;
        if (selectors.length == ApplicationConstants.INT_4) {
            imageQualitySelector = selectors[ApplicationConstants.INT_3];
        } else if (selectors.length == ApplicationConstants.INT_3) {
            imageQualitySelector = selectors[ApplicationConstants.INT_2];
        } else if (selectors.length == ApplicationConstants.INT_2) {
            imageQualitySelector = selectors[ApplicationConstants.INT_1];
        }

        writeLayer(request, response, context, layer, getRequestedImageQuality(imageQualitySelector));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.day.cq.wcm.commons.AbstractImageServlet#getImageType(java.lang.
     * String)
     */
    @Override
    protected String getImageType(String ext) {
        String type = super.getImageType(ext);
        if (type == null) {
            type = ext;
        }
        return type;
    }

    /**
     * Gets the requested image quality.
     *
     * @param imageQualitySelector
     *            the image quality selector
     * @return the requested image quality
     */
    private double getRequestedImageQuality(String imageQualitySelector) {
        double imageQualityValue = this.defaultImageQuality;
        if (StringUtils.equalsIgnoreCase(imageQualitySelector, AdaptiveImageHelper.Quality.HIGH.name())) {
            imageQualityValue = this.highImageQuality;
        } else if (StringUtils.equalsIgnoreCase(imageQualitySelector, AdaptiveImageHelper.Quality.MEDIUM.name())) {
            imageQualityValue = this.mediumImageQuality;
        } else if (StringUtils.equalsIgnoreCase(imageQualitySelector, AdaptiveImageHelper.Quality.LOW.name())) {
            imageQualityValue = this.lowImageQuality;
        }
        return imageQualityValue;
    }

    /**
     * Sets the image quality from osgi config.
     *
     * @param properties
     *            the properties
     */
    private void setImageQualityFromOsgiConfig(Dictionary<?, ?> properties) {

        double defaultImgQuality = PropertiesUtil.toDouble(properties.get(DEFAULT_IMAGE_QUALITY),
                AdaptiveImageHelper.Quality.MEDIUM.getQualityValue());
        if ((defaultImgQuality > 1.0D) || (defaultImgQuality < 0.0D)) {
            defaultImgQuality = AdaptiveImageHelper.Quality.MEDIUM.getQualityValue();
        }
        this.defaultImageQuality = defaultImgQuality;

        double highImgQuality = PropertiesUtil.toDouble(properties.get(HIGH_IMAGE_QUALITY), defaultImgQuality);
        if ((highImgQuality > 1.0D) || (highImgQuality < 0.0D)) {
            highImgQuality = defaultImgQuality;
        }
        this.highImageQuality = highImgQuality;

        double mediumImgQuality = PropertiesUtil.toDouble(properties.get(MEDIUM_IMAGE_QUALITY), defaultImgQuality);
        if ((mediumImgQuality > 1.0D) || (mediumImgQuality < 0.0D)) {
            mediumImgQuality = defaultImgQuality;
        }
        this.mediumImageQuality = mediumImgQuality;

        double lowImgQuality = PropertiesUtil.toDouble(properties.get(LOW_IMAGE_QUALITY), defaultImgQuality);
        if ((lowImgQuality > 1.0D) || (lowImgQuality < 0.0D)) {
            lowImgQuality = defaultImgQuality;
        }
        this.lowImageQuality = lowImgQuality;
    }

}

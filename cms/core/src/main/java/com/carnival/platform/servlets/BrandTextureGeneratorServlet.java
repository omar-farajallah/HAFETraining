package com.carnival.platform.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.carnival.platform.utils.PageUtil;
import com.day.cq.commons.jcr.JcrConstants;

/**
 * The Class GridValueGeneratorServlet.
 *
 * @author sbha61 This class is used to retrieve texture resources(assets) with
 *         dynamic brand specific path in the current invoking page. It is used
 *         to populate the drop-down in C006 component
 */

@SlingServlet(resourceTypes = "component/texture")
public class BrandTextureGeneratorServlet extends SlingSafeMethodsServlet {

    @Reference
    private ResourceResolverFactory rrFactory;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4956902639136729218L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandTextureGeneratorServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException {
        LOGGER.info("brand texture genarator servlet starts >>");

        String texturePath = "/etc/designs/carnival/{brand}/images/textures";
        final List<Resource> resourceList = new ArrayList<>();
        ResourceResolver resolver = request.getResourceResolver();

        String brand = PageUtil.getBrandNameForCurrentPage(request.getRequestURI(), texturePath);
        texturePath = texturePath.replace("{brand}", brand);
        final Map<String, String> textureData = populateKeyValue(resolver, texturePath);
        ValueMap valueMap = new ValueMapDecorator(new HashMap<String, Object>());
        valueMap.put("text", "Select");
        valueMap.put("value", "");
        resourceList.add(new ValueMapResource(resolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, valueMap));
        for (final Entry<String, String> data : textureData.entrySet()) {
            valueMap = new ValueMapDecorator(new HashMap<String, Object>());
            valueMap.put("text", data.getKey());
            valueMap.put("value", data.getValue());
            resourceList.add(new ValueMapResource(resolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, valueMap));
        }
        DataSource dataSoruce = new SimpleDataSource(resourceList.iterator());
        request.setAttribute(DataSource.class.getName(), dataSoruce);
        LOGGER.info("<<  brand texture genarator servlet ends");

    }

    /**
     * Populate resource.
     *
     * @param resolver
     *            the resolver
     * @param texturePath
     *            the texture path with brand name
     * @return the map
     */
    Map<String, String> populateKeyValue(ResourceResolver resolver, String texturePath) {
        Map<String, String> textureKeyValue = new HashMap<>();
        Resource resource = resolver.getResource(texturePath);
        if (null != resource) {

            Iterator<Resource> childResources = resource.listChildren();

            while (childResources.hasNext()) {
                Resource child = childResources.next();
                textureKeyValue.put(child.getName(), child.getPath());
            }

        }
        return textureKeyValue;
    }
}

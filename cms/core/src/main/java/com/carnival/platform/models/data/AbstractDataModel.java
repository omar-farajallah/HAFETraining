package com.carnival.platform.models.data;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.AbstractSlingModel;
import com.carnival.platform.models.BaseJson;
import com.carnival.platform.services.SlingModelService;
import com.carnival.platform.services.impl.DefaultDataServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class is used to generate the base structure json of the component.
 * 
 * @author vbonth
 *
 */
public abstract class AbstractDataModel extends AbstractSlingModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3470418868210227380L;

    /** The default data service impl. */
    @Inject
    private transient DefaultDataServiceImpl defaultDataServiceImpl;

    /** The resource. */
    @Self
    private transient Resource resource;

    /** The child components. */
    @JsonInclude(Include.NON_EMPTY)
    private BaseJson[] childComponents;

    /**
     * Gets the child components.
     *
     * @return the childComponents
     */
    public BaseJson[] getChildComponents() {
        return childComponents;
    }

    /**
     * Sets the child components.
     *
     * @param childComponents
     *            the new child components
     */
    public void setChildComponents(BaseJson[] childComponents) {
        this.childComponents = childComponents;
    }

    /**
     * Get React Component name.
     *
     * @return the data service
     */
    @Override
    protected SlingModelService getSlingModelService() {
        return defaultDataServiceImpl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.AbstractSlingModel#getResource()
     */
    @Override
    public final Resource getResource() {
        return resource;
    }

    /**
     * Shorten the given URL if it is internal AEM URL also append
     * <code> ApplicationConstants.HTML_EXTN </code> otherwise return same URL
     *
     * @param url
     *            the url
     * @return the string
     */
    protected String shortenUrl(String url) {
        String updatedUrl = url;
        if (StringUtils.startsWith(url, ApplicationConstants.BASE_PATH) && (!StringUtils.contains(url, ApplicationConstants.DAM_PATH))) {
            String shortUrl = getResourceResolver().map(url);
            updatedUrl = StringUtils.appendIfMissing(shortUrl, ApplicationConstants.HTML_EXTN);
        }
        return updatedUrl;
    }

    /**
     * Update request info. This default implementation does not do anything.
     * Child classes may override this to implement request dependent
     * manipulation of the data model.
     *
     * @param request
     *            the request
     */
    public void updateRequestInfo(SlingHttpServletRequest request) {
        // Empty default implementation
    }

}

/**
 * 
 */
package com.carnival.platform.models;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.services.SlingModelService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class to be extended by the component and data model classes.
 * 
 * @author ssahu6
 *
 */
public abstract class AbstractSlingModel implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8505009030786957721L;

    /**
     * Get React Component name.
     *
     * @return the component name
     */
    @JsonIgnore
    public abstract String getComponentName();

    /**
     * Implementation should use this with @PostConstruct annotation.
     *
     * @return the resource
     */
    @PostConstruct
    private void init() {
        getSlingModelService().updateModel(this);
    }

    /**
     * Gets the sling model service.
     *
     * @return the sling model service
     */
    protected abstract SlingModelService getSlingModelService();

    /**
     * Gets the resource.
     *
     * @return the resource
     */
    public abstract Resource getResource();

    /**
     * Gets the resource resolver.
     *
     * @return the resource resolver
     */
    @JsonIgnore
    public final ResourceResolver getResourceResolver() {
        return getResource().getResourceResolver();
    }

    /**
     * Gets the brand page name.
     *
     * @return the brand name
     */

    @JsonIgnore
    public String getBrandName() {
        return getAbsoluteParentName(ApplicationConstants.INT_1);
    }

    /**
     * Gets the brand page name.
     *
     * @return the brand name
     */

    @JsonIgnore
    public String getLocaleName() {
        return getAbsoluteParentName(ApplicationConstants.INT_2);
    }

    /**
     * returns the absolute parent based on input page level.
     *
     * @param level
     *            the level
     * @return page name
     */
    String getAbsoluteParentName(int level) {
        String pageName = StringUtils.EMPTY;
        PageManager pageManager = getResourceResolver().adaptTo(PageManager.class);
        // get the absolute parent level to know the 'brand' and 'locale'
        if (null != pageManager) {
            Page page = pageManager.getContainingPage(getResource().getPath());
            if (null != page) {
                Page pranetPage = page.getAbsoluteParent(level);
                if (null != pranetPage) {
                    pageName = pranetPage.getName();
                }
            }
        }
        return pageName;
    }

}

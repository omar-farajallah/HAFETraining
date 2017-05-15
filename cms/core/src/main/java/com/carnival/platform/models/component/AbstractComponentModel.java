package com.carnival.platform.models.component;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.carnival.platform.models.AbstractSlingModel;
import com.carnival.platform.models.data.AbstractDataModel;
import com.carnival.platform.services.SlingModelService;
import com.carnival.platform.services.impl.DefaultComponentServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class provides abstraction for component sling models.
 * 
 * @author vbonth
 *
 */
public abstract class AbstractComponentModel extends AbstractSlingModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3470418868210227380L;

    /** The json. */
    private transient String json;

    /** The default component service. */
    @Inject
    private transient DefaultComponentServiceImpl defaultJsonServiceImpl;

    /** The id. */
    private final transient String id = String.valueOf(System.currentTimeMillis());

    /** The slingRequest. */
    @Self
    private transient SlingHttpServletRequest slingRequest;

    /**
     * Get React Component data.
     *
     * @return the component data
     */
    @JsonIgnore
    public abstract AbstractDataModel[] getComponentData();

    /**
     * Method to get server side rendering HTML DOM Element.
     *
     * @return the html
     */
    @JsonIgnore
    public final String getHtml() {
        return defaultJsonServiceImpl.getComponentHtml(this);
    }

    /**
     * Method to get JSON Data for client side rendering.
     *
     * @return the json
     */
    public final String getJson() {
        return json;
    }

    /**
     * Method to set JSON Data for client side rendering.
     *
     * @param json
     *            the new json
     */
    public final void setJson(String json) {
        this.json = json;
    }

    /**
     * Check if given component is static or dynamic.
     *
     * @return true, if is dynamic
     */
    @JsonIgnore
    public final boolean isStatic() {
        return defaultJsonServiceImpl.isComponentStatic(getComponentName());
    }

    /**
     * Return unique ID of the component.
     *
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.models.AbstractSlingModel#getSlingModelService()
     */
    @Override
    protected SlingModelService getSlingModelService() {
        return defaultJsonServiceImpl;
    }

    /**
     * Return slingRequest.
     *
     * @return slingRequest
     */
    public final SlingHttpServletRequest getRequest() {
        return slingRequest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.AbstractSlingModel#getResource()
     */
    @Override
    public final Resource getResource() {
        return slingRequest.getResource();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.AbstractSlingModel#getComponentName()
     */
    @Override
    public final String getComponentName() {
        return (getComponentData()[0]).getComponentName();
    }
}

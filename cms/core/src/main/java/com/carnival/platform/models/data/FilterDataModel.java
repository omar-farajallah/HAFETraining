package com.carnival.platform.models.data;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

/**
 * FilterDataModel class.
 *
 * @author vaddur
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FilterDataModel extends AbstractDataModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6715767817119160844L;

    /** The filter. */
    @Inject
    private String filter;

    /**
     * Gets the filter.
     *
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.AbstractSlingModel#getComponentName()
     */
    @Override
    public String getComponentName() {
        return StringUtils.EMPTY;
    }

}

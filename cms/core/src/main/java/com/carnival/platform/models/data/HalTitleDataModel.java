package com.carnival.platform.models.data;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HalTitleDataModel extends AbstractDataModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1602768992394637344L;

    /** The Constant COMPONENT_NAME. */
    public static final String COMPONENT_NAME = "halTitle";

    /** H1 Title. */
    @Inject
    @Required
    private String title;

    /**
     * Gets the title.
     *
     * @return the h1 title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the component name.
     *
     * @return the component name
     */
    @Override
    public String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    public String toString() {
        return "TitleDataModel [title=" + getTitle() + "]";
    }

}

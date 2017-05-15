package com.carnival.platform.models.data;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

/**
 * The Class TitleDataModel.
 *
 * @author spati9
 * 
 *         Model class Title component
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TitleDataModel extends AbstractDataModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1602768992394637344L;

    /** The Constant COMPONENT_NAME. */
    public static final String COMPONENT_NAME = "titleH1";

    /** H1 Title. */
    @Inject
    @Required
    private String title;

    /** Description. */
    @Inject
    private String description;

    /** decorator (icon/text). */
    @Inject
    private String dividerType;

    /** text for title decorator. */
    @Inject
    @Default(values = StringUtils.EMPTY)
    private String dividerText;

    /** selection is campaign header required or not. */
    @Inject
    private String isCampaignHeaderRequired;

    /** text for campaign header label. */
    @Inject
    private String campaignHeaderText;

    /**
     * Gets the title.
     *
     * @return the h1 title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the description.
     *
     * @return the title description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the divider type.
     *
     * @return the type of divider(icon/text)
     */
    public String getDividerType() {
        return dividerType;
    }

    /**
     * Gets the divider text.
     *
     * @return the text for dividertype as text
     */
    public String getDividerText() {
        return dividerText;
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

    /**
     * Gets the campaign header is required.
     *
     * @return the campaign header required value
     */
    public String getIsCampaignHeaderRequired() {
        return isCampaignHeaderRequired;
    }

    /**
     * Gets the campaign header label.
     *
     * @return the campaign header label
     */
    public String getCampaignHeaderText() {
        return campaignHeaderText;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TitleDataModel [title=" + getTitle() + ", description=" + getDescription() + ", dividerType=" + getDividerType()
                + ", dividerText=" + getDividerText() + ", isCampaignHeaderRequired=" + getIsCampaignHeaderRequired()
                + ", campaignHeaderText=" + getCampaignHeaderText() + "]";
    }

}

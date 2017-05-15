package com.carnival.platform.models.data;

/**
 * The Class FilterDataModel.
 * 
 * @author gnatas
 */
public class FilterDetailsModel {

    /** The filter title. */
    private String filterTitle;

    /** The filter sub header. */
    private String filterSubHeader;

    /** The filter API key. */
    private String filterAPIKey;

    /**
     * Gets the filter title.
     *
     * @return the filterTitle
     */
    public String getFilterTitle() {
        return filterTitle;
    }

    /**
     * Sets the filter title.
     *
     * @param filterTitle
     *            the filterTitle to set
     */
    public void setFilterTitle(String filterTitle) {
        this.filterTitle = filterTitle;
    }

    /**
     * Gets the filter sub header.
     *
     * @return the filterSubHeader
     */
    public String getFilterSubHeader() {
        return filterSubHeader;
    }

    /**
     * Sets the filter sub header.
     *
     * @param filterSubHeader
     *            the filterSubHeader to set
     */
    public void setFilterSubHeader(String filterSubHeader) {
        this.filterSubHeader = filterSubHeader;
    }

    /**
     * Gets the filter API key.
     *
     * @return the filterAPIKey
     */
    public String getFilterAPIKey() {
        return filterAPIKey;
    }

    /**
     * Sets the filter API key.
     *
     * @param filterAPIKey
     *            the filterAPIKey to set
     */
    public void setFilterAPIKey(String filterAPIKey) {
        this.filterAPIKey = filterAPIKey;
    }

}

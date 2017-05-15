package com.carnival.platform.services;

import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;

/**
 * The Interface LabelConfiguration Service.
 *
 * @author bmuthu
 */
public interface LabelConfigurationService {

    /**
     * Provide the label values.
     *
     * @param brand
     *            the brand key
     * @param locale
     *            the locale
     * @param labelKeys
     *            the label keys
     * @return values for the keys passed, if no keys are passed all the
     *         properties will be returned
     */
    Map<String, String> getLabelValues(String brand, String locale, String... labelKeys);

    /**
     * Provide a single label value.
     * 
     * @param brand
     *            the brand key
     * @param locale
     *            the locale
     * @param labelKey
     *            key to fetch the label value
     * @return values label key
     */
    String getLabelValue(String brand, String locale, String labelKey);

    /**
     * Provide all the label for specific component.
     *
     * @param brand
     *            the brand
     * @param locale
     *            the locale
     * @param componentName
     *            labels are stored with the component name
     * @return Map of values as label:key
     */
    Resource getLabelValuesByComponentResource(String brand, String locale, String componentName);

    /**
     * Gets the property values.
     *
     * @param componentName
     *            the component name
     * @return the property values
     */
    List<String> getPropertyValues(String componentName);

    /**
     * Gets the brand label path.
     *
     * @param brand
     *            the brand
     * @param locale
     *            the locale
     * @return the brand label path
     */
    String getBrandLabelPath(String brand, String locale);

}

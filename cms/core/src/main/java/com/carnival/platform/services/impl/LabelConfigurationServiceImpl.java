package com.carnival.platform.services.impl;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;

import com.carnival.platform.services.LabelConfigurationService;
import com.carnival.platform.utils.JCRUtils;

/**
 * Configuration class which provides the display text for labels. It will be
 * leveraged to work for multi-languages in future.
 * 
 * @author bmuthu
 *
 */
@Component(immediate = true, metatype = true, label = "Carnival - Common Label Service", description = "Configuration to maintain common label page path")
@Service(value = LabelConfigurationService.class)
public class LabelConfigurationServiceImpl extends AbstractAdminResouceService implements LabelConfigurationService {

    /** The propeties. */
    private Dictionary<?, ?> propeties;

    /** The label page path pattern. */
    private String labelPagePathPattern;

    /** The Constant SITEPATH_DOMAIN_MAPPINGS. */
    @Property(label = "Site Specific Common Label Page Path", value = "", description = "Specify the path as '/content/{brand}/{locale}' and {brand} and {locale} will be replaced dynamically.")
    private static final String COMMON_LABEL_PAGE_PATH = "commonLabelPagePath";

    /** The Constant SEARCH_COMPONENT_NAME. */
    @Property(unbounded = PropertyUnbounded.ARRAY, cardinality = 10, label = "Required Key Value Names", value = { "sailToTitle",
            "departFromTitle", "datesTitle", "durationTitle" })
    private static final String SEARCH_COMPONENT_NAME = "search";

    /**
     * Gets the label values.
     *
     * @param brand
     *            the brand
     * @param locale
     *            the locale
     * @param labelKeys
     *            the label keys
     * @return the label values
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.services.LabelConfigurationService#getLabelValues(
     * java.lang.String, java.lang.String, java.lang.String[])
     */
    @Override
    public Map<String, String> getLabelValues(String brand, String locale, String... labelKeys) {
        String labelConfigPath = getBrandLabelPath(brand, locale);
        Resource labelContentResource = getAdminContentResource(labelConfigPath);
        Map<String, String> labelMap = new HashMap<>();
        populateLabelMap(labelContentResource, labelMap, labelKeys);
        return labelMap;
    }

    /**
     * Gets the label value.
     *
     * @param brand
     *            the brand
     * @param locale
     *            the locale
     * @param labelKey
     *            the label key
     * @return the label value
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.services.LabelConfigurationService#getLabelValue(
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String getLabelValue(String brand, String locale, String labelKey) {
        return getLabelValues(brand, locale, new String[] { labelKey }).get(labelKey);
    }

    /**
     * Populate label map.
     *
     * @param labelContentResource
     *            the label content resource
     * @param labelMap
     *            the label map
     * @param labelKeys
     *            the label keys
     */
    private void populateLabelMap(Resource labelContentResource, Map<String, String> labelMap, String... labelKeys) {
        if (labelContentResource != null) {

            ValueMap labelModel = labelContentResource.getValueMap();
            Set<Entry<String, Object>> entrySet = labelModel.entrySet();
            for (Entry<String, Object> entry : entrySet) {
                if (labelKeys == null || labelKeys.length == 0 || ArrayUtils.contains(labelKeys, entry.getKey())) {
                    labelMap.put(entry.getKey(), entry.getValue().toString());
                }
            }
            labelContentResource.getChildren().forEach(child -> populateLabelMap(child, labelMap, labelKeys));
        }
    }

    /**
     * Gets the label values by component name.
     *
     * @param brand
     *            the brand
     * @param locale
     *            the locale
     * @param componentName
     *            the component name
     * @return the label values by component name
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.services.LabelConfigurationService#
     * getLabelValuesByComponentName(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public Resource getLabelValuesByComponentResource(String brand, String locale, String componentName) {
        String labelConfigPath = getBrandLabelPath(brand, locale);
        Resource labelContentResource = getAdminContentResource(labelConfigPath);
        if (null != labelContentResource) {
            return JCRUtils.getResourceByComponentName(labelContentResource, componentName);
        }
        return labelContentResource;
    }

    /**
     * Do activate.
     *
     * @param context
     *            the context
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.services.impl.AbstractAdminResouceService#
     * doActivate(org.osgi.service.component.ComponentContext)
     */
    @Override
    protected void doActivate(ComponentContext context) {
        propeties = context.getProperties();
        labelPagePathPattern = PropertiesUtil.toString(propeties.get(COMMON_LABEL_PAGE_PATH), "");
    }

    /**
     * Gets the property values.
     *
     * @param componentName
     *            the component name
     * @return the property values
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.services.LabelConfigurationService#
     * getPropertyValues(java.lang.String)
     */
    @Override
    public List<String> getPropertyValues(String componentName) {
        String[] values = PropertiesUtil.toStringArray(propeties.get(componentName));
        return Arrays.asList(values);
    }

    /**
     * Gets the brand label path.
     *
     * @param brand
     *            the brand
     * @param locale
     *            the locale
     * @return the brand label path
     */
    @Override
    public String getBrandLabelPath(String brand, String locale) {
        String labelPath = labelPagePathPattern;
        labelPath = labelPath.replace("{brand}", brand).replace("{locale}", locale);
        return labelPath;
    }
}

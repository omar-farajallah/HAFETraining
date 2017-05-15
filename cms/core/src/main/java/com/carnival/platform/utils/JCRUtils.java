package com.carnival.platform.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.models.data.FilterDetailsModel;

/**
 * The Class PageUtils.
 * 
 * @author gnatas
 * 
 */
public final class JCRUtils {

    /** The Constant LOG. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JCRUtils.class);

    /**
     * Instantiates a new page util.
     */
    private JCRUtils() {
        // Hide default constructor
    }

    /**
     * Gets the resource by component name.
     *
     * @param resource
     *            the resource
     * @param componentName
     *            the component name
     * @return the resource by component name
     */
    public static Resource getResourceByComponentName(Resource resource, String componentName) {
        LOGGER.debug("component resource - {}", resource);
        Iterator<Resource> resourceItr = resource.listChildren();
        while (resourceItr.hasNext()) {
            Resource childResource = resourceItr.next();
            if (null != childResource) {
                if (componentName.equals(childResource.getName())) {
                    return childResource;
                }
                resourceItr = childResource.listChildren();
            }
        }
        return resource;
    }

    /**
     * Sets the primary filter data.
     *
     * @param filters
     *            the filter name
     * @param resource
     *            the resource
     * @return the list
     */
    public static List<FilterDetailsModel> fetchFilterDetails(String[] filters, Resource resource) {

        List<FilterDetailsModel> filterDataModelList = new ArrayList<>();
        if (null != resource && null != filters) {
            for (String filter : filters) {
                Iterator<Resource> resourceItr = resource.listChildren();
                FilterDetailsModel filterModel = getFilterModelData(resourceItr, filter);
                if (null != filterModel && null != filterModel.getFilterAPIKey()) {
                    filterDataModelList.add(filterModel);
                }

            }
        }
        return filterDataModelList;
    }

    /**
     * Gets the filter model data.
     *
     * @param resourceItr
     *            the resource itr
     * @param filter
     *            the filter
     * @return the filter model data
     */
    private static FilterDetailsModel getFilterModelData(Iterator<Resource> resourceItr, String filter) {
        FilterDetailsModel filterDataModel = new FilterDetailsModel();
        while (resourceItr.hasNext()) {
            Resource childResource = resourceItr.next();
            ValueMap valueMap = childResource.getValueMap();
            if (StringUtils.equals(filter, (String) valueMap.get("filterAPIKey"))) {
                filterDataModel.setFilterAPIKey((String) valueMap.get("filterAPIKey"));
                filterDataModel.setFilterSubHeader((String) valueMap.get("filterSubHeader"));
                filterDataModel.setFilterTitle((String) valueMap.get("filterTitle"));
                return filterDataModel;
            }

        }
        return null;

    }

}

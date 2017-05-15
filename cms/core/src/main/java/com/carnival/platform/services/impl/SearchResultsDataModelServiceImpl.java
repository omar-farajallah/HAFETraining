package com.carnival.platform.services.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;

import com.carnival.platform.models.data.SearchResultsDataModel;
import com.carnival.platform.services.LabelConfigurationService;
import com.carnival.platform.utils.JCRUtils;

/**
 * The Class SearchHeaderDataModelServiceImpl.
 * 
 * @author gnatas
 */
@Component
@Service(value = SearchResultsDataModelServiceImpl.class)
public class SearchResultsDataModelServiceImpl extends AbstractDataService<SearchResultsDataModel> {

    /** The label configuration service. */
    @Reference
    private transient LabelConfigurationService labelConfigurationService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.services.impl.AbstractDataService#updateSlingModel(
     * com.carnival.platform.models.data.AbstractDataModel)
     */
    @Override
    protected void updateSlingModel(SearchResultsDataModel slingModel) {
        Resource searchLabelResource = labelConfigurationService.getLabelValuesByComponentResource(slingModel.getBrandName(),
                slingModel.getLocaleName(), "search");
        // value map setting for the search related labels
        slingModel.setLabels(searchLabelResource.getValueMap());

        Resource searchchildResource = searchLabelResource.getChild("searchfilter");
        slingModel.setFilters(JCRUtils.fetchFilterDetails(slingModel.getPrimaryFilters(), searchchildResource));
        slingModel.setMoreFilters(JCRUtils.fetchFilterDetails(slingModel.getAdditionalFilters(), searchchildResource));

        searchchildResource = searchLabelResource.getChild("subfilter");
        slingModel.setSortByFilter(JCRUtils.fetchFilterDetails(slingModel.getSubFilters(), searchchildResource));

    }

}

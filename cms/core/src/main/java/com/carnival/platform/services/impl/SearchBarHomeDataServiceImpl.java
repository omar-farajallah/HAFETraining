package com.carnival.platform.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.models.data.FilterDataModel;
import com.carnival.platform.models.data.SearchBarHomeDataModel;
import com.carnival.platform.services.LabelConfigurationService;
import com.carnival.platform.utils.JCRUtils;

/**
 * The Class SearchBarHomeDataServiceImpl.
 * 
 * @author gnatas
 */
@Component
@Service(SearchBarHomeDataServiceImpl.class)
public class SearchBarHomeDataServiceImpl extends AbstractDataService<SearchBarHomeDataModel> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchBarHomeDataServiceImpl.class);

	/** The label configuration service. */
	@Reference
	private LabelConfigurationService labelConfigurationService;

	private static final String[] LABEL_KEYS = { "closeBtnLabel", "errorMsg", "title", "paginationLabel",
			"searchCtaLabel", "resultsLabel", "showLessLabel", "showMoreLabel" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.carnival.platform.services.impl.AbstractDataService#updateSlingModel(
	 * com.carnival.platform.models.data.AbstractDataModel)
	 */
	@Override
	protected void updateSlingModel(SearchBarHomeDataModel slingModel) {
		LOGGER.debug("update sling model method - {}", slingModel);

		Resource searchResource = labelConfigurationService.getLabelValuesByComponentResource(slingModel.getBrandName(),
				slingModel.getLocaleName(), "search");
		Resource searchFilterChildResource = searchResource.getChild("searchfilter");
		List<FilterDataModel> filterModelList = slingModel.getFilter();
		List<String> filterList = new ArrayList<>();

		filterModelList.forEach(filterModel -> filterList.add(filterModel.getFilter()));
		slingModel.setFilters(JCRUtils.fetchFilterDetails(filterList.toArray(new String[filterList.size()]),
				searchFilterChildResource));

		ValueMap valueMap = searchResource.getValueMap();
		Map<String, String> mapObj = new HashMap<>();

		for (String key : LABEL_KEYS) {
			mapObj.put(key, StringUtils.defaultString((String) valueMap.get(key), StringUtils.EMPTY));
		}

		// setting all the search label required for the component
		slingModel.setLabels(mapObj);

	}

}

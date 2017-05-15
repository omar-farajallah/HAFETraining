package com.carnival.platform.services.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.FilterDataModel;
import com.carnival.platform.models.data.SearchBarHomeDataModel;
import com.day.cq.wcm.api.PageManager;

import junitx.util.PrivateAccessor;

@RunWith(MockitoJUnitRunner.class)
public class SearchBarHomeDataServiceImplTest extends AbstractServiceTest {

    @Mock
    private SearchBarHomeDataModel searchBarHomeDataModel;

    @InjectMocks
    private SearchBarHomeDataServiceImpl searchBarHomeDataServiceImpl;

    @Before
    public void setUp() throws NoSuchFieldException {
        super.setUp();
        
        FilterDataModel filterDataModel = new FilterDataModel();
        List<FilterDataModel> filterData = new ArrayList<>();
        filterData.add(filterDataModel);
        
        PrivateAccessor.setField(filterDataModel, "filter", "landsea");
        PrivateAccessor.setField(filterDataModel, "filter", "portsOfCall");
        
        searchBarHomeDataModel = new SearchBarHomeDataModel();
        PrivateAccessor.setField(searchBarHomeDataModel, "searchTargetUrl", "hal/en.html");
        
        PrivateAccessor.setField(searchBarHomeDataModel, "filter", filterData);
        PrivateAccessor.setField(searchBarHomeDataModel, "resource", resource);

        when(searchBarHomeDataModel.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageMang);
        when(resource.getPath()).thenReturn("/content/carnival/hal/en/test#1");
        when(pageMang.getContainingPage("/content/carnival/hal/en/test#1")).thenReturn(page);

        when(searchBarHomeDataModel.getLocaleName()).thenReturn("en");
        when(searchBarHomeDataModel.getBrandName()).thenReturn("platform");
        when(resource.getValueMap()).thenReturn(valueMap);
        when(resource.getChild("searchfilter")).thenReturn(resource);
        List<Resource> resourceList = new ArrayList<>();
        when(resource.listChildren()).thenReturn(resourceList.iterator());
    }

    @Override
    public void testUpdateSlingModel() {
        final String expectedString = "SearchBarHomeDataModel [searchTargetUrl=hal/en.html, filters=[], labels={paginationLabel=, closeBtnLabel=, resultsLabel=, showMoreLabel=, title=, showLessLabel=, searchCtaLabel=, errorMsg=}]";
        when(labelConfigurationService.getLabelValuesByComponentResource("platform", "en", "search")).thenReturn(resource);
        searchBarHomeDataServiceImpl.updateSlingModel(searchBarHomeDataModel);
        assertTrue(expectedString.equals(searchBarHomeDataModel.toString()));
    }

}

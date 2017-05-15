package com.carnival.platform.services.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.FilterDetailsModel;
import com.carnival.platform.models.data.SearchResultsDataModel;
import com.day.cq.wcm.api.PageManager;

import junitx.util.PrivateAccessor;

/**
 * The Class SearchHeaderDataModelServiceImpl.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchResultsDataModelServiceImplTest extends AbstractServiceTest {

    @Mock
    private SearchResultsDataModel searchHeaderDataModel;

    @InjectMocks
    private SearchResultsDataModelServiceImpl searchHeaderDataModelServiceImpl;

    @Mock
    private FilterDetailsModel filterDataModel;

    @Before
    public void setUp() throws NoSuchFieldException {
        searchHeaderDataModel = new SearchResultsDataModel();
        filterDataModel = new FilterDetailsModel();
        List<FilterDetailsModel> filterList = new ArrayList<>();

        PrivateAccessor.setField(filterDataModel, "filterTitle", "title");
        PrivateAccessor.setField(filterDataModel, "filterSubHeader", "sub header");
        PrivateAccessor.setField(filterDataModel, "filterAPIKey", "apiKey");
        filterList.add(filterDataModel);

        PrivateAccessor.setField(searchHeaderDataModel, "logourl", "logourl");
        PrivateAccessor.setField(searchHeaderDataModel, "logoAltText", "logoAltText");
        PrivateAccessor.setField(searchHeaderDataModel, "logoTarget", "logoTarget");
        PrivateAccessor.setField(searchHeaderDataModel, "noOfResults", "4");
        PrivateAccessor.setField(searchHeaderDataModel, "labels", valueMap);
        PrivateAccessor.setField(searchHeaderDataModel, "itineraryPageURL", "itineraryPageURL");
        PrivateAccessor.setField(searchHeaderDataModel, "termsAndConditionURL", "termsAndConditionURL");
        PrivateAccessor.setField(searchHeaderDataModel, "additionalFilters", new String[] { "landsea", "portsOfCall" });
        PrivateAccessor.setField(searchHeaderDataModel, "primaryFilters", new String[] { "landsea", "ships" });
        PrivateAccessor.setField(searchHeaderDataModel, "subFilters", new String[] { "longest" });
        PrivateAccessor.setField(searchHeaderDataModel, "resource", resource);

        when(searchHeaderDataModel.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageMang);
        when(resource.getPath()).thenReturn("/content/carnival/hal/en/test#1");
        when(pageMang.getContainingPage("/content/carnival/hal/en/test#1")).thenReturn(page);
        when(page.getAbsoluteParent(1)).thenReturn(parentPage1);
        when(page.getAbsoluteParent(2)).thenReturn(parentPage2);

        when(parentPage2.getName()).thenReturn("en");
        when(parentPage1.getName()).thenReturn("platform");

        when(searchHeaderDataModel.getLocaleName()).thenReturn("en");
        when(searchHeaderDataModel.getBrandName()).thenReturn("platform");
        when(resource.getValueMap()).thenReturn(valueMap);
        when(resource.getChild("searchfilter")).thenReturn(resource);

        List<Resource> resourceList = new ArrayList<>();
        when(resource.listChildren()).thenReturn(resourceList.iterator());

    }

    @Test
    public void testUpdateSlingModel() {
        final String expectedFinalString = "SearchResultsDataModel [logourl=logourl, logoAltText=logoAltText, logoTarget=logoTarget, noOfResults=4, itineraryPageURL=itineraryPageURL, termsAndConditionURL=termsAndConditionURL, filters=[], moreFilters=[], sortByFilter=[], labels=valueMap]";
        when(labelConfigurationService.getLabelValuesByComponentResource("platform", "en", "search")).thenReturn(resource);
        searchHeaderDataModelServiceImpl.updateSlingModel(searchHeaderDataModel);
        assertTrue(expectedFinalString.equals(searchHeaderDataModel.toString()));

    }

}

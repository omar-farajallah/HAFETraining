package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.FilterDataModel;
import com.carnival.platform.models.data.SearchBarHomeDataModel;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import junitx.util.PrivateAccessor;

/**
 * The Class SearchBarComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchBarComponentModelTest extends AbstractComponentTest {

    private SearchBarHomeDataModel searchBarDataModel;

    private SearchBarComponentModel searchBarComponentModel;

    /** The page. */
    @Mock
    private Page page;

    /** The parent page 1. */
    @Mock
    private Page parentPage1;

    /** The parent page 2. */
    @Mock
    private Page parentPage2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        searchBarDataModel = new SearchBarHomeDataModel();
        searchBarComponentModel = new SearchBarComponentModel();
        FilterDataModel filterDataModel = new FilterDataModel();
        List<FilterDataModel> filterData = new ArrayList<>();
        filterData.add(filterDataModel);
        
        PrivateAccessor.setField(filterDataModel, "filter", "landsea");
        
        PrivateAccessor.setField(searchBarDataModel, "filter", filterData);
        PrivateAccessor.setField(searchBarDataModel, "searchTargetUrl", "/hal/search.html");
        PrivateAccessor.setField(searchBarDataModel, "resource", resource);
        when(searchBarDataModel.getResourceResolver()).thenReturn(resourceResolver);

        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageMang);
        when(resource.getPath()).thenReturn("/content/carnival/hal/en/test");
        when(pageMang.getContainingPage("/content/carnival/hal/en/test")).thenReturn(page);
        when(resourceResolver.map("")).thenReturn("");

        when(page.getAbsoluteParent(1)).thenReturn(parentPage1);
        when(page.getAbsoluteParent(2)).thenReturn(parentPage2);
        when(parentPage2.getName()).thenReturn("en");
        when(parentPage1.getName()).thenReturn("platform");
        when(searchBarDataModel.getLocaleName()).thenReturn("en");
        when(searchBarDataModel.getBrandName()).thenReturn("platform");
        when(labelConfigurationService.getLabelValuesByComponentResource(anyString(), anyString(), anyString())).thenReturn(resource);

        PrivateAccessor.setField(searchBarComponentModel, "searchBar", searchBarDataModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "SearchBarHomeDataModel [searchTargetUrl=/hal/search.html, filters=null, labels=null]";
        assertTrue(expectedString.equals(searchBarComponentModel.getComponentData()[0].toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() throws NoSuchFieldException {
        assertTrue(SearchBarHomeDataModel.COMPONENT_NAME.equals(searchBarDataModel.getComponentName()));
    }

}

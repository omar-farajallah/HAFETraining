package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.FilterDataModel;
import com.carnival.platform.models.data.InlineSearchBarDataModel;
import com.carnival.platform.models.data.SearchBarHomeDataModel;
import com.carnival.platform.models.data.TitleDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class InlineSearchBarComponentModelTest.
 *
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class InlineSearchBarComponentModelTest extends AbstractComponentTest {

    /** The inline search bar data model. */
    @InjectMocks
    private SearchBarHomeDataModel searchBarDataModel;

    /** The inline search bar component model. */
    @InjectMocks
    private InlineSearchBarComponentModel inlineSearchBarComponentModel;

    /** The title data model. */
    @InjectMocks
    private TitleDataModel titleDataModel;
    
    /** The inline saerch bar data model. */
    @InjectMocks
    private InlineSearchBarDataModel inlineSearchBarDataModel;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.models.component.AbstractBaseComponentTest#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
   
        FilterDataModel filterDataModel = new FilterDataModel();
        List<FilterDataModel> filterData = new ArrayList<>();
        filterData.add(filterDataModel);
        
        PrivateAccessor.setField(filterDataModel, "filter", "landsea");

        PrivateAccessor.setField(searchBarDataModel, "filter", filterData);
        PrivateAccessor.setField(searchBarDataModel, "searchTargetUrl", "/hal/search.html");
        PrivateAccessor.setField(inlineSearchBarDataModel, "texture", "/etc/designs/carnival/platform/images/textures/flv");
        PrivateAccessor.setField(titleDataModel, "title", "Title");
        PrivateAccessor.setField(titleDataModel, "dividerType", "icon");
        PrivateAccessor.setField(titleDataModel, "dividerText", "text");
        PrivateAccessor.setField(titleDataModel, "description", "Description");
        PrivateAccessor.setField(titleDataModel, "isCampaignHeaderRequired", "false");

        PrivateAccessor.setField(inlineSearchBarComponentModel, "searchBar", searchBarDataModel);
        PrivateAccessor.setField(inlineSearchBarComponentModel, "titleH1", titleDataModel);
        PrivateAccessor.setField(inlineSearchBarComponentModel, "inlineSearchBar", inlineSearchBarDataModel);
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "InlineSearchBarDataModel [texture=/etc/designs/carnival/platform/images/textures/flv]";
        assertTrue(expectedString.equals(inlineSearchBarComponentModel.getComponentData()[0].toString()));
    }

    /**
     * Test component model title data.
     *
     * @throws NoSuchFieldException the no such field exception
     */
    @Test
    public void testComponentModelTitleData() throws NoSuchFieldException {
        final String expectedTileDataString = "SearchBarHomeDataModel [searchTargetUrl=/hal/search.html, filters=null, labels=valueMap]";
        assertTrue(expectedTileDataString.equals(inlineSearchBarComponentModel.getComponentData()[1].toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() throws NoSuchFieldException {
       assertTrue(InlineSearchBarDataModel.COMPONENT_NAME.equals(inlineSearchBarDataModel.getComponentName()));
    }

}

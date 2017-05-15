package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.SearchResultsDataModel;
import com.carnival.platform.services.LabelConfigurationService;

import junitx.util.PrivateAccessor;

/**
 * The Class SearchHeaderComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchResultsComponentModelTest extends AbstractComponentTest {

    @InjectMocks
    private SearchResultsDataModel searchHeaderDataModel;

    @InjectMocks
    private SearchResultsComponentModel searchHeaderComponentModel;

    /** The label configuration service. */
    @Mock
    public LabelConfigurationService labelConfigurationService;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        PrivateAccessor.setField(searchHeaderDataModel, "logourl", "logourl");
        PrivateAccessor.setField(searchHeaderDataModel, "logoAltText", "logoAltText");
        PrivateAccessor.setField(searchHeaderDataModel, "logoTarget", "logoTarget");
        PrivateAccessor.setField(searchHeaderDataModel, "noOfResults", "4");
        PrivateAccessor.setField(searchHeaderDataModel, "itineraryPageURL", "itineraryPageURL");
        PrivateAccessor.setField(searchHeaderDataModel, "termsAndConditionURL", "termsAndConditionURL");
        PrivateAccessor.setField(searchHeaderDataModel, "additionalFilters", new String[] { "landsea", "portsOfCall" });
        PrivateAccessor.setField(searchHeaderDataModel, "primaryFilters", new String[] { "landsea", "ships" });
        PrivateAccessor.setField(searchHeaderDataModel, "subFilters", new String[] { "longest" });
        PrivateAccessor.setField(searchHeaderDataModel, "labels", valueMap);

        when(valueMap.get("paginationLabel")).thenReturn("paginationLabel");

        PrivateAccessor.setField(searchHeaderComponentModel, "searchResults", searchHeaderDataModel);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentName() throws NoSuchFieldException {
        assertTrue(SearchResultsDataModel.COMPONENT_NAME.equals(searchHeaderDataModel.getComponentName()));

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "SearchResultsDataModel [logourl=logourl, logoAltText=logoAltText, logoTarget=logoTarget, noOfResults=4, itineraryPageURL=itineraryPageURL, termsAndConditionURL=termsAndConditionURL, filters=null, moreFilters=null, sortByFilter=null, labels=valueMap]";
        assertTrue(expectedString.equals(searchHeaderComponentModel.getComponentData()[0].toString()));
    }

}

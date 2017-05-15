package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.FooterQuoteDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class FooterComponentModelTest.
 *
 * @author smedur
 */
@RunWith(MockitoJUnitRunner.class)
public class FooterQuoteComponentModelTest extends AbstractComponentTest {

    /* Data model which is tested in this test case */
    private FooterQuoteDataModel footerQuoteDataModel = new FooterQuoteDataModel();

    /* Component model which is tested in this test case */
    private FooterQuoteComponentModel footerQuoteComponentModel = new FooterQuoteComponentModel();
    
    @Before
    public void setUp() throws Exception {

        super.setUp();
        
        String quoteText = "Text";
        String quoteCredit = "Author";
        PrivateAccessor.setField(footerQuoteDataModel, "quoteText", quoteText);
        PrivateAccessor.setField(footerQuoteDataModel, "quoteCredit", quoteCredit);
        PrivateAccessor.setField(footerQuoteDataModel, "resource", resource);
        
        PrivateAccessor.setField(footerQuoteComponentModel, "footerQuote", footerQuoteDataModel);
    }

    @Test
    public void testComponentName() {
        assertTrue(FooterQuoteDataModel.COMPONENT_NAME.equals(footerQuoteComponentModel.getComponentName()));
    }

    @Override
    @Test
    public void testComponentModelData() throws NoSuchFieldException {
        String expectedStringOutput = "FooterQuoteComponentModel [ComponentData=[FooterQuoteDataModel [quoteText=Text, quoteCredit=Author]]]";
        assertTrue(expectedStringOutput.equals(footerQuoteComponentModel.toString()));
    }

    @Test
    public void testQuoteFieldsNotAuthored() throws NoSuchFieldException {
        String expectedStringOutput = "FooterQuoteComponentModel [ComponentData=[FooterQuoteDataModel [quoteText=null, quoteCredit=null]]]";
        PrivateAccessor.setField(footerQuoteDataModel, "quoteText", null);
        PrivateAccessor.setField(footerQuoteDataModel, "quoteCredit", null);
        
        assertTrue(expectedStringOutput.equals(footerQuoteComponentModel.toString()));
    }
}
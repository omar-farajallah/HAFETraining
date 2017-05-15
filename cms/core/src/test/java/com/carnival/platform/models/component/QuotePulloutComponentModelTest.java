package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.QuotePulloutDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class QuotePulloutComponentModelTest.
 *
 * @author ngurr1
 * @version 1.0
 * 
 *          This is a test class for QuotePulloutComponentModel Class
 */
@RunWith(MockitoJUnitRunner.class)
public class QuotePulloutComponentModelTest {

    /** The quote pullout data model. */
    @InjectMocks
    private QuotePulloutDataModel quotePulloutDataModel;

    /** The quote pullout component model. */
    @InjectMocks
    private QuotePulloutComponentModel quotePulloutComponentModel;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {

        PrivateAccessor.setField(quotePulloutDataModel, "quoteText", "quoteText");
        PrivateAccessor.setField(quotePulloutComponentModel, "quotePullout", quotePulloutDataModel);
    }

    /**
     * Test component name.
     */
    @Test
    public void testComponentName() {
        assertTrue(QuotePulloutDataModel.COMPONENT_NAME.equals(quotePulloutComponentModel.getComponentName()));
    }

    /**
     * Test component data.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testComponentData() throws Exception {
        PrivateAccessor.setField(quotePulloutComponentModel, "quotePullout", quotePulloutDataModel);
        assertTrue(quotePulloutDataModel.toString().equals(quotePulloutComponentModel.getComponentData()[0].toString()));
    }

}

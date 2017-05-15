package com.carnival.platform.models.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.CopyBlockDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class CopyBlockComponentModelTest.
 *
 * @author ngurr1
 * 
 *         This is a test class for CopyBlockComponentModel Class
 */
@RunWith(MockitoJUnitRunner.class)
public class CopyBlockComponentModelTest {

    /** The copy block data model. */
    @InjectMocks
    private CopyBlockDataModel copyBlockDataModel;

    /** The copy block component model. */
    @InjectMocks
    private CopyBlockComponentModel copyBlockComponentModel = new CopyBlockComponentModel();

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {

        PrivateAccessor.setField(copyBlockDataModel, "text", "text");
        PrivateAccessor.setField(copyBlockComponentModel, "copyBlock", copyBlockDataModel);
    }

    /**
     * Test component name.
     */
    @Test
    public void testComponentName() {
        assertTrue(CopyBlockDataModel.COMPONENT_NAME.equals(copyBlockComponentModel.getComponentName()));
    }

    /**
     * Test component data.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testComponentData() throws Exception {
        PrivateAccessor.setField(copyBlockComponentModel, "copyBlock", copyBlockDataModel);
        assertEquals(copyBlockDataModel.toString(),copyBlockComponentModel.getComponentData()[0].toString());
    }

}

package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.GlobalHeaderDataModel;
import com.carnival.platform.models.data.UtilityNavDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class GlobalHeaderComponentModelTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class GlobalHeaderComponentModelTest extends AbstractComponentTest {

    /** The global header data model. */
    @InjectMocks
    private GlobalHeaderDataModel globalHeaderDataModel;

    /** The global header component model. */
    @InjectMocks
    private GlobalHeaderComponentModel globalHeaderComponentModel;

    /** The utility nav data model. */
    @InjectMocks
    private UtilityNavDataModel utilityNavDataModel;

    /** The expected string output. */
    final String expectedStringOutput = "GlobalHeaderDataModel [logo=/content/dam/carnival/hal/test.jpg, logoAlt=Alt, logoTarget=/content/carnival/hal/en_us/home, menuText=MENU, closeButtonText=closeButtonText, phoneNumber=12345, utilityNavRequired=true, numberOfItems=2, navigationItems=null]";

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.models.component.AbstractBaseComponentTest#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        PrivateAccessor.setField(globalHeaderDataModel, "logo", "/content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(globalHeaderDataModel, "logoAlt", "Alt");
        PrivateAccessor.setField(globalHeaderDataModel, "logoTarget", "/content/carnival/hal/en_us/home");
        PrivateAccessor.setField(globalHeaderDataModel, "menuText", "MENU");
        PrivateAccessor.setField(globalHeaderDataModel, "closeButtonText", "closeButtonText");
        PrivateAccessor.setField(globalHeaderDataModel, "phoneNumber", "12345");
        PrivateAccessor.setField(globalHeaderDataModel, "utilityNavRequired", "true");
        PrivateAccessor.setField(globalHeaderDataModel, "numberOfItems", "2");
        PrivateAccessor.setField(globalHeaderDataModel, "resource", resource);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Test
    public void testComponentName() throws NoSuchFieldException {
        PrivateAccessor.setField(globalHeaderComponentModel, "globalHeader", globalHeaderDataModel);
        assertTrue(GlobalHeaderDataModel.COMPONENT_NAME.equals(globalHeaderComponentModel.getComponentName()));
    }

    /**
     * Test component data without utility nav.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testComponentDataWithoutUtilityNav() throws Exception {
        final String expectedString = "GlobalHeaderDataModel [logo=/content/dam/carnival/hal/test.jpg, logoAlt=Alt, logoTarget=null, menuText=MENU, closeButtonText=closeButtonText, phoneNumber=12345, utilityNavRequired=false, numberOfItems=2, navigationItems=null]";
        PrivateAccessor.setField(globalHeaderComponentModel, "globalHeader", globalHeaderDataModel);
        PrivateAccessor.setField(globalHeaderDataModel, "utilityNavRequired", "false");
        assertTrue(expectedString.equals(globalHeaderComponentModel.getComponentData()[0].toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "GlobalHeaderDataModel [logo=/content/dam/carnival/hal/test.jpg, logoAlt=Alt, logoTarget=null, menuText=MENU, closeButtonText=closeButtonText, phoneNumber=12345, utilityNavRequired=true, numberOfItems=2, navigationItems=null]";
        PrivateAccessor.setField(globalHeaderComponentModel, "globalHeader", globalHeaderDataModel);
        PrivateAccessor.setField(globalHeaderComponentModel, "utilityNav", utilityNavDataModel);
        assertTrue(expectedString.equals(globalHeaderComponentModel.getComponentData()[0].toString()));
    }

}

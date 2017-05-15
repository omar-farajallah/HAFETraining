package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.FullWidthImageDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class FullWidthImageComponentModelTest.
 *
 * @author ngurr1
 * 
 *         This is a test class for Full Width Image Component Model Class
 */
@RunWith(MockitoJUnitRunner.class)
public class FullWidthImageComponentModelTest extends AbstractComponentTest {

    /** The full width image data model. */
    @InjectMocks
    private FullWidthImageDataModel fullWidthImageDataModel;

    /** The full width image component model. */
    @InjectMocks
    private FullWidthImageComponentModel fullWidthImageComponentModel;

    /* (non-Javadoc)
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        PrivateAccessor.setField(fullWidthImageDataModel, "desktopImageURL", "content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(fullWidthImageDataModel, "tabletImageURL", "content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(fullWidthImageDataModel, "mobileImageURL", "content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(fullWidthImageDataModel, "alt", "alt");
        PrivateAccessor.setField(fullWidthImageDataModel, "resource", resource);
        PrivateAccessor.setField(fullWidthImageComponentModel, "fullWidthImage", fullWidthImageDataModel);
    }

    /* (non-Javadoc)
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#testComponentName()
     */
    @Test
    public void testComponentName() {
        assertTrue(FullWidthImageDataModel.COMPONENT_NAME.equals(fullWidthImageDataModel.getComponentName()));
    }

    /* (non-Javadoc)
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "FullWidthImageComponentModel [ComponentName=fullWidthImage, ComponentData=[FullWidthImageDataModel [image=Image [imgAlt=alt, smallRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.320.214.low.jpg, retina=content/dam/carnival/hal/test.jpg.image.640.428.low.jpg], mediumRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.585.390.medium.jpg, retina=content/dam/carnival/hal/test.jpg.image.1170.780.medium.jpg], largeRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.668.446.high.jpg, retina=content/dam/carnival/hal/test.jpg.image.1336.892.high.jpg]]]]]";
        assertTrue(expectedString.equals(fullWidthImageComponentModel.toString()));
    }
}

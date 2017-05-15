package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.HeroTileDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class HeroTileComponentModelTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class HeroTileComponentModelTest extends AbstractComponentTest {

    /** The component model. */
    @InjectMocks
    private HeroTileComponentModel componentModel;

    /** The data model. */
    @InjectMocks
    private HeroTileDataModel dataModel;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.models.component.AbstractBaseComponentTest#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        PrivateAccessor.setField(dataModel, "type", "image");
        PrivateAccessor.setField(dataModel, "imageURL", "/content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(dataModel, "imageAltText", "Alt");
        PrivateAccessor.setField(dataModel, "videoURL", "/content/dam/carnival/hal/test.mp4");
        PrivateAccessor.setField(dataModel, "cardAlignment", "center");
        PrivateAccessor.setField(dataModel, "logo", "/content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(dataModel, "logoAlt", "Logo Alt");
        PrivateAccessor.setField(dataModel, "title", "Title");
        PrivateAccessor.setField(dataModel, "description", "Description");
        PrivateAccessor.setField(dataModel, "ctaIcon", "/content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(dataModel, "ctaAlt", "Alt");
        PrivateAccessor.setField(dataModel, "ctaLabel", "CTA");
        PrivateAccessor.setField(dataModel, "ctaType", "URL");
        PrivateAccessor.setField(dataModel, "ctaURL", "/content/carnival/hal/en_us/home");
        PrivateAccessor.setField(dataModel, "resource", resource);
        PrivateAccessor.setField(componentModel, "heroTile", dataModel);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "HeroTileComponentModel [ComponentData=[HeroTileDataModel [type=image, videoURL=/content/dam/carnival/hal/test.mp4, cardAlignment=center, logo=/content/dam/carnival/hal/test.jpg, logoAlt=Logo Alt, title=Title, description=Description, ctaIcon=/content/dam/carnival/hal/test.jpg, ctaAlt=Alt, ctaLabel=CTA, ctaType=URL, ctaURL=null, image=Image [imgAlt=Alt, smallRendition=Rendition [regular=/content/dam/carnival/hal/test.jpg.image.320.240.low.jpg, retina=/content/dam/carnival/hal/test.jpg.image.640.480.low.jpg], mediumRendition=Rendition [regular=/content/dam/carnival/hal/test.jpg.image.768.575.medium.jpg, retina=/content/dam/carnival/hal/test.jpg.image.1536.1150.medium.jpg], largeRendition=Rendition [regular=/content/dam/carnival/hal/test.jpg.image.1480.538.high.jpg, retina=/content/dam/carnival/hal/test.jpg.image.2960.1076.high.jpg]]]], ComponentName=heroTile]";
        assertTrue(expectedString.equals(componentModel.toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() {
        assertTrue(HeroTileDataModel.COMPONENT_NAME.equals(dataModel.getComponentName()));
    }
}

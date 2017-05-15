package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.DestinationTileDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class DestinationTileComponentModelTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DestinationTileComponentModelTest extends AbstractComponentTest {

    /** The destination tile data model. */
    @InjectMocks
    private DestinationTileDataModel destinationTileDataModel;

    /** The destination tile component model. */
    @InjectMocks
    private DestinationTileComponentModel destinationTileComponentModel;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.models.component.AbstractBaseComponentTest#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        PrivateAccessor.setField(destinationTileDataModel, "imageURL", "/content/dam/carnival/image-1.jpg");
        PrivateAccessor.setField(destinationTileDataModel, "title", "Heading");
        PrivateAccessor.setField(destinationTileDataModel, "imageAltText", "alt text");
        PrivateAccessor.setField(destinationTileDataModel, "ctaLabel", "cta");
        PrivateAccessor.setField(destinationTileDataModel, "ctaURL", "/hal/en.html");
        PrivateAccessor.setField(destinationTileDataModel, "description", "description test");

        PrivateAccessor.setField(destinationTileDataModel, "offerBanner", "description test");
        PrivateAccessor.setField(destinationTileDataModel, "textAlignment", "centre");
        PrivateAccessor.setField(destinationTileComponentModel, "destinationTile", destinationTileDataModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Test
    public void testComponentName() {
        assertTrue(DestinationTileDataModel.COMPONENT_NAME.equals(destinationTileDataModel.getComponentName()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    @Test
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "DestinationTileDataModel [title = Heading, offerBanner = description test, textAlignment = centre, ctaLabel = cta, ctaURL = /hal/en.html, description = description test, image = Image [imgAlt=alt text, smallRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.504.377.low.jpg, retina=/content/dam/carnival/image-1.jpg.image.1008.754.low.jpg], mediumRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.348.260.medium.jpg, retina=/content/dam/carnival/image-1.jpg.image.696.520.medium.jpg], largeRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.320.245.high.jpg, retina=/content/dam/carnival/image-1.jpg.image.640.490.high.jpg]]]";
        assertTrue(expectedString.equals(destinationTileComponentModel.getComponentData()[0].toString()));
    }

}

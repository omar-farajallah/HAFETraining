package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.DestinationTileDataModel;
import com.carnival.platform.models.data.SpecialOffersDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class SpecialOffersComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class SpecialOffersComponentModelTest extends AbstractComponentTest {

    /** The special offers component model. */
    @InjectMocks
    private SpecialOffersComponentModel specialOffersComponentModel;

    /** The special offers data model. */
    @InjectMocks
    private SpecialOffersDataModel specialOffersDataModel;

    /** The destination tile data model. */
    @InjectMocks
    private DestinationTileDataModel destinationTileDataModel;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        PrivateAccessor.setField(specialOffersDataModel, "ctaLabel", "Button");
        PrivateAccessor.setField(specialOffersDataModel, "ctaUrl", "http://carnival.com");
        PrivateAccessor.setField(specialOffersDataModel, "labelConfigurationService", labelConfigurationService);
        PrivateAccessor.setField(specialOffersDataModel, "resource", resource);
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");
        PrivateAccessor.setField(specialOffersComponentModel, "specialOffers", specialOffersDataModel);

        PrivateAccessor.setField(destinationTileDataModel, "title", "test");
        PrivateAccessor.setField(destinationTileDataModel, "description", "desc");
        PrivateAccessor.setField(destinationTileDataModel, "textAlignment", "left");
        PrivateAccessor.setField(destinationTileDataModel, "ctaLabel", "cta");
        PrivateAccessor.setField(destinationTileDataModel, "ctaURL", "http://carnival.com");
        PrivateAccessor.setField(destinationTileDataModel, "imageURL", "/content/dam/carnival/image-1.jpg");
        PrivateAccessor.setField(destinationTileDataModel, "imageAltText", "alt text");

        List<DestinationTileDataModel> destinationList = new ArrayList<>();
        destinationList.add(destinationTileDataModel);

        PrivateAccessor.setField(specialOffersComponentModel, "destinationTile", destinationList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "SpecialOffersComponentModel [ComponentData=[SpecialOffersDataModel [DividerText=of, CtaLabel=Button, CtaUrl=http://carnival.com, ComponentName=specialOffers], DestinationTileDataModel [title = test, offerBanner = null, textAlignment = left, ctaLabel = cta, ctaURL = http://carnival.com, description = desc, image = Image [imgAlt=alt text, smallRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.504.377.low.jpg, retina=/content/dam/carnival/image-1.jpg.image.1008.754.low.jpg], mediumRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.348.260.medium.jpg, retina=/content/dam/carnival/image-1.jpg.image.696.520.medium.jpg], largeRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.320.245.high.jpg, retina=/content/dam/carnival/image-1.jpg.image.640.490.high.jpg]]]]]";
        assertTrue(expectedString.equals(specialOffersComponentModel.toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() {
        assertTrue(SpecialOffersDataModel.COMPONENT_NAME.equals(specialOffersDataModel.getComponentName()));
    }

}

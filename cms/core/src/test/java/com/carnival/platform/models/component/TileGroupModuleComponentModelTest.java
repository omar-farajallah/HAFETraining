package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.DestinationTileDataModel;
import com.carnival.platform.models.data.TileGroupModuleDataModel;
import com.carnival.platform.models.data.TitleDataModel;
import com.day.cq.wcm.api.Page;

import junitx.util.PrivateAccessor;

/**
 * The Class TileGroupModuleComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class TileGroupModuleComponentModelTest extends AbstractComponentTest {

    /** The tile group module data model. */
    @InjectMocks
    private TileGroupModuleDataModel tileGroupModuleDataModel;

    /** The title data model. */
    @InjectMocks
    private TitleDataModel titleDataModel;

    /** The destination tile data model. */
    @InjectMocks
    private DestinationTileDataModel destinationTileDataModel;

    /** The tile group module component model. */
    @InjectMocks
    private TileGroupModuleComponentModel tileGroupModuleComponentModel;

    /** The parent page 1. */
    @Mock
    private Page parentPage1;

    /** The parent page 2. */
    @Mock
    private Page parentPage2;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        PrivateAccessor.setField(tileGroupModuleDataModel, "resource", resource);

        when(resource.getPath()).thenReturn("/content/carnival/hal/en");
        when(pageMang.getContainingPage("/content/carnival/hal/en")).thenReturn(page);
        when(page.getAbsoluteParent(1)).thenReturn(parentPage1);
        when(page.getAbsoluteParent(2)).thenReturn(parentPage2);

        when(parentPage2.getName()).thenReturn("en");
        when(parentPage1.getName()).thenReturn("platform");

        when(tileGroupModuleDataModel.getLocaleName()).thenReturn("en");
        when(tileGroupModuleDataModel.getBrandName()).thenReturn("platform");

        // tile group model data set
        PrivateAccessor.setField(tileGroupModuleDataModel, "termsLabel", "termsLabel");
        PrivateAccessor.setField(tileGroupModuleDataModel, "termsLink", "terms");
        PrivateAccessor.setField(tileGroupModuleDataModel, "labelConfigurationService", labelConfigurationService);

        // title component data set
        PrivateAccessor.setField(titleDataModel, "title", "Title");
        PrivateAccessor.setField(titleDataModel, "dividerType", "icon");
        PrivateAccessor.setField(titleDataModel, "dividerText", "text");
        PrivateAccessor.setField(titleDataModel, "description", "Description");

        // destination data set
        PrivateAccessor.setField(destinationTileDataModel, "imageURL", "/content/dam/carnival/image-1.jpg");
        PrivateAccessor.setField(destinationTileDataModel, "title", "Heading");
        PrivateAccessor.setField(destinationTileDataModel, "imageAltText", "alt1");
        PrivateAccessor.setField(destinationTileDataModel, "ctaLabel", "cta");
        PrivateAccessor.setField(destinationTileDataModel, "ctaURL", "/hal/en.html");
        PrivateAccessor.setField(destinationTileDataModel, "description", "Description");
        PrivateAccessor.setField(destinationTileDataModel, "offerBanner", "Description");
        PrivateAccessor.setField(destinationTileDataModel, "textAlignment", "centre");
        PrivateAccessor.setField(destinationTileDataModel, "imageURL", "/content/dam/carnival/image-1.jpg");
        PrivateAccessor.setField(destinationTileDataModel, "imageAltText", "alt text");

        List<DestinationTileDataModel> destinationTileList = new ArrayList<>();
        destinationTileList.add(destinationTileDataModel);
        PrivateAccessor.setField(tileGroupModuleComponentModel, "tileGroupModule", tileGroupModuleDataModel);
        PrivateAccessor.setField(tileGroupModuleComponentModel, "titleH1", titleDataModel);
        PrivateAccessor.setField(tileGroupModuleComponentModel, "destinationTile", destinationTileList);
    }

    /**
     * Test component name.
     */
    @Test
    public void testComponentName() {
        assertTrue(TileGroupModuleDataModel.COMPONENT_NAME.equals(tileGroupModuleDataModel.getComponentName()));
    }

    /**
     * Test model data.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testModelData() throws Exception {
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");
        final String expectedtileGrpModel = "TileGroupModuleDataModel [termsLabel=termsLabel, termsLink=terms, dividerText=of]";
        assertTrue(expectedtileGrpModel.equals(tileGroupModuleDataModel.toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedDestinationObj = "DestinationTileDataModel [title = Heading, offerBanner = Description, textAlignment = centre, ctaLabel = cta, ctaURL = /hal/en.html, description = Description, image = Image [imgAlt=alt text, smallRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.504.377.low.jpg, retina=/content/dam/carnival/image-1.jpg.image.1008.754.low.jpg], mediumRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.348.260.medium.jpg, retina=/content/dam/carnival/image-1.jpg.image.696.520.medium.jpg], largeRendition=Rendition [regular=/content/dam/carnival/image-1.jpg.image.320.245.high.jpg, retina=/content/dam/carnival/image-1.jpg.image.640.490.high.jpg]]]";
        assertTrue(titleDataModel.toString().equals(tileGroupModuleComponentModel.getComponentData()[1].toString()));
        assertTrue(expectedDestinationObj.equals(tileGroupModuleComponentModel.getComponentData()[2].toString()));
    }

}

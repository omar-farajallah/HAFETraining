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
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.BulletedListDataModel;
import com.carnival.platform.models.data.DestinationTileDataModel;
import com.carnival.platform.models.data.TileGridDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class TileGridComponentModelTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TileGridComponentModelTest extends AbstractComponentTest {

    /** The tile grid data model. */
    @InjectMocks
    private TileGridDataModel tileGridDataModel;

    /** The tile grid component model. */
    @InjectMocks
    private TileGridComponentModel tileGridComponentModel;

    /** The bulleted list model. */
    @InjectMocks
    private BulletedListDataModel bulletedListModel;

    /** The destination tile. */
    private List<DestinationTileDataModel> destinationTile;

    /** The bulleted list. */
    private List<BulletedListDataModel> bulletedList;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.models.component.AbstractBaseComponentTest#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        PrivateAccessor.setField(bulletedListModel, "bulletedListContent", "bulletedListContent");
        bulletedList = new ArrayList<>();
        bulletedList.add(bulletedListModel);

        PrivateAccessor.setField(tileGridDataModel, "backgroundHeroImage", "/content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(tileGridDataModel, "backgroundHeroImageAltText", "Hero Alt");
        PrivateAccessor.setField(tileGridDataModel, "headerH2Type", "text");
        PrivateAccessor.setField(tileGridDataModel, "headerH2Logo", "/content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(tileGridDataModel, "headerH2Text", "Header");
        PrivateAccessor.setField(tileGridDataModel, "headerH2LogoAltText", "Logo Alt");
        PrivateAccessor.setField(tileGridDataModel, "paragraphTextBlock", "Paragraph");
        PrivateAccessor.setField(tileGridDataModel, "bulletedListType", "icon");
        PrivateAccessor.setField(tileGridDataModel, "termsAndConditionsText", "Terms");
        PrivateAccessor.setField(tileGridDataModel, "termsAndConditionsURL", "/content/carnival/hal/en_us/home");
        PrivateAccessor.setField(tileGridDataModel, "title", "Title");
        PrivateAccessor.setField(tileGridDataModel, "headerH2", "Header H2");
        PrivateAccessor.setField(tileGridDataModel, "ctaButtonLabel", "CTA");
        PrivateAccessor.setField(tileGridDataModel, "ctaButtonURL", "/content/carnival/hal/en_us/home");
        PrivateAccessor.setField(tileGridDataModel, "resource", resource);
        PrivateAccessor.setField(tileGridDataModel, "labelConfigurationService", labelConfigurationService);
        PrivateAccessor.setField(tileGridDataModel, "bulletedList", bulletedList);

        DestinationTileDataModel destinationTileDataModel = new DestinationTileDataModel();
        PrivateAccessor.setField(destinationTileDataModel, "imageURL", "/content/dam/carnival/image-1.jpg");
        PrivateAccessor.setField(destinationTileDataModel, "title", "Heading");
        PrivateAccessor.setField(destinationTileDataModel, "imageAltText", "alt1");
        PrivateAccessor.setField(destinationTileDataModel, "ctaLabel", "cta");
        PrivateAccessor.setField(destinationTileDataModel, "ctaURL", "/hal/en.html");
        PrivateAccessor.setField(destinationTileDataModel, "description", "description test");

        destinationTile = new ArrayList<DestinationTileDataModel>();
        destinationTile.add(destinationTileDataModel);
        PrivateAccessor.setField(tileGridComponentModel, "tileGrid", tileGridDataModel);
        PrivateAccessor.setField(tileGridComponentModel, "destinationTile", destinationTile);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Test
    public void testComponentName() {
        assertTrue(TileGridDataModel.COMPONENT_NAME.equals(tileGridDataModel.getComponentName()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedStringOutput = "TileGridDataModel [headerH2Type=text, headerH2Text=Header, headerH2Logo=/content/dam/carnival/hal/test.jpg, headerH2LogoAltText=Logo Alt, paragraphTextBlock=Paragraph, bulletedListType=icon, termsAndConditionsText=Terms, termsAndConditionsURL=null, title=Title, headerH2=Header H2, ctaButtonLabel=CTA, ctaButtonURL=null, image=Image [imgAlt=Hero Alt, smallRendition=Rendition [regular=/content/dam/carnival/hal/test.jpg.image.320.514.low.jpg, retina=/content/dam/carnival/hal/test.jpg.image.640.1028.low.jpg], mediumRendition=Rendition [regular=/content/dam/carnival/hal/test.jpg.image.768.936.medium.jpg, retina=/content/dam/carnival/hal/test.jpg.image.1536.1872.medium.jpg], largeRendition=Rendition [regular=/content/dam/carnival/hal/test.jpg.image.1480.688.high.jpg, retina=/content/dam/carnival/hal/test.jpg.image.2960.1376.high.jpg]], ofLabel=of]";
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");
        PrivateAccessor.setField(tileGridComponentModel, "tileGrid", tileGridDataModel);
        PrivateAccessor.setField(tileGridComponentModel, "destinationTile", destinationTile);
        assertTrue(expectedStringOutput.equals(tileGridComponentModel.getComponentData()[0].toString()));
    }

    /**
     * Test bulleted list content.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Test
    public void testBulletedListContent() throws NoSuchFieldException {
        final String expectedString = "BulletedListDataModel [bulletedListContent=bulletedListContent]";
        assertTrue(expectedString.equals(bulletedListModel.toString()));
    }

}

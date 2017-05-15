package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.CarouselEditorialDataModel;
import com.carnival.platform.models.data.CarouselEditorialTileModel;
import com.carnival.platform.models.data.GridImageCopyBlockDataModel;
import com.carnival.platform.models.data.GridImageCopyDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class GridImageCopyBlockComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class GridImageCopyBlockComponentModelTest extends AbstractComponentTest {

    /** The grid image copy block. */
    @InjectMocks
    private GridImageCopyBlockDataModel gridImageCopyBlock;

    /** The sling model. */
    @InjectMocks
    private GridImageCopyDataModel slingModel;

    /** The carousel editorial data model. */
    @InjectMocks
    private CarouselEditorialDataModel carouselEditorialDataModel;

    /** The carousel editorial tile model. */
    @InjectMocks
    private CarouselEditorialTileModel carouselEditorialTileModel;

    /** The grid image copy block component model. */
    private GridImageCopyBlockComponentModel gridImageCopyBlockComponentModel = new GridImageCopyBlockComponentModel();

    /** The resource. */
    @Mock
    private Resource resource;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        List<GridImageCopyDataModel> gridDetails = new ArrayList<>();
        List<CarouselEditorialTileModel> carouselEditorialTileModelList = new ArrayList<>();
        PrivateAccessor.setField(carouselEditorialTileModel, "resource", resource);
        carouselEditorialTileModelList.add(carouselEditorialTileModel);
        PrivateAccessor.setField(carouselEditorialDataModel, "resource", resource);
        PrivateAccessor.setField(carouselEditorialDataModel, "tiles", carouselEditorialTileModelList);

        PrivateAccessor.setField(slingModel, "resource", resource);
        PrivateAccessor.setField(slingModel, "align", "align");
        PrivateAccessor.setField(slingModel, "ctaLabel", "ctaLabel");
        PrivateAccessor.setField(slingModel, "ctaURL",
                "/content/carnival/platform/en/sprint-1-json/jcr:content/contentpar_2/carouseleditorial/carouselEditorial");
        PrivateAccessor.setField(slingModel, "description", "description");
        PrivateAccessor.setField(slingModel, "iconCTALabel", "iconCTALabel");
        PrivateAccessor.setField(slingModel, "iconCTAURL", "iconCTAURL");
        PrivateAccessor.setField(slingModel, "iconType", "iconType");
        PrivateAccessor.setField(slingModel, "authoredImage", "authoredImage");
        PrivateAccessor.setField(slingModel, "imageAltText", "imageAltText");
        PrivateAccessor.setField(slingModel, "termsLabel", "termsLabel");
        PrivateAccessor.setField(slingModel, "termsLink", "termsLink");
        PrivateAccessor.setField(slingModel, "title", "title");
        PrivateAccessor.setField(slingModel, "type", "type");

        when(slingModel.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.getResource(slingModel.getCtaURL())).thenReturn(resource);
        when(resource.adaptTo(CarouselEditorialDataModel.class)).thenReturn(carouselEditorialDataModel);
        gridDetails.add(slingModel);
        PrivateAccessor.setField(gridImageCopyBlock, "gridList", gridDetails);
        PrivateAccessor.setField(gridImageCopyBlockComponentModel, "gridImageCopyBlock", gridImageCopyBlock);

    }

    /**
     * Test component name.
     */
    @Test
    public void testComponentName() {
        assertTrue(GridImageCopyBlockDataModel.COMPONENT_NAME.equals(gridImageCopyBlock.getComponentName()));
    }

    /**
     * Test component model data.
     *
     * @throws NoSuchFieldException the no such field exception
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "ImageCopyBlockDataModel [gridList=[GridImageCopyDataModel [align=align, ctaLabel=ctaLabel, description=description, iconCTALabel=iconCTALabel, iconCTAURL=iconCTAURL, iconType=iconType, termsLabel=termsLabel, termsLink=termsLink, title=title, type=type, image=Image [imgAlt=imageAltText, smallRendition=Rendition [regular=authoredImage.image.320.240.low.jpg, retina=authoredImage.image.640.480.low.jpg], mediumRendition=Rendition [regular=authoredImage.image.768.575.medium.jpg, retina=authoredImage.image.1536.1150.medium.jpg], largeRendition=Rendition [regular=authoredImage.image.740.836.high.jpg, retina=authoredImage.image.1480.1672.high.jpg]], carouselEditorial=CarouselEditorialDataModel [title=null, termsText=null, termsURL=null, tiles=[CarouselEditorialTileModel [titleHeading=null, imageType=null, description=null, imageObj=Image [imgAlt=null, smallRendition=Rendition [regular=null.image.286.286.low.jpg, retina=null.image.572.572.low.jpg], mediumRendition=Rendition [regular=null.image.360.360.medium.jpg, retina=null.image.720.720.medium.jpg], largeRendition=Rendition [regular=null.image.466.466.high.jpg, retina=null.image.932.932.high.jpg]]]], dividerText=of]]]]";
        PrivateAccessor.setField(gridImageCopyBlockComponentModel, "gridImageCopyBlock", gridImageCopyBlock);
        assertTrue(expectedString.equals(gridImageCopyBlockComponentModel.getComponentData()[0].toString()));
    }
}

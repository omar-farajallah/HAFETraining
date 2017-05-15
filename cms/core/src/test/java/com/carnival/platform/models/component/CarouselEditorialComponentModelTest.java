package com.carnival.platform.models.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.AbstractDataModel;
import com.carnival.platform.models.data.CarouselEditorialDataModel;
import com.carnival.platform.models.data.CarouselEditorialTileModel;

import junitx.util.PrivateAccessor;

/**
 * The Class CarouselEditorialComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class CarouselEditorialComponentModelTest extends AbstractComponentTest {

    /** The carousel editorial data model. */
    @InjectMocks
    private CarouselEditorialDataModel carouselEditorialDataModel;

    /** The component model. */
    @InjectMocks
    private CarouselEditorialComponentModel componentModel;

    /** The carousel editorial tile model. */
    @InjectMocks
    private CarouselEditorialTileModel carouselEditorialTileModel;

    /** The abstract model. */
    @Mock
    AbstractDataModel abstractModel;

    /** The resource resolver. */

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        PrivateAccessor.setField(carouselEditorialTileModel, "titleHeading", "titleHeading");
        PrivateAccessor.setField(carouselEditorialTileModel, "imageType", "rectangle");
        PrivateAccessor.setField(carouselEditorialTileModel, "authoredImage", "image.jpg");
        PrivateAccessor.setField(carouselEditorialTileModel, "imageAltText", "imageAltText");
        PrivateAccessor.setField(carouselEditorialTileModel, "description", "description");
        PrivateAccessor.setField(carouselEditorialTileModel, "titleHeading", "titleHeading");
        List<CarouselEditorialTileModel> carouselEditorialTileModelList = new ArrayList<>();
        carouselEditorialTileModelList.add(carouselEditorialTileModel);

        PrivateAccessor.setField(carouselEditorialDataModel, "title", "title test");
        PrivateAccessor.setField(carouselEditorialDataModel, "termsText", "termsText test");
        PrivateAccessor.setField(carouselEditorialDataModel, "termsURL", "/content/carnival/hal/en/test");
        PrivateAccessor.setField(carouselEditorialDataModel, "resource", resource);
        PrivateAccessor.setField(carouselEditorialDataModel, "tiles", carouselEditorialTileModelList);
        PrivateAccessor.setField(carouselEditorialDataModel, "labelConfigurationService", labelConfigurationService);

        PrivateAccessor.setField(abstractModel, "resource", resource);
        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.map("/content/carnival/hal/en/test")).thenReturn("/hal/en/test");
        PrivateAccessor.setField(componentModel, "carouselEditorial", carouselEditorialDataModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "CarouselEditorialComponentModel [ComponentData=[CarouselEditorialDataModel [title=title test, termsText=termsText test, termsURL=/hal/en/test.html, tiles=[CarouselEditorialTileModel [titleHeading=titleHeading, imageType=rectangle, description=description, imageObj=Image [imgAlt=imageAltText, smallRendition=Rendition [regular=image.jpg.image.286.286.low.jpg, retina=image.jpg.image.572.572.low.jpg], mediumRendition=Rendition [regular=image.jpg.image.360.360.medium.jpg, retina=image.jpg.image.720.720.medium.jpg], largeRendition=Rendition [regular=image.jpg.image.466.466.high.jpg, retina=image.jpg.image.932.932.high.jpg]]]], dividerText=of]], ComponentName=carouselEditorial]";
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");
        assertEquals(expectedString, componentModel.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() {
        assertTrue(CarouselEditorialDataModel.COMPONENT_NAME.equals(carouselEditorialDataModel.getComponentName()));
    }

}

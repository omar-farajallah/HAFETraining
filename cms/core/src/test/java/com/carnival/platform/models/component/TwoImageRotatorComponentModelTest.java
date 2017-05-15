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

import com.carnival.platform.models.data.TwoImageRotatorDataModel;
import com.carnival.platform.models.data.TwoImageSlideModel;
import com.carnival.platform.services.impl.TwoImageSlideServiceimpl;

import junitx.util.PrivateAccessor;

/**
 * The Class TwoImageRotatorComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class TwoImageRotatorComponentModelTest extends AbstractComponentTest {

    /** The two image rotator comp model. */
    @InjectMocks
    TwoImageRotatorComponentModel twoImageRotatorCompModel;

    /** The tile group module data model. */
    @InjectMocks
    private TwoImageRotatorDataModel twoImageRotatorDataModel;

    /** The two image rotator data model. */
    @InjectMocks
    private TwoImageSlideModel twoImageSlideModel;

    /** The two image rotator data service impl. */
    @InjectMocks
    private TwoImageSlideServiceimpl twoImageSlideServiceimpl;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        PrivateAccessor.setField(twoImageSlideModel, "caption", "caption");
        PrivateAccessor.setField(twoImageSlideModel, "image1", "image1.jpg");
        PrivateAccessor.setField(twoImageSlideModel, "image1Alt", "image1Alt");
        PrivateAccessor.setField(twoImageSlideModel, "image2", "image2.jpg");
        PrivateAccessor.setField(twoImageSlideModel, "image2Alt", "image2Alt");
        PrivateAccessor.setField(twoImageSlideModel, "pullquote", "pullquote");
        PrivateAccessor.setField(twoImageSlideModel, "quotedesc", "quotedesc");

        PrivateAccessor.setField(twoImageRotatorDataModel, "title", "title");
        PrivateAccessor.setField(twoImageRotatorDataModel, "resource", resource);
        PrivateAccessor.setField(twoImageRotatorDataModel, "description", "description");
        PrivateAccessor.setField(twoImageRotatorDataModel, "labelConfigurationService", labelConfigurationService);

        PrivateAccessor.setField(twoImageRotatorCompModel, "twoImageRotator", twoImageRotatorDataModel);
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");
    }

    /**
     * Test component name.
     */
    @Test
    public void testComponentName() {
        assertTrue(TwoImageRotatorDataModel.COMPONENT_NAME.equals(twoImageRotatorDataModel.getComponentName()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "TwoImageRotatorDataModel [title=title, description=description, dividerText=of, slide=[TwoImageSlideModel [caption=caption, pullquote=pullquote, quotedesc=quotedesc, images=[{image=Image [imgAlt=image1Alt, smallRendition=Rendition [regular=image1.jpg.image.256.340.low.jpg, retina=image1.jpg.image.513.681.low.jpg], mediumRendition=Rendition [regular=image1.jpg.image.348.464.medium.jpg, retina=image1.jpg.image.696.928.medium.jpg], largeRendition=Rendition [regular=image1.jpg.image.440.587.high.jpg, retina=image1.jpg.image.880.1174.high.jpg]]}, {image=Image [imgAlt=image2Alt, smallRendition=Rendition [regular=image2.jpg.image.256.340.low.jpg, retina=image2.jpg.image.513.681.low.jpg], mediumRendition=Rendition [regular=image2.jpg.image.168.224.medium.jpg, retina=image2.jpg.image.336.448.medium.jpg], largeRendition=Rendition [regular=image2.jpg.image.210.280.high.jpg, retina=image2.jpg.image.420.560.high.jpg]], pullquote=pullquote, quotedesc=quotedesc}]]]]";
        List<TwoImageSlideModel> modelList = new ArrayList<>();
        twoImageSlideServiceimpl.updateModel(twoImageSlideModel);
        modelList.add(twoImageSlideModel);
        PrivateAccessor.setField(twoImageRotatorDataModel, "slide", modelList);
        PrivateAccessor.setField(twoImageRotatorCompModel, "twoImageRotator", twoImageRotatorDataModel);
        assertTrue(expectedString.equals(twoImageRotatorCompModel.getComponentData()[0].toString()));
    }
}

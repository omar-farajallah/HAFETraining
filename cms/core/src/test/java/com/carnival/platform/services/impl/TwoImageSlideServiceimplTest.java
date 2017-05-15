package com.carnival.platform.services.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.TwoImageSlideModel;

import junitx.util.PrivateAccessor;

/**
 * The Class TwoImageSlideServiceimplTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TwoImageSlideServiceimplTest {

    /** The resource resolver. */
    @Mock
    private ResourceResolver resourceResolver;

    /** The resource. */
    @Mock
    private Resource resource;

    /** The two image rotator data model. */
    private TwoImageSlideModel twoImageSlideModel;

    /** The two image rotator data service impl. */
    @InjectMocks
    private TwoImageSlideServiceimpl twoImageSlideServiceimpl;


    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        twoImageSlideModel = new TwoImageSlideModel();
        PrivateAccessor.setField(twoImageSlideModel, "caption", "caption");
        PrivateAccessor.setField(twoImageSlideModel, "image1", "image1.jpg");
        PrivateAccessor.setField(twoImageSlideModel, "image1Alt", "image1Alt");
        PrivateAccessor.setField(twoImageSlideModel, "image2", "image2.jpg");
        PrivateAccessor.setField(twoImageSlideModel, "image2Alt", "image2Alt");
        PrivateAccessor.setField(twoImageSlideModel, "pullquote", "pullquote");
        PrivateAccessor.setField(twoImageSlideModel, "quotedesc", "quotedesc");

    }

    /**
     * Test update sling model.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testUpdateSlingModel() throws Exception {
        final String expectedString = "TwoImageSlideModel [caption=caption, pullquote=pullquote, quotedesc=quotedesc, images=[{image=Image [imgAlt=image1Alt, smallRendition=Rendition [regular=image1.jpg.image.256.340.low.jpg, retina=image1.jpg.image.513.681.low.jpg], mediumRendition=Rendition [regular=image1.jpg.image.348.464.medium.jpg, retina=image1.jpg.image.696.928.medium.jpg], largeRendition=Rendition [regular=image1.jpg.image.440.587.high.jpg, retina=image1.jpg.image.880.1174.high.jpg]]}, {image=Image [imgAlt=image2Alt, smallRendition=Rendition [regular=image2.jpg.image.256.340.low.jpg, retina=image2.jpg.image.513.681.low.jpg], mediumRendition=Rendition [regular=image2.jpg.image.168.224.medium.jpg, retina=image2.jpg.image.336.448.medium.jpg], largeRendition=Rendition [regular=image2.jpg.image.210.280.high.jpg, retina=image2.jpg.image.420.560.high.jpg]], pullquote=pullquote, quotedesc=quotedesc}]]";
        twoImageSlideServiceimpl.updateSlingModel(twoImageSlideModel);
        Assert.assertTrue(expectedString.equals(twoImageSlideModel.toString()));
    }

}

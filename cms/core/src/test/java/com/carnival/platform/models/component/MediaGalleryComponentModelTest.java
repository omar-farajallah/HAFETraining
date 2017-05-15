package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.GalleryDetailsDataModel;
import com.carnival.platform.models.data.GalleryTabDataModel;
import com.carnival.platform.models.data.MediaGalleryDataModel;
import com.carnival.platform.services.impl.MediaGalleryDataModelServiceImpl;

import junitx.util.PrivateAccessor;

/**
 * The Class MediaGalleryComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class MediaGalleryComponentModelTest extends AbstractComponentTest {

    /** The media gallery data model. */
    @InjectMocks
    private MediaGalleryDataModel mediaGalleryDataModel;

    /** The media gallery component model. */
    @InjectMocks
    private MediaGalleryComponentModel mediaGalleryComponentModel;

    /** The media details list. */
    List<Map<String, Object>> mediaDetailsList = null;

    /** The gallery tab data model. */
    @InjectMocks
    private GalleryTabDataModel galleryTabDataModel;

    /** The gallery details data model. */
    @InjectMocks
    private GalleryDetailsDataModel galleryDetailsDataModel;

    /** The gallery details data model. */
    @InjectMocks
    private GalleryDetailsDataModel galleryDetailsDataModel1;

    /** The media gallery data model service impl. */
    @InjectMocks
    private MediaGalleryDataModelServiceImpl mediaGalleryDataModelServiceImpl;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        PrivateAccessor.setField(galleryDetailsDataModel, "desktopImage", "desktop.jpg");
        PrivateAccessor.setField(galleryDetailsDataModel, "altText", "image alt text");
        PrivateAccessor.setField(galleryDetailsDataModel, "imageCaption", "imageCaption");
        PrivateAccessor.setField(galleryDetailsDataModel, "ctaLabel", "ctaLabel");
        PrivateAccessor.setField(galleryDetailsDataModel, "ctaLink", "ctaLink");
        PrivateAccessor.setField(galleryDetailsDataModel, "tilePosition", "bottom");
        PrivateAccessor.setField(galleryDetailsDataModel, "mediaSize", "8");
        PrivateAccessor.setField(galleryDetailsDataModel, "resource", resource);

        PrivateAccessor.setField(galleryDetailsDataModel1, "desktopImage", "desktop.jpg");
        PrivateAccessor.setField(galleryDetailsDataModel1, "altText", "image alt text");
        PrivateAccessor.setField(galleryDetailsDataModel1, "imageCaption", "imageCaption");
        PrivateAccessor.setField(galleryDetailsDataModel1, "ctaLabel", "ctaLabel");
        PrivateAccessor.setField(galleryDetailsDataModel1, "ctaLink", "ctaLink");
        PrivateAccessor.setField(galleryDetailsDataModel1, "tilePosition", "bottom");
        PrivateAccessor.setField(galleryDetailsDataModel1, "mediaSize", "8");
        PrivateAccessor.setField(galleryDetailsDataModel1, "resource", resource);

        List<GalleryDetailsDataModel> galleryDetailsDataModelList = new ArrayList<>();
        galleryDetailsDataModelList.add(galleryDetailsDataModel);
        galleryDetailsDataModelList.add(galleryDetailsDataModel1);
        PrivateAccessor.setField(galleryTabDataModel, "media", galleryDetailsDataModelList);
        PrivateAccessor.setField(galleryTabDataModel, "order", "1");
        PrivateAccessor.setField(galleryTabDataModel, "tileType", "full");

        List<GalleryTabDataModel> galleryTabDataModelList = new ArrayList<>();
        galleryTabDataModelList.add(galleryTabDataModel);
        PrivateAccessor.setField(mediaGalleryDataModel, "mediaRow", galleryTabDataModelList);
        PrivateAccessor.setField(mediaGalleryDataModel, "numberOfTabs", "1");
        mediaGalleryDataModelServiceImpl.updateModel(mediaGalleryDataModel);

        PrivateAccessor.setField(mediaGalleryComponentModel, "mediaGallery", mediaGalleryDataModel);
    }

    /**
     * Test component name.
     */
    @Test
    public void testComponentName() {
        assertTrue(MediaGalleryDataModel.COMPONENT_NAME.equals(mediaGalleryDataModel.getComponentName()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "MediaGalleryDataModel [mediaDetailsList=[GalleryTabDataModel [galleryDetailsModel=[GalleryDetailsDataModel [imageCaption=imageCaption, ctaLabel=ctaLabel, ctaLink=ctaLink, tilePosition=bottom, mediaSize=12, image=Image [imgAlt=image alt text, smallRendition=Rendition [regular=desktop.jpg.image.500.670.low.jpg, retina=desktop.jpg.image.1000.1340.low.jpg], mediumRendition=Rendition [regular=desktop.jpg.image.467.622.medium.jpg, retina=desktop.jpg.image.934.1244.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.1170.490.high.jpg, retina=desktop.jpg.image.2340.980.high.jpg]]], GalleryDetailsDataModel [imageCaption=imageCaption, ctaLabel=ctaLabel, ctaLink=ctaLink, tilePosition=bottom, mediaSize=8, image=null]]]]]";
        assertTrue(expectedString.equals(mediaGalleryComponentModel.getComponentData()[0].toString()));
    }

}

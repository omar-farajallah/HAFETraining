package com.carnival.platform.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.GalleryDetailsDataModel;
import com.carnival.platform.models.data.GalleryTabDataModel;
import com.carnival.platform.models.data.MediaGalleryDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Unit test Class for Media Gallery.
 * 
 * @author spati9
 */
@RunWith(MockitoJUnitRunner.class)
public class MediaGalleryServiceImplTest {

    /** The resource resolver. */
    @Mock
    private ResourceResolver resourceResolver;

    /** The resource. */
    @Mock
    private Resource resource;

    /** The carousel editorial data model. */
    @InjectMocks
    private MediaGalleryDataModel mediaGalleryDataModel;

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

    /** The expected string. */
    final String expectedString = "MediaGalleryDataModel [getMediaDetailsList()=[{2:1=[{image=Image [imgAlt=a, smallRendition=Rendition [regular=a.image.250.336.low.jpg, retina=a.image.500.672.low.jpg], mediumRendition=Rendition [regular=a.image.465.622.medium.jpg, retina=a.image.930.1244.medium.jpg], largeRendition=Rendition [regular=a.image.785.433.high.jpg, retina=a.image.1570.866.high.jpg]], imageCaption=a, tilePosition=bottom, ctaLink=/hal/en/test.html, ctaLabel=a}, {image=Image [imgAlt=a, smallRendition=Rendition [regular=a.image.250.336.low.jpg, retina=a.image.500.672.low.jpg], mediumRendition=Rendition [regular=a.image.325.433.medium.jpg, retina=a.image.650.866.medium.jpg], largeRendition=Rendition [regular=a.image.325.433.high.jpg, retina=a.image.650.866.high.jpg]], imageCaption=a, tilePosition=null, ctaLink=/hal/en/test1.html, ctaLabel=a}]}, {fullwidthimagetablet=, fullwidthimagemobile=, 1:2=[{image=Image [imgAlt=a, smallRendition=Rendition [regular=a.image.250.336.low.jpg, retina=a.image.500.672.low.jpg], mediumRendition=Rendition [regular=a.image.325.433.medium.jpg, retina=a.image.650.866.medium.jpg], largeRendition=Rendition [regular=a.image.325.433.high.jpg, retina=a.image.650.866.high.jpg]], imageCaption=a, tilePosition=bottom, ctaLink=/hal/en/test1.html, ctaLabel=a}, {image=Image [imgAlt=a, smallRendition=Rendition [regular=a.image.250.336.low.jpg, retina=a.image.500.672.low.jpg], mediumRendition=Rendition [regular=a.image.465.622.medium.jpg, retina=a.image.930.1244.medium.jpg], largeRendition=Rendition [regular=a.image.785.433.high.jpg, retina=a.image.1570.866.high.jpg]], imageCaption=a, tilePosition=bottom, ctaLink=/hal/en/test.html, ctaLabel=a}]}, {fullwidthimagetablet=b, fullwidthimagemobile=b, full=[{image=Image [imgAlt=b, smallRendition=Rendition [regular=b.image.325.433.low.jpg, retina=b.image.650.866.low.jpg], mediumRendition=Rendition [regular=b.image.325.433.medium.jpg, retina=b.image.650.866.medium.jpg], largeRendition=Rendition [regular=b.image.1130.433.high.jpg, retina=b.image.2260.866.high.jpg]], imageCaption=b, tilePosition=bottom, ctaLink=/hal/en/test2.html, ctaLabel=b}]}]]";

    /**
     * Test update sling model.
     *
     * @throws Exception
     *             the exception
     */

    @Before
    public void setUp() throws Exception {

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
        mediaGalleryDataModelServiceImpl.updateSlingModel(mediaGalleryDataModel);
    }

    /**
     * Test update sling model.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testUpdateSlingModel() throws Exception {
        final String expectedString = "MediaGalleryDataModel [mediaDetailsList=[GalleryTabDataModel [galleryDetailsModel=[GalleryDetailsDataModel [imageCaption=imageCaption, ctaLabel=ctaLabel, ctaLink=ctaLink, tilePosition=bottom, mediaSize=12, image=Image [imgAlt=image alt text, smallRendition=Rendition [regular=desktop.jpg.image.500.670.low.jpg, retina=desktop.jpg.image.1000.1340.low.jpg], mediumRendition=Rendition [regular=desktop.jpg.image.467.622.medium.jpg, retina=desktop.jpg.image.934.1244.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.1170.490.high.jpg, retina=desktop.jpg.image.2340.980.high.jpg]]], GalleryDetailsDataModel [imageCaption=imageCaption, ctaLabel=ctaLabel, ctaLink=ctaLink, tilePosition=bottom, mediaSize=8, image=null]]]]]";
        Assert.assertEquals(mediaGalleryDataModel.toString(), expectedString);

    }

    /**
     * Test media size.
     *
     * @throws Exception
     *             the exception
     */

    @Test
    public void testMediaSize() throws Exception {
        PrivateAccessor.setField(galleryTabDataModel, "tileType", "1:2");
        final String expectedStringFor1Width = "MediaGalleryDataModel [mediaDetailsList=[GalleryTabDataModel [galleryDetailsModel=[GalleryDetailsDataModel [imageCaption=imageCaption, ctaLabel=ctaLabel, ctaLink=ctaLink, tilePosition=bottom, mediaSize=4, image=Image [imgAlt=image alt text, smallRendition=Rendition [regular=desktop.jpg.image.500.670.low.jpg, retina=desktop.jpg.image.1000.1340.low.jpg], mediumRendition=Rendition [regular=desktop.jpg.image.467.622.medium.jpg, retina=desktop.jpg.image.934.1244.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.467.490.high.jpg, retina=desktop.jpg.image.934.980.high.jpg]]], GalleryDetailsDataModel [imageCaption=imageCaption, ctaLabel=ctaLabel, ctaLink=ctaLink, tilePosition=bottom, mediaSize=8, image=Image [imgAlt=image alt text, smallRendition=Rendition [regular=desktop.jpg.image.500.670.low.jpg, retina=desktop.jpg.image.1000.1340.low.jpg], mediumRendition=Rendition [regular=desktop.jpg.image.467.622.medium.jpg, retina=desktop.jpg.image.934.1244.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.763.490.high.jpg, retina=desktop.jpg.image.1526.980.high.jpg]]]]]]]";
        mediaGalleryDataModelServiceImpl.updateSlingModel(mediaGalleryDataModel);
        Assert.assertEquals(mediaGalleryDataModel.toString(), expectedStringFor1Width);

        PrivateAccessor.setField(galleryTabDataModel, "tileType", "2:1");
        final String expectedString = "MediaGalleryDataModel [mediaDetailsList=[GalleryTabDataModel [galleryDetailsModel=[GalleryDetailsDataModel [imageCaption=imageCaption, ctaLabel=ctaLabel, ctaLink=ctaLink, tilePosition=bottom, mediaSize=8, image=Image [imgAlt=image alt text, smallRendition=Rendition [regular=desktop.jpg.image.500.670.low.jpg, retina=desktop.jpg.image.1000.1340.low.jpg], mediumRendition=Rendition [regular=desktop.jpg.image.467.622.medium.jpg, retina=desktop.jpg.image.934.1244.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.763.490.high.jpg, retina=desktop.jpg.image.1526.980.high.jpg]]], GalleryDetailsDataModel [imageCaption=imageCaption, ctaLabel=ctaLabel, ctaLink=ctaLink, tilePosition=bottom, mediaSize=4, image=Image [imgAlt=image alt text, smallRendition=Rendition [regular=desktop.jpg.image.500.670.low.jpg, retina=desktop.jpg.image.1000.1340.low.jpg], mediumRendition=Rendition [regular=desktop.jpg.image.467.622.medium.jpg, retina=desktop.jpg.image.934.1244.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.467.490.high.jpg, retina=desktop.jpg.image.934.980.high.jpg]]]]]]]";
        mediaGalleryDataModelServiceImpl.updateSlingModel(mediaGalleryDataModel);
        Assert.assertEquals(mediaGalleryDataModel.toString(), expectedString);
    }

}

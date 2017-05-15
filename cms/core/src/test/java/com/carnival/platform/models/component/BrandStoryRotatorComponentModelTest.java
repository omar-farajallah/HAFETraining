package com.carnival.platform.models.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.BrandStoryDataModel;
import com.carnival.platform.models.data.BrandStoryRotatorDataModel;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import junitx.util.PrivateAccessor;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandStoryRotatorComponentModelTest.
 * 
 * @author agupta
 */
@RunWith(MockitoJUnitRunner.class)
public class BrandStoryRotatorComponentModelTest extends AbstractComponentTest {

    /** The brand story rotator data model. */
    @InjectMocks
    BrandStoryRotatorDataModel brandStoryRotatorDataModel;

    /** The story model. */
    @InjectMocks
    private BrandStoryDataModel brandStoryModel;

    /** The brand story rotator component model. */
    @InjectMocks
    private BrandStoryRotatorComponentModel brandStoryRotatorComponentModel;

    /** The parent page 1. */
    @Mock
    private Page parentPage1;

    /** The parent page 2. */
    @Mock
    private Page parentPage2;

    /**
     * Setup.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        List<BrandStoryDataModel> storyModelList = new ArrayList<>();
        storyModelList.add(brandStoryModel);

        PrivateAccessor.setField(brandStoryModel, "tileType", "fullwidth");
        PrivateAccessor.setField(brandStoryModel, "desktopImageURL", "desktop.jpg");
        PrivateAccessor.setField(brandStoryModel, "mobileImageURL", "mobile.jpg");
        PrivateAccessor.setField(brandStoryModel, "tabletImageURL", "tablet.jpg");
        PrivateAccessor.setField(brandStoryModel, "infoCardAlignment", "left");
        PrivateAccessor.setField(brandStoryModel, "title", "title");
        PrivateAccessor.setField(brandStoryModel, "description", "description");
        PrivateAccessor.setField(brandStoryModel, "alt", "alt");
        PrivateAccessor.setField(brandStoryModel, "ctaURL", "/content/carnival/hal/en/test#1");
        PrivateAccessor.setField(brandStoryModel, "ctaLabel", "Label");

        PrivateAccessor.setField(brandStoryRotatorDataModel, "fullTileTexture", "/content/dam/carnival/marble.jpg");
        PrivateAccessor.setField(brandStoryRotatorDataModel, "halfTileTexture", "/content/dam/carnival/marble.jpg");
        PrivateAccessor.setField(brandStoryRotatorDataModel, "storyList", storyModelList);

        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        PrivateAccessor.setField(brandStoryModel, "resource", resource);
        when(brandStoryModel.getResourceResolver()).thenReturn(resourceResolver);

        PrivateAccessor.setField(brandStoryRotatorDataModel, "resource", resource);
        when(brandStoryRotatorDataModel.getResourceResolver()).thenReturn(resourceResolver);

        when(resourceResolver.map("")).thenReturn("");
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageMang);
        when(resource.getPath()).thenReturn("/content/carnival/hal/en/test#1");
        when(pageMang.getContainingPage("/content/carnival/hal/en/test#1")).thenReturn(page);
        when(page.getAbsoluteParent(1)).thenReturn(parentPage1);
        when(page.getAbsoluteParent(2)).thenReturn(parentPage2);

        PrivateAccessor.setField(brandStoryRotatorComponentModel, "brandStoryRotator", brandStoryRotatorDataModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "BrandStoryRotatorComponentModel [brandStoryRotator=[StoryRotatorDataModel [fullTileTexture=/content/dam/carnival/marble.jpg, halfTileTexture=/content/dam/carnival/marble.jpg, storyList=[BrandStoryModel [tileType=fullwidth, title=title, desc=description, ctaURL=null, ctaLabel=Label, infoCardAlignment=left, image=Image [imgAlt=alt, smallRendition=Rendition [regular=mobile.jpg.image.320.240.low.jpg, retina=mobile.jpg.image.640.480.low.jpg], mediumRendition=Rendition [regular=tablet.jpg.image.768.576.medium.jpg, retina=tablet.jpg.image.1536.1152.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.1170.492.high.jpg, retina=desktop.jpg.image.2340.984.high.jpg]]]]]]]";
        assertEquals(expectedString, brandStoryRotatorComponentModel.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() {
        assertTrue(BrandStoryRotatorDataModel.COMPONENT_NAME.equals(brandStoryRotatorDataModel.getComponentName()));
    }
}

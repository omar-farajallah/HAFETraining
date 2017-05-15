package com.carnival.platform.models.component;

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

import com.carnival.platform.models.data.StoryDataModel;
import com.carnival.platform.models.data.StoryRotatorDataModel;
import com.carnival.platform.services.LabelConfigurationService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import junitx.util.PrivateAccessor;

/**
 * The Class StoryRotatorComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class StoryRotatorComponentModelTest extends AbstractComponentTest {

    /** The brand story rotator data model. */
    @InjectMocks
    StoryRotatorDataModel storyRotatorDataModel;

    /** The story model. */
    @InjectMocks
    private StoryDataModel storyModel;

    /** The label configuration service impl. */
    @Mock
    private LabelConfigurationService labelConfigurationService;

    /** The page mang. */
    @Mock
    private PageManager pageMang;

    /** The page. */
    @Mock
    private Page page;

    /** The parent page 1. */
    @Mock
    private Page parentPage1;

    /** The parent page 2. */
    @Mock
    private Page parentPage2;

    /** The story rotator component model. */
    @InjectMocks
    private StoryRotatorComponentModel storyRotatorComponentModel;

    /**
     * Setup.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        List<StoryDataModel> storyModelList = new ArrayList<>();
        storyModelList.add(storyModel);

        PrivateAccessor.setField(storyModel, "desktopImageURL", "desktop.jpg");
        PrivateAccessor.setField(storyModel, "mobileImageURL", "mobile.jpg");
        PrivateAccessor.setField(storyModel, "tabletImageURL", "tablet.jpg");
        PrivateAccessor.setField(storyModel, "title", "title");
        PrivateAccessor.setField(storyModel, "desc", "desc");
        PrivateAccessor.setField(storyModel, "alt", "alt");
        PrivateAccessor.setField(storyModel, "ctaURL", "/content/carnival/hal/en/test#1");
        PrivateAccessor.setField(storyModel, "ctaLabel", "Label");

        PrivateAccessor.setField(storyRotatorDataModel, "infoCardAlignment", "Buttom Right");
        PrivateAccessor.setField(storyRotatorDataModel, "showTexture", "Yes");
        PrivateAccessor.setField(storyRotatorDataModel, "texture", "Marble");
        PrivateAccessor.setField(storyRotatorDataModel, "storyList", storyModelList);
        PrivateAccessor.setField(storyRotatorDataModel, "labelConfigurationService", labelConfigurationService);
        PrivateAccessor.setField(storyModel, "resource", resource);

        when(storyModel.getResourceResolver()).thenReturn(resourceResolver);

        PrivateAccessor.setField(storyRotatorDataModel, "resource", resource);
        when(storyRotatorDataModel.getResourceResolver()).thenReturn(resourceResolver);
        PrivateAccessor.setField(storyRotatorComponentModel, "storyRotator", storyRotatorDataModel);

        when(resourceResolver.map("")).thenReturn("");
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageMang);
        when(resource.getPath()).thenReturn("/content/carnival/hal/en/test#1");
        when(pageMang.getContainingPage("/content/carnival/hal/en/test#1")).thenReturn(page);
        when(page.getAbsoluteParent(1)).thenReturn(parentPage1);
        when(page.getAbsoluteParent(2)).thenReturn(parentPage2);

        when(parentPage2.getName()).thenReturn("en");
        when(parentPage1.getName()).thenReturn("platform");

        when(storyRotatorDataModel.getLocaleName()).thenReturn("en");
        when(storyRotatorDataModel.getBrandName()).thenReturn("platform");

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "StoryRotatorComponentModel [storyRotator=[StoryRotatorDataModel [dividertext=of, infoCardAlignment=Buttom Right, showTexture=Yes, texture=Marble, storyList=[StoryModel [title=title, desc=desc, ctaURL=null, ctaLabel=Label, image=Image [imgAlt=alt, smallRendition=Rendition [regular=mobile.jpg.image.320.240.low.jpg, retina=mobile.jpg.image.640.480.low.jpg], mediumRendition=Rendition [regular=tablet.jpg.image.768.364.medium.jpg, retina=tablet.jpg.image.1536.728.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.1480.538.high.jpg, retina=desktop.jpg.image.2960.1076.high.jpg]]]]]]]";
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");

        assertTrue(expectedString.equals(storyRotatorComponentModel.toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() {
        assertTrue(StoryRotatorDataModel.COMPONENT_NAME.equals(storyRotatorDataModel.getComponentName()));
    }
}

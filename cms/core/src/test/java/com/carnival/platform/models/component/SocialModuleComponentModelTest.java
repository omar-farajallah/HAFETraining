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

import com.carnival.platform.models.data.SocialModuleDataModel;
import com.carnival.platform.models.data.SocialModuleMediaDataModel;
import com.carnival.platform.models.data.SocialModuleTileDataModel;
import com.carnival.platform.services.impl.LabelConfigurationServiceImpl;
import com.day.cq.wcm.api.Page;

import junitx.util.PrivateAccessor;

/**
 * The Class SocialModuleComponentModelTest.
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SocialModuleComponentModelTest extends AbstractComponentTest {

    /** The social module data model. */
    @InjectMocks
    private SocialModuleDataModel socialModuleDataModel;

    /** The component model. */
    @InjectMocks
    private SocialModuleComponentModel componentModel;

    /** The social module media data model. */
    @InjectMocks
    private SocialModuleMediaDataModel socialModuleMediaDataModel;

    /** The social module tile data model. */
    @InjectMocks
    private SocialModuleTileDataModel socialModuleTileDataModel;

    /** The label configuration service impl. */
    @Mock
    private LabelConfigurationServiceImpl labelConfigurationServiceImpl;

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

        PrivateAccessor.setField(socialModuleMediaDataModel, "socialIcon", "icon.svg");
        PrivateAccessor.setField(socialModuleMediaDataModel, "socialIconAlt", "icon alt");
        PrivateAccessor.setField(socialModuleMediaDataModel, "socialUrl", "/content/carnival/home");
        PrivateAccessor.setField(socialModuleMediaDataModel, "resource", resource);

        List<SocialModuleMediaDataModel> socialMediaList = new ArrayList<>();
        socialMediaList.add(socialModuleMediaDataModel);

        PrivateAccessor.setField(socialModuleTileDataModel, "postImage", "image.jpg");
        PrivateAccessor.setField(socialModuleTileDataModel, "imageAltText", "image alt");
        PrivateAccessor.setField(socialModuleTileDataModel, "postContent", "postContent");
        PrivateAccessor.setField(socialModuleTileDataModel, "postImage", "image");
        PrivateAccessor.setField(socialModuleTileDataModel, "postDate", "Mar 27");
        PrivateAccessor.setField(socialModuleTileDataModel, "postIcon", "icon2.svg");
        PrivateAccessor.setField(socialModuleTileDataModel, "postIconAlt", "icon2 alt");
        PrivateAccessor.setField(socialModuleTileDataModel, "postTitle", "title");
        PrivateAccessor.setField(socialModuleTileDataModel, "postImage", "image");
        PrivateAccessor.setField(socialModuleTileDataModel, "postImage", "image");
        List<SocialModuleTileDataModel> tileList = new ArrayList<>();
        tileList.add(socialModuleTileDataModel);

        PrivateAccessor.setField(socialModuleDataModel, "followUsText", "Follow Us");
        PrivateAccessor.setField(socialModuleDataModel, "socialMediaList", socialMediaList);
        PrivateAccessor.setField(socialModuleDataModel, "tileList", tileList);
        PrivateAccessor.setField(componentModel, "socialModule", socialModuleDataModel);
        PrivateAccessor.setField(socialModuleDataModel, "labelConfigurationService", labelConfigurationService);
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");

        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        PrivateAccessor.setField(socialModuleDataModel, "resource", resource);
        when(socialModuleDataModel.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.map("/content/carnival/home")).thenReturn("/home.html");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "SocialModuleComponentModel [ComponentName=socialModule, ComponentData=[SocialModuleDataModel [followUsText=Follow Us, socialMediaList=[SocialModuleMediaDataModel [socialIcon=icon.svg, socialIconAlt=icon alt, socialUrl=/home.html]], tileList=[SocialModuleTileDataModel [postContent=postContent, postDate=Mar 27, postIcon=icon2.svg, postIconAlt=icon2 alt, postTitle=title, image=Image [imgAlt=image alt, smallRendition=Rendition [regular=image.image.251.251.low.jpg, retina=image.image.502.502.low.jpg], mediumRendition=Rendition [regular=image.image.348.348.medium.jpg, retina=image.image.696.696.medium.jpg], largeRendition=Rendition [regular=image.image.530.530.high.jpg, retina=image.image.1060.1060.high.jpg]]]], dividerText=of]]]";
        assertTrue(expectedString.equals(componentModel.toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() {
        assertTrue(SocialModuleDataModel.COMPONENT_NAME.equals(socialModuleDataModel.getComponentName()));
    }
}

package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.HeroDataModel;
import com.carnival.platform.models.data.HeroListDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class HeroComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class HeroComponentModelTest extends AbstractComponentTest {

    /** The input model. */
    @InjectMocks
    private HeroDataModel inputModel;

    /** The component model. */
    @InjectMocks
    private HeroComponentModel componentModel;

    /** The hero data model. */
    @InjectMocks
    private HeroListDataModel heroDataModel;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        PrivateAccessor.setField(heroDataModel, "alt", "alt");
        PrivateAccessor.setField(heroDataModel, "ctaLabel", "ctaLabel");
        PrivateAccessor.setField(heroDataModel, "ctaURL", "/content/carnival/platform/en.html");
        PrivateAccessor.setField(heroDataModel, "desc", "desc");
        PrivateAccessor.setField(heroDataModel, "desktopImageURL", "desktop.jpg");
        PrivateAccessor.setField(heroDataModel, "mobileImageURL", "mobile.jpg");
        PrivateAccessor.setField(heroDataModel, "tabletImageURL", "tablet.jpg");
        PrivateAccessor.setField(heroDataModel, "heroCardAlignment", "heroCardAlignment");
        PrivateAccessor.setField(heroDataModel, "heroCardIcon", "heroCardIcon");
        PrivateAccessor.setField(heroDataModel, "title", "title");

        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        PrivateAccessor.setField(heroDataModel, "resource", resource);
        when(heroDataModel.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.map("")).thenReturn("");

        List<HeroListDataModel> heroModelList = new ArrayList<>();
        heroModelList.add(heroDataModel);

        PrivateAccessor.setField(inputModel, "resource", resource);
        PrivateAccessor.setField(inputModel, "recentlyviewtext", "Recently viewed");
        PrivateAccessor.setField(inputModel, "heroContentList", heroModelList);

        PrivateAccessor.setField(componentModel, "heroData", inputModel);

        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.map("")).thenReturn("");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {
        final String expectedString = "HeroComponentModel [ComponentName=hero, ComponentData=[HeroDataModel [recentlyviewtext=Recently viewed, heroContentList=[HeroListDataModel [ctaLabel=ctaLabel, ctaURL=null, desc=desc, heroCardAlignment=heroCardAlignment, heroCardIcon=false, title=title, image=Image [imgAlt=alt, smallRendition=Rendition [regular=mobile.jpg.image.160.119.low.jpg, retina=mobile.jpg.image.320.238.low.jpg], mediumRendition=Rendition [regular=tablet.jpg.image.384.245.medium.jpg, retina=tablet.jpg.image.768.490.medium.jpg], largeRendition=Rendition [regular=desktop.jpg.image.1440.570.high.jpg, retina=desktop.jpg.image.2880.1140.high.jpg]]]], enableSearchBar=false]]]";
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
        assertTrue(HeroDataModel.COMPONENT_NAME.equals(inputModel.getComponentName()));

    }

}

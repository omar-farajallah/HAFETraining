/**
 * 
 */
package com.carnival.platform.services.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.GlobalHeaderDataModel;
import com.carnival.platform.models.data.ImageDropdownDataModel;
import com.carnival.platform.models.data.NavItemDataModel;
import com.carnival.platform.models.data.SecondaryListDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class HeroTileDataServiceImplTest.
 *
 * @author vaddur
 */
@RunWith(MockitoJUnitRunner.class)
public class GlobalHeaderDataServiceImplTest {

    /** The hero tile service impl. */
    @InjectMocks
    private GlobalHeaderDataServiceImpl globalHeaderDataServiceImpl;

    /** The sling model. */
    private GlobalHeaderDataModel slingModel;

    /** The nav item data model. */
    private NavItemDataModel navItemDataModel;

    private SecondaryListDataModel secondaryListDataModel;

    private ImageDropdownDataModel imageDropdownDataModel;

    /** The resource resolver. */
    @Mock
    private ResourceResolver resourceResolver;

    /** The resource. */
    @Mock
    private Resource resource;

    /**
     * Test update sling model.
     *
     * @throws Exception
     *             the exception
     */

    @Before
    public void setUp() throws Exception {

        slingModel = new GlobalHeaderDataModel();
        navItemDataModel = new NavItemDataModel();
        imageDropdownDataModel = new ImageDropdownDataModel();
        secondaryListDataModel = new SecondaryListDataModel();

        PrivateAccessor.setField(imageDropdownDataModel, "dropdownImageAlt", "image alt");
        PrivateAccessor.setField(imageDropdownDataModel, "imageLink", "/content/dam/carnival/en.jpg");
        PrivateAccessor.setField(imageDropdownDataModel, "target", "/content/carnival/en");
        PrivateAccessor.setField(imageDropdownDataModel, "titleOverlayText", "overlayText");
        PrivateAccessor.setField(imageDropdownDataModel, "resource", resource);
        List<ImageDropdownDataModel> imageDropdownList = new ArrayList<>();
        imageDropdownList.add(imageDropdownDataModel);

        PrivateAccessor.setField(secondaryListDataModel, "secondaryLinkText", "Secondary Link Text");
        PrivateAccessor.setField(secondaryListDataModel, "secondaryLinkTarget", "/content/carnival/platform/en_us.html");
        PrivateAccessor.setField(secondaryListDataModel, "resource", resource);
        List<SecondaryListDataModel> secondaryLinkList = new ArrayList<>();
        secondaryLinkList.add(secondaryListDataModel);

        List<NavItemDataModel> navigationItems = new ArrayList<>();
        navigationItems.add(navItemDataModel);
        PrivateAccessor.setField(navItemDataModel, "globalNavigationTitle", "Title");
        PrivateAccessor.setField(navItemDataModel, "navigationType", "direct");
        PrivateAccessor.setField(navItemDataModel, "directLinkTarget", "/content/carnival/platform/en_us.html");
        PrivateAccessor.setField(navItemDataModel, "resource", resource);
        PrivateAccessor.setField(navItemDataModel, "imageDropdownList", imageDropdownList);
        PrivateAccessor.setField(navItemDataModel, "secondaryLinkList", secondaryLinkList);

        PrivateAccessor.setField(slingModel, "logo", "/content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(slingModel, "logoAlt", "Alt");
        PrivateAccessor.setField(slingModel, "logoTarget", "/content/carnival/hal/en_us/home");
        PrivateAccessor.setField(slingModel, "menuText", "MENU");
        PrivateAccessor.setField(slingModel, "closeButtonText", "CLOSE");
        PrivateAccessor.setField(slingModel, "phoneNumber", "12345");
        PrivateAccessor.setField(slingModel, "utilityNavRequired", "true");
        PrivateAccessor.setField(slingModel, "numberOfItems", "2");
        PrivateAccessor.setField(slingModel, "navigationItems", navigationItems);
        PrivateAccessor.setField(slingModel, "resource", resource);

        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.map("/content/carnival/hal/en_us/home")).thenReturn("/hal/en.html");

    }

    /**
     * Test update sling model.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Test
    public void testUpdateSlingModel() throws NoSuchFieldException {
        final String expectedString = "GlobalHeaderDataModel [logo=/content/dam/carnival/hal/test.jpg, logoAlt=Alt, logoTarget=/hal/en.html, menuText=MENU, closeButtonText=CLOSE, phoneNumber=12345, utilityNavRequired=true, numberOfItems=2, navigationItems=[NavItemDataModel [globalNavigationTitle=Title, navigationType=direct, directLinkTarget=null, secondaryLinkList=[SecondaryListDataModel [secondaryLinkTarget=null, secondaryLinkText=Secondary Link Text]], imageDropdownList=[ImageDropdownDataModel [target=null, titleOverlayText=overlayText, image=Image [imgAlt=image alt, smallRendition=Rendition [regular=/content/dam/carnival/en.jpg.image.286.193.low.jpg, retina=/content/dam/carnival/en.jpg.image.572.386.low.jpg], mediumRendition=Rendition [regular=/content/dam/carnival/en.jpg.image.348.233.medium.jpg, retina=/content/dam/carnival/en.jpg.image.696.466.medium.jpg], largeRendition=Rendition [regular=/content/dam/carnival/en.jpg.image.288.193.high.jpg, retina=/content/dam/carnival/en.jpg.image.576.386.high.jpg]]]]]]]";
        globalHeaderDataServiceImpl.updateSlingModel(slingModel);
        assertTrue(expectedString.equals(slingModel.toString()));

    }
}

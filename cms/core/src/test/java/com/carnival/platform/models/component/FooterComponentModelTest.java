package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.FooterDataModel;
import com.carnival.platform.models.data.FooterSecondaryLogoDataModel;
import com.carnival.platform.models.data.FooterSocialIconDataModel;
import com.carnival.platform.models.data.FooterSublinkDataModel;
import com.carnival.platform.models.data.FooterTextLinkDataModel;
import com.carnival.platform.models.data.FooterTitleSublinksDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class FooterComponentModelTest.
 *
 * @author smedur
 */
@RunWith(MockitoJUnitRunner.class)
public class FooterComponentModelTest extends AbstractComponentTest {

    /* Data model which is tested in this test case */
    private FooterDataModel footerDataModel = new FooterDataModel();
    
    /* Component model which is tested in this test case */
    private FooterComponentModel footerComponentModel = new FooterComponentModel();
    
    /* Data models which have composition relation with FooterDataModel */
    FooterSublinkDataModel subLinkDataModel = new FooterSublinkDataModel();
    FooterTitleSublinksDataModel titleSublinksDataModel = new FooterTitleSublinksDataModel();
    FooterTextLinkDataModel textLinkDataModel = new FooterTextLinkDataModel();
    FooterSocialIconDataModel socialIconDataModel = new FooterSocialIconDataModel();
    FooterSecondaryLogoDataModel secondaryLogoDataModel = new FooterSecondaryLogoDataModel();

    /** Constant .html */
    private static final String DOT_HTML = ".html";
    
    @Before
    public void setUp() throws Exception {

        super.setUp();
        
        /* Setting fields of General tab */
        setUpGeneralTabFields();
        
        /* Setting up FooterTitleSublinksDataModel List */
        List<FooterTitleSublinksDataModel> titleSublinksDataModelList = buildTitleSublinksDataModelList();
        PrivateAccessor.setField(footerDataModel, "topNavList", titleSublinksDataModelList);

        /* Text links setup */
        List<FooterTextLinkDataModel> textLinkList = buildTextLinksDMList();
        PrivateAccessor.setField(footerDataModel, "subNavList", textLinkList);

        /* Social Icon List Setup */
        List<FooterSocialIconDataModel> socialIconDMList = buildSocialIconList();
        PrivateAccessor.setField(footerDataModel, "socialList", socialIconDMList);

        /* Secondary logo List Setup */
        List<FooterSecondaryLogoDataModel> secondaryLogoDMList = buildSecondaryLogoDMList();
        PrivateAccessor.setField(footerDataModel, "secondaryLogoList", secondaryLogoDMList);

        PrivateAccessor.setField(footerComponentModel, "footer", footerDataModel);
    }

    @Test
    public void testComponentName() {
        assertTrue(FooterDataModel.COMPONENT_NAME.equals(footerComponentModel.getComponentName()));
    }
    
    @Override
    @Test
    public void testComponentModelData() throws NoSuchFieldException {
        String expectedStringOutput = "FooterComponentModel [ComponentData=[FooterDataModel [brandLogo=Name, brandLogoAltText=Alt, brandLogoURL=/content/carnival/hal/en_us/home.html, numberOfTopNavItems=3, topNavList=[FooterTitleSublinksDataModel [title=Title 1, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_11, url=/content/data/fe/sublinkURL_11.html], FooterSublinkDataModel [label=sublinkLabel_12, url=/content/data/fe/sublinkURL_12.html]]], FooterTitleSublinksDataModel [title=Title 2, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_21, url=/content/data/fe/sublinkURL_21.html], FooterSublinkDataModel [label=sublinkLabel_22, url=http://www.abc.com/d.html]]], FooterTitleSublinksDataModel [title=Title 3, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_31, url=http://www.abc.com/b/c/d.html], FooterSublinkDataModel [label=sublinkLabel_32, url=/content/data/fe/sublinkURL_32.html]]]], subNavList=[FooterTextLinkDataModel [label=textLinkLabel_1, url=/content/data/fe/textLinkURL_1.html], FooterTextLinkDataModel [label=textLinkLabel_2, url=http://www.externalURL.com/content/data/fe/textLinkURL_2]], socialList=[FooterSocialIconDataModel [icon=social icon 1, url=http://www.xyz.com], FooterSocialIconDataModel [icon=social icon 2, url=http://www.zyx.com/content/page/dam]], secondaryLogoList=[FooterSecondaryLogoDataModel [logo=/content/dam/secondaryLogos/image1.png, altText=image1, url=/content/data/fe/secondaryLogoURL_1.html], FooterSecondaryLogoDataModel [logo=/content/dam/secondaryLogos/image2.png, altText=image2, url=http://www.abc.com/dam/x/d.php]]]]]";
        assertTrue(expectedStringOutput.equals(footerComponentModel.toString()));
    }

    @Test
    public void testTitleSublinksListWithTwoItems() throws NoSuchFieldException {
        String expectedStringOutput = "FooterComponentModel [ComponentData=[FooterDataModel [brandLogo=Name, brandLogoAltText=Alt, brandLogoURL=/content/carnival/hal/en_us/home.html, numberOfTopNavItems=2, topNavList=[FooterTitleSublinksDataModel [title=Title 1, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_11, url=/content/data/fe/sublinkURL_11.html], FooterSublinkDataModel [label=sublinkLabel_12, url=/content/data/fe/sublinkURL_12.html]]], FooterTitleSublinksDataModel [title=Title 2, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_21, url=/content/data/fe/sublinkURL_21.html], FooterSublinkDataModel [label=sublinkLabel_22, url=http://www.abc.com/d.html]]]], subNavList=[FooterTextLinkDataModel [label=textLinkLabel_1, url=/content/data/fe/textLinkURL_1.html], FooterTextLinkDataModel [label=textLinkLabel_2, url=http://www.externalURL.com/content/data/fe/textLinkURL_2]], socialList=[FooterSocialIconDataModel [icon=social icon 1, url=http://www.xyz.com], FooterSocialIconDataModel [icon=social icon 2, url=http://www.zyx.com/content/page/dam]], secondaryLogoList=[FooterSecondaryLogoDataModel [logo=/content/dam/secondaryLogos/image1.png, altText=image1, url=/content/data/fe/secondaryLogoURL_1.html], FooterSecondaryLogoDataModel [logo=/content/dam/secondaryLogos/image2.png, altText=image2, url=http://www.abc.com/dam/x/d.php]]]]]";
        PrivateAccessor.setField(footerDataModel, "numberOfTopNavItems", "2");
        assertTrue(expectedStringOutput.equals(footerComponentModel.toString()));
    }

    @Test
    public void testTitleSublinksListWithThreeItems() throws NoSuchFieldException {
        String expectedStringOutput = "FooterComponentModel [ComponentData=[FooterDataModel [brandLogo=Name, brandLogoAltText=Alt, brandLogoURL=/content/carnival/hal/en_us/home.html, numberOfTopNavItems=3, topNavList=[FooterTitleSublinksDataModel [title=Title 1, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_11, url=/content/data/fe/sublinkURL_11.html], FooterSublinkDataModel [label=sublinkLabel_12, url=/content/data/fe/sublinkURL_12.html]]], FooterTitleSublinksDataModel [title=Title 2, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_21, url=/content/data/fe/sublinkURL_21.html], FooterSublinkDataModel [label=sublinkLabel_22, url=http://www.abc.com/d.html]]], FooterTitleSublinksDataModel [title=Title 3, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_31, url=http://www.abc.com/b/c/d.html], FooterSublinkDataModel [label=sublinkLabel_32, url=/content/data/fe/sublinkURL_32.html]]]], subNavList=[FooterTextLinkDataModel [label=textLinkLabel_1, url=/content/data/fe/textLinkURL_1.html], FooterTextLinkDataModel [label=textLinkLabel_2, url=http://www.externalURL.com/content/data/fe/textLinkURL_2]], socialList=[FooterSocialIconDataModel [icon=social icon 1, url=http://www.xyz.com], FooterSocialIconDataModel [icon=social icon 2, url=http://www.zyx.com/content/page/dam]], secondaryLogoList=[FooterSecondaryLogoDataModel [logo=/content/dam/secondaryLogos/image1.png, altText=image1, url=/content/data/fe/secondaryLogoURL_1.html], FooterSecondaryLogoDataModel [logo=/content/dam/secondaryLogos/image2.png, altText=image2, url=http://www.abc.com/dam/x/d.php]]]]]";
        PrivateAccessor.setField(footerDataModel, "numberOfTopNavItems", "3");
        assertTrue(expectedStringOutput.equals(footerComponentModel.toString()));
    }

    @Test
    public void testExternalBrandLogoURL() throws NoSuchFieldException {
        String brandLogoURL = "http://www.testExternal.com/content/carnival/hal/en_us/home";
        PrivateAccessor.setField(footerDataModel, "brandLogoURL", brandLogoURL);
        
        //External url is expected to be not modified
        assertTrue(brandLogoURL.equals(footerDataModel.getBrandLogoURL()));
    }

    /* Tests when socialIconDataModel received null values */
    @Test
    public void testSocialIconNotAuthored() throws NoSuchFieldException {
        FooterSocialIconDataModel socialIconDataModel = new FooterSocialIconDataModel();
        List<FooterSocialIconDataModel> socialIconDMList = new ArrayList<>();
        
        PrivateAccessor.setField(socialIconDataModel, "icon", "facebook");
        PrivateAccessor.setField(socialIconDataModel, "url", null);
        PrivateAccessor.setField(socialIconDataModel, "resource", resource);
        socialIconDMList.add(socialIconDataModel);

        PrivateAccessor.setField(footerDataModel, "socialList", socialIconDMList);
        //System.out.println(footerComponentModel.toString());

        String expectedStringOutput = "FooterComponentModel [ComponentData=[FooterDataModel [brandLogo=Name, brandLogoAltText=Alt, brandLogoURL=/content/carnival/hal/en_us/home.html, numberOfTopNavItems=3, topNavList=[FooterTitleSublinksDataModel [title=Title 1, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_11, url=/content/data/fe/sublinkURL_11.html], FooterSublinkDataModel [label=sublinkLabel_12, url=/content/data/fe/sublinkURL_12.html]]], FooterTitleSublinksDataModel [title=Title 2, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_21, url=/content/data/fe/sublinkURL_21.html], FooterSublinkDataModel [label=sublinkLabel_22, url=http://www.abc.com/d.html]]], FooterTitleSublinksDataModel [title=Title 3, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_31, url=http://www.abc.com/b/c/d.html], FooterSublinkDataModel [label=sublinkLabel_32, url=/content/data/fe/sublinkURL_32.html]]]], subNavList=[FooterTextLinkDataModel [label=textLinkLabel_1, url=/content/data/fe/textLinkURL_1.html], FooterTextLinkDataModel [label=textLinkLabel_2, url=http://www.externalURL.com/content/data/fe/textLinkURL_2]], socialList=[FooterSocialIconDataModel [icon=facebook, url=null]], secondaryLogoList=[FooterSecondaryLogoDataModel [logo=/content/dam/secondaryLogos/image1.png, altText=image1, url=/content/data/fe/secondaryLogoURL_1.html], FooterSecondaryLogoDataModel [logo=/content/dam/secondaryLogos/image2.png, altText=image2, url=http://www.abc.com/dam/x/d.php]]]]]";
        assertTrue(expectedStringOutput.equals(footerComponentModel.toString()));
    }
    
    /* Tests when footerSecondaryLogoDataModel received null values */
    @Test
    public void testSecondaryLogoNotAuthored() throws NoSuchFieldException {
        List<FooterSecondaryLogoDataModel> secondaryLogoList = new ArrayList<>();
        
        /* Build individual FooterSocialIconDataModel, add to return param. */
        secondaryLogoDataModel = new FooterSecondaryLogoDataModel();
        
        PrivateAccessor.setField(secondaryLogoDataModel, "logo", null);
        PrivateAccessor.setField(secondaryLogoDataModel, "altText", null);
        PrivateAccessor.setField(secondaryLogoDataModel, "url", null);
        PrivateAccessor.setField(secondaryLogoDataModel, "resource", resource);
        secondaryLogoList.add(secondaryLogoDataModel);
        
        PrivateAccessor.setField(footerDataModel, "secondaryLogoList", secondaryLogoList);
        
        String expectedStringOutput = "FooterComponentModel [ComponentData=[FooterDataModel [brandLogo=Name, brandLogoAltText=Alt, brandLogoURL=/content/carnival/hal/en_us/home.html, numberOfTopNavItems=3, topNavList=[FooterTitleSublinksDataModel [title=Title 1, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_11, url=/content/data/fe/sublinkURL_11.html], FooterSublinkDataModel [label=sublinkLabel_12, url=/content/data/fe/sublinkURL_12.html]]], FooterTitleSublinksDataModel [title=Title 2, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_21, url=/content/data/fe/sublinkURL_21.html], FooterSublinkDataModel [label=sublinkLabel_22, url=http://www.abc.com/d.html]]], FooterTitleSublinksDataModel [title=Title 3, sublinkList=[FooterSublinkDataModel [label=sublinkLabel_31, url=http://www.abc.com/b/c/d.html], FooterSublinkDataModel [label=sublinkLabel_32, url=/content/data/fe/sublinkURL_32.html]]]], subNavList=[FooterTextLinkDataModel [label=textLinkLabel_1, url=/content/data/fe/textLinkURL_1.html], FooterTextLinkDataModel [label=textLinkLabel_2, url=http://www.externalURL.com/content/data/fe/textLinkURL_2]], socialList=[FooterSocialIconDataModel [icon=social icon 1, url=http://www.xyz.com], FooterSocialIconDataModel [icon=social icon 2, url=http://www.zyx.com/content/page/dam]], secondaryLogoList=[FooterSecondaryLogoDataModel [logo=null, altText=null, url=null]]]]]";
        assertTrue(expectedStringOutput.equals(footerComponentModel.toString()));
        
    }
    
    /** Tests whether empty component name is returned */
    @Test
    public void testComponentNameFooterSublinkDM() throws NoSuchFieldException {
        assertTrue(StringUtils.EMPTY.equals(subLinkDataModel.getComponentName()));
    }
    
    /** Tests whether empty component name is returned */
    @Test
    public void testComponentNameFooterTitleSublinksDM() throws NoSuchFieldException {
        assertTrue(StringUtils.EMPTY.equals(titleSublinksDataModel.getComponentName()));
    }
    
    /** Tests whether empty component name is returned */
    @Test
    public void testComponentNameFooterTextLinkDM() throws NoSuchFieldException {
        assertTrue(StringUtils.EMPTY.equals(textLinkDataModel.getComponentName()));
    }
    
    /** Tests whether empty component name is returned */
    @Test
    public void testComponentNameFooterSocialIconDM() throws NoSuchFieldException {
        assertTrue(StringUtils.EMPTY.equals(socialIconDataModel.getComponentName()));
    }
    
    /** Tests whether empty component name is returned */
    @Test
    public void testComponentNameFooterSecondaryLogoDM() throws NoSuchFieldException {
        assertTrue(StringUtils.EMPTY.equals(secondaryLogoDataModel.getComponentName()));
    }
    
    /** Adds general tab fields to footerDataModel class level variable */
    private void setUpGeneralTabFields() throws NoSuchFieldException {
    
        /** General Tab fields */
        String brandLogo = "Name";
        String brandLogoAltTxt = "Alt";
        String brandLogoURL = "/content/carnival/hal/en_us/home";
        String numberOfTopNavItems = "3";
        
        PrivateAccessor.setField(footerDataModel, "brandLogo", brandLogo);
        PrivateAccessor.setField(footerDataModel, "brandLogoAltText", brandLogoAltTxt);
        PrivateAccessor.setField(footerDataModel, "brandLogoURL", brandLogoURL);
        PrivateAccessor.setField(footerDataModel, "numberOfTopNavItems", numberOfTopNavItems);
        PrivateAccessor.setField(footerDataModel, "resource", resource);
        when(resourceResolver.map(brandLogoURL)).thenReturn(brandLogoURL + DOT_HTML);
    }

    /** Builds list of FooterTitleSublinksDataModel and returns it */
    private List<FooterTitleSublinksDataModel> buildTitleSublinksDataModelList() throws NoSuchFieldException {
        /* return param */
        List<FooterTitleSublinksDataModel> titleSublinksDataModelList = new ArrayList<>();

        List<FooterSublinkDataModel> subLinkDataModelList1 = new ArrayList<>();
        List<FooterSublinkDataModel> subLinkDataModelList2 = new ArrayList<>();
        List<FooterSublinkDataModel> subLinkDataModelList3 = new ArrayList<>();
        
        /** Title values */
        String titles[] = { "Title 1", "Title 2", "Title 3" };

        /** The values for sublinkLabels & sublinkURLs. Use same index parameters to get label and url */
        String sublinkLabels[][] = { { "sublinkLabel_11", "sublinkLabel_12" }, { "sublinkLabel_21", "sublinkLabel_22" },
                { "sublinkLabel_31", "sublinkLabel_32" } };
        String sublinkURLs[][] = { { "/content/data/fe/sublinkURL_11", "/content/data/fe/sublinkURL_12" },
                { "/content/data/fe/sublinkURL_21", "http://www.abc.com/d.html" },
                { "http://www.abc.com/b/c/d.html", "/content/data/fe/sublinkURL_32" } };
        
        /* Construct 1st object of type FooterTitleSublinksDataModel, add to return param */
        subLinkDataModel = new FooterSublinkDataModel();
        PrivateAccessor.setField(subLinkDataModel, "label", sublinkLabels[0][0]);
        PrivateAccessor.setField(subLinkDataModel, "url", sublinkURLs[0][0]);
        PrivateAccessor.setField(subLinkDataModel, "resource", resource);
        when(resourceResolver.map(sublinkURLs[0][0])).thenReturn(sublinkURLs[0][0] + DOT_HTML);
        subLinkDataModelList1.add(subLinkDataModel);

        subLinkDataModel = new FooterSublinkDataModel();
        PrivateAccessor.setField(subLinkDataModel, "label", sublinkLabels[0][1]);
        PrivateAccessor.setField(subLinkDataModel, "url", sublinkURLs[0][1]);
        PrivateAccessor.setField(subLinkDataModel, "resource", resource);
        when(resourceResolver.map(sublinkURLs[0][1])).thenReturn(sublinkURLs[0][1] + DOT_HTML);
        subLinkDataModelList1.add(subLinkDataModel);

        titleSublinksDataModel = new FooterTitleSublinksDataModel();
        PrivateAccessor.setField(titleSublinksDataModel, "title", titles[0]);
        PrivateAccessor.setField(titleSublinksDataModel, "sublinkList", subLinkDataModelList1);
        titleSublinksDataModelList.add(titleSublinksDataModel);

        /* Construct 2nd object of type FooterTitleSublinksDataModel, add to return param */
        subLinkDataModel = new FooterSublinkDataModel();
        PrivateAccessor.setField(subLinkDataModel, "label", sublinkLabels[1][0]);
        PrivateAccessor.setField(subLinkDataModel, "url", sublinkURLs[1][0]);
        when(resourceResolver.map(sublinkURLs[1][0])).thenReturn(sublinkURLs[1][0] + DOT_HTML);
        PrivateAccessor.setField(subLinkDataModel, "resource", resource);
        subLinkDataModelList2.add(subLinkDataModel);

        subLinkDataModel = new FooterSublinkDataModel();
        PrivateAccessor.setField(subLinkDataModel, "label", sublinkLabels[1][1]);
        PrivateAccessor.setField(subLinkDataModel, "url", sublinkURLs[1][1]);
        PrivateAccessor.setField(subLinkDataModel, "resource", resource);
        when(resourceResolver.map(sublinkURLs[1][1])).thenReturn(sublinkURLs[1][1]);
        subLinkDataModelList2.add(subLinkDataModel);

        titleSublinksDataModel = new FooterTitleSublinksDataModel();
        PrivateAccessor.setField(titleSublinksDataModel, "title", titles[1]);
        PrivateAccessor.setField(titleSublinksDataModel, "sublinkList", subLinkDataModelList2);
        titleSublinksDataModelList.add(titleSublinksDataModel);

        /* Construct 3rd object of type FooterTitleSublinksDataModel, add to return param */
        subLinkDataModel = new FooterSublinkDataModel();
        PrivateAccessor.setField(subLinkDataModel, "label", sublinkLabels[2][0]);
        PrivateAccessor.setField(subLinkDataModel, "url", sublinkURLs[2][0]);
        PrivateAccessor.setField(subLinkDataModel, "resource", resource);
        when(resourceResolver.map(sublinkURLs[2][0])).thenReturn(sublinkURLs[2][0]);
        subLinkDataModelList3.add(subLinkDataModel);

        subLinkDataModel = new FooterSublinkDataModel();
        PrivateAccessor.setField(subLinkDataModel, "label", sublinkLabels[2][1]);
        PrivateAccessor.setField(subLinkDataModel, "url", sublinkURLs[2][1]);
        PrivateAccessor.setField(subLinkDataModel, "resource", resource);
        when(resourceResolver.map(sublinkURLs[2][1])).thenReturn(sublinkURLs[2][1] + DOT_HTML);
        subLinkDataModelList3.add(subLinkDataModel);

        titleSublinksDataModel = new FooterTitleSublinksDataModel();
        PrivateAccessor.setField(titleSublinksDataModel, "title", titles[2]);
        PrivateAccessor.setField(titleSublinksDataModel, "sublinkList", subLinkDataModelList3);
        titleSublinksDataModelList.add(titleSublinksDataModel);

        return titleSublinksDataModelList;
    }

    /** Builds list of FooterTextLinkDataModel and returns it */
    private List<FooterTextLinkDataModel> buildTextLinksDMList() throws NoSuchFieldException {

        /* return param */
        List<FooterTextLinkDataModel> textLinkDMList = new ArrayList<>();        
        
        /** The values for textLinkLabels and textLinkURLs. Use same index parameters to get label and url. */
        String textLinkLabels[] = { "textLinkLabel_1", "textLinkLabel_2" };
        String textLinkURLs[] = { "/content/data/fe/textLinkURL_1", "http://www.externalURL.com/content/data/fe/textLinkURL_2" };
        
        /* Build individual FooterTextLinkDataModel, add to return param. */
        textLinkDataModel = new FooterTextLinkDataModel();

        PrivateAccessor.setField(textLinkDataModel, "label", textLinkLabels[0]);
        PrivateAccessor.setField(textLinkDataModel, "url", textLinkURLs[0]);
        when(resourceResolver.map(textLinkURLs[0])).thenReturn(textLinkURLs[0] + DOT_HTML);
        PrivateAccessor.setField(textLinkDataModel, "resource", resource);
        textLinkDMList.add(textLinkDataModel);

        textLinkDataModel = new FooterTextLinkDataModel();
        PrivateAccessor.setField(textLinkDataModel, "label", textLinkLabels[1]);
        PrivateAccessor.setField(textLinkDataModel, "url", textLinkURLs[1]);
        when(resourceResolver.map(textLinkURLs[1])).thenReturn(textLinkURLs[1]);
        PrivateAccessor.setField(textLinkDataModel, "resource", resource);
        textLinkDMList.add(textLinkDataModel);

        return textLinkDMList;
    }
    
    /** Builds list of FooterSocialIconDataModel and returns it */
    private List<FooterSocialIconDataModel> buildSocialIconList() throws NoSuchFieldException {
        /* return param */
        List<FooterSocialIconDataModel> socialIconDMList = new ArrayList<>();

        /** The values for socialIconNames, socialIconAltText and socialIconURLs. */
        String socialIconNames[] = { "social icon 1", "social icon 2" };
        String socialIconURLs[] = { "http://www.xyz.com", "http://www.zyx.com/content/page/dam" };        
        
        /* Build individual FooterSocialIconDataModel, add to return param. */
        socialIconDataModel = new FooterSocialIconDataModel();
        
        PrivateAccessor.setField(socialIconDataModel, "icon", socialIconNames[0]);
        PrivateAccessor.setField(socialIconDataModel, "url", socialIconURLs[0]);
        PrivateAccessor.setField(socialIconDataModel, "resource", resource);
        when(resourceResolver.map(socialIconURLs[0])).thenReturn(socialIconURLs[0]);
        socialIconDMList.add(socialIconDataModel);

        socialIconDataModel = new FooterSocialIconDataModel();
        PrivateAccessor.setField(socialIconDataModel, "icon", socialIconNames[1]);
        PrivateAccessor.setField(socialIconDataModel, "url", socialIconURLs[1]);
        PrivateAccessor.setField(socialIconDataModel, "resource", resource);
        when(resourceResolver.map(socialIconURLs[1])).thenReturn(socialIconURLs[1]);
        socialIconDMList.add(socialIconDataModel);
        
        return socialIconDMList;
    }
    
    /** Builds list of FooterSecondaryLogoDataModel and returns it */
    private List<FooterSecondaryLogoDataModel> buildSecondaryLogoDMList() throws NoSuchFieldException {
        /* return param */
        List<FooterSecondaryLogoDataModel> secondaryLogoList = new ArrayList<>();
        
        /** The secondary logo paths */
        String secondaryLogoPaths[] = { "/content/dam/secondaryLogos/image1.png", "/content/dam/secondaryLogos/image2.png" };
        String secondaryLogoAltTexts[] = { "image1", "image2" };
        String secondaryLogoURLs[] = { "/content/data/fe/secondaryLogoURL_1", "http://www.abc.com/dam/x/d.php" };

        /* Build individual FooterSocialIconDataModel, add to return param. */
        secondaryLogoDataModel = new FooterSecondaryLogoDataModel();
        
        PrivateAccessor.setField(secondaryLogoDataModel, "logo", secondaryLogoPaths[0]);
        PrivateAccessor.setField(secondaryLogoDataModel, "altText", secondaryLogoAltTexts[0]);
        PrivateAccessor.setField(secondaryLogoDataModel, "url", secondaryLogoURLs[0]);
        PrivateAccessor.setField(secondaryLogoDataModel, "resource", resource);
        when(resourceResolver.map(secondaryLogoURLs[0])).thenReturn(secondaryLogoURLs[0] + DOT_HTML);
        secondaryLogoList.add(secondaryLogoDataModel);
        
        secondaryLogoDataModel = new FooterSecondaryLogoDataModel();
        PrivateAccessor.setField(secondaryLogoDataModel, "logo", secondaryLogoPaths[1]);
        PrivateAccessor.setField(secondaryLogoDataModel, "altText", secondaryLogoAltTexts[1]);
        PrivateAccessor.setField(secondaryLogoDataModel, "url", secondaryLogoURLs[1]);
        PrivateAccessor.setField(secondaryLogoDataModel, "resource", resource);
        when(resourceResolver.map(secondaryLogoURLs[1])).thenReturn(secondaryLogoURLs[1]);
        secondaryLogoList.add(secondaryLogoDataModel);
        
        return secondaryLogoList;
    }    
}
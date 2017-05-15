package com.carnival.platform.services.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.data.UtilityNavDataModel;
import com.carnival.platform.models.data.UtilityNavItemDataModel;
import com.carnival.platform.utils.PageUtil;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import junitx.util.PrivateAccessor;

/**
 * The Class UtilityNavListServiceImplTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilityNavItemDataServiceImplTest {

	/** The resource resolver. */
	@Mock
	private ResourceResolver resourceResolver;

	/** The resource. */
	@Mock
	private Resource resource;

	/** The page mang. */
	@Mock
	private PageManager pageManager;

	/** The page. */
	@Mock
	private Page page;

	/** The request. */
	@Mock
	private SlingHttpServletRequest request;

	/** The properties. */
	@Mock
	private ValueMap properties;

	/** The utility nav list service impl. */
	@InjectMocks
	private UtilityNavItemDataServiceImpl utilityNavItemDataServiceImpl;

	/** The utility nav list data model. */
	private UtilityNavItemDataModel utilityNavListDataModel;

	/** The request path info. */
	@Mock
	private RequestPathInfo requestPathInfo;

	/** The value map. */
	@Mock
	private ValueMap valueMap;

	/** The page. */
	@Mock
	private Page homePage;
	
	/** The utility nav data model. */
	private UtilityNavDataModel utilityNavDataModel;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		utilityNavDataModel = new UtilityNavDataModel();
		utilityNavListDataModel = new UtilityNavItemDataModel();
		PrivateAccessor.setField(utilityNavListDataModel, "linkUrl", "linkUrl");
		PrivateAccessor.setField(utilityNavListDataModel, "navType", "language");
		PrivateAccessor.setField(utilityNavListDataModel, "showIcon", "true");
		PrivateAccessor.setField(utilityNavListDataModel, "title", "title");
		PrivateAccessor.setField(utilityNavListDataModel, "langPaths", new String[] { "/content/carnival/en" });
		PrivateAccessor.setField(utilityNavListDataModel, "linkUrl", "linkUrl");
		PrivateAccessor.setField(utilityNavListDataModel, "resource", resource);

		when(request.getRequestURI()).thenReturn("/content/carnival/en.html");
		when(request.getResource()).thenReturn(resource);
        when(resource.getPath()).thenReturn("/content/hal/en_us/jcr:content/globalHeader/globalHeader");
		when(request.getQueryString()).thenReturn(null);
		when(request.getPathInfo()).thenReturn("/content/carnival/en.html");
		when(utilityNavListDataModel.getResourceResolver()).thenReturn(resourceResolver);
		when(request.getRequestPathInfo()).thenReturn(requestPathInfo);
		when(requestPathInfo.getSelectorString()).thenReturn(null);
		when(resourceResolver.getResource(anyString())).thenReturn(resource);
		when(resource.adaptTo(ValueMap.class)).thenReturn(valueMap);
		when(valueMap.get(JcrConstants.JCR_TITLE)).thenReturn("title");
		when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		when(pageManager.getPage("/content/carnival/en")).thenReturn(page);
		when(page.getAbsoluteParent(ApplicationConstants.INT_2)).thenReturn(homePage);
		when(page.getPath()).thenReturn("/content/carnival/en");
		when(homePage.getPath()).thenReturn("/content/carnival/en");
		when(pageManager.getPage("/content/carnival/en")).thenReturn(page);


	}

	/**
	 * Test set language drop down items.
	 */
	@Test
	public void testSetLanguageDropDownItems() {
		final String expectedString = "UtilityNavListDataModel [linkUrl=linkUrl, navType=language, showIcon=true, title=title, items=[LanguageModel [name=title, path=null, status=active]]]";
		utilityNavItemDataServiceImpl.setLanguageDropDownItems(utilityNavListDataModel, request);
		Assert.assertTrue(expectedString.equals(utilityNavListDataModel.toString()));
	}

	/**
	 * Test update sling model.
	 *
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	@Test
	public void testUpdateSlingModel() throws NoSuchFieldException {
		final String expectedString = "UtilityNavListDataModel [linkUrl=linkUrl, navType=language, showIcon=true, title=title, items=null]";
		List<UtilityNavItemDataModel> utilityNavigationList = new ArrayList<>();
		utilityNavigationList.add(utilityNavListDataModel);

		PrivateAccessor.setField(utilityNavDataModel, "navItems", utilityNavigationList);
		Assert.assertTrue(expectedString.equals(utilityNavListDataModel.toString()));
	}
    /**
     * Test set langauge dropdown  items
     * @throws NoSuchFieldException 
     */
	@Test
	public void testSetLangaugeDropdownItemsWhenLangPathsisNull() throws NoSuchFieldException{
		final String expectedString="UtilityNavListDataModel [linkUrl=linkUrl, navType=language, showIcon=true, title=title, items=null]";
		PrivateAccessor.setField(utilityNavListDataModel, "langPaths",null);
		utilityNavItemDataServiceImpl.setLanguageDropDownItems(utilityNavListDataModel, request);
		Assert.assertTrue(expectedString.equals(utilityNavListDataModel.toString()));
	}
	/**
	 * Test getLangaugeModel whenPageisavailableinlocale
	 * */
	@Test
	public void testgetLangaugeModelwhenPageisavailableinlocale(){
		when(PageUtil.shortenUrl(resourceResolver, "/content/carnival/fr")).thenReturn("/content/carnival/fr.html");
		when(pageManager.getPage("/content/carnival/fr")).thenReturn(page);
		when(page.getPath()).thenReturn("/content/carnival/fr");
		String expectedString="LanguageModel [name=title, path=/content/carnival/fr.html, status=inactive]";
		String actualString=utilityNavItemDataServiceImpl.getLangaugeModel(resourceResolver, "/content/carnival/fr", "/content/carnival/en", null, null).toString();
		Assert.assertEquals(expectedString,actualString);
	}
	/**
	 * Test getLangaugeModel whenPageisnotavailableinlocale
	 * */
	@Test
	public void testgetLangaugeModelwhenPageisnotavailableinlocale(){
		when(PageUtil.shortenUrl(resourceResolver, "/content/carnival/fr")).thenReturn("/content/carnival/fr.html");
		when(pageManager.getPage("/content/carnival/fr")).thenReturn(null);
		String expectedString="LanguageModel [name=title, path=/content/carnival/fr.html, status=inactive]";
		String actualString=utilityNavItemDataServiceImpl.getLangaugeModel(resourceResolver, "/content/carnival/fr", "/content/carnival/en", null, null).toString();
		Assert.assertEquals(expectedString,actualString);
	}
	
    /**
	 * Test getLangaugeModel when queryparam and selector is not null
	 * */
    @Test
    public void testgetLangaugeModelwhenQueryParamisnotnull(){
    	when(PageUtil.shortenUrl(resourceResolver, "/content/carnival/fr")).thenReturn("/content/carnival/fr.html");
    	when(pageManager.getPage("/content/carnival/fr")).thenReturn(page);
    	when(PageUtil.shortenUrl(resourceResolver, "/content/carnival/fr.test")).thenReturn("/content/carnival/fr.test.html");
    	when(page.getPath()).thenReturn("/content/carnival/fr");
    	when(homePage.getPath()).thenReturn("/content/carnival/fr");
    	String expectedString="LanguageModel [name=title, path=/content/carnival/fr.test.html?name=xyz, status=active]";
		String actualString=utilityNavItemDataServiceImpl.getLangaugeModel(resourceResolver, "/content/carnival/fr", "/content/carnival/fr","test", "name=xyz").toString();
		Assert.assertEquals(expectedString,actualString);
    }
    
}

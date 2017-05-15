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
/**
 * The Class UtilityNavDataModel Test
 * @author pramis
 * */

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.data.UtilityNavDataModel;
import com.carnival.platform.models.data.UtilityNavItemDataModel;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import junitx.util.PrivateAccessor;

@RunWith(MockitoJUnitRunner.class)
public class UtilityNavDataModelTest {

	/** The Utility Nav Data Model */
	private UtilityNavDataModel utilityNavDataModel;

	/** The utility nav list data model. */
	private UtilityNavItemDataModel utilityNavItemDataModel;

	/** The utility nav list service impl. */
	@InjectMocks
	private UtilityNavItemDataServiceImpl utilityNavItemDataServiceImpl;

	/** The resource. */
	@Mock
	private Resource resource;

	@Mock
	private SlingHttpServletRequest request;

	/** The request path info. */
	@Mock
	private RequestPathInfo requestPathInfo;
	/** The resource resolver. */
	@Mock
	private ResourceResolver resourceResolver;

	/** The page mang. */
	@Mock
	private PageManager pageManager;

	/** The page. */
	@Mock
	private Page page;
	/** The properties. */
	@Mock
	private ValueMap properties;
	/** The value map. */
	@Mock
	private ValueMap valueMap;

	/** The page. */
	@Mock
	private Page homePage;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		utilityNavDataModel = new UtilityNavDataModel();
		utilityNavItemDataModel = new UtilityNavItemDataModel();
		PrivateAccessor.setField(utilityNavItemDataModel, "linkUrl", "linkUrl");
		PrivateAccessor.setField(utilityNavItemDataModel, "navType", "language");
		PrivateAccessor.setField(utilityNavItemDataModel, "showIcon", "true");
		PrivateAccessor.setField(utilityNavItemDataModel, "title", "title");
		PrivateAccessor.setField(utilityNavItemDataModel, "langPaths", new String[] { "/content/carnival/en" });
		PrivateAccessor.setField(utilityNavItemDataModel, "linkUrl", "linkUrl");
		PrivateAccessor.setField(utilityNavItemDataModel, "resource", resource);
		List<UtilityNavItemDataModel> navItems = new ArrayList<>();
		navItems.add(utilityNavItemDataModel);
		PrivateAccessor.setField(utilityNavDataModel, "navItems", navItems);
		PrivateAccessor.setField(utilityNavItemDataModel, "utilityNavItemDataServiceImpl",
				utilityNavItemDataServiceImpl);
		when(utilityNavItemDataModel.getResourceResolver()).thenReturn(resourceResolver);
		when(request.getRequestURI()).thenReturn("/content/carnival/en.html");
		when(request.getResource()).thenReturn(resource);
		when(resource.getPath()).thenReturn("/content/hal/en_us/jcr:content/globalHeader/globalHeader");
		when(request.getRequestPathInfo()).thenReturn(requestPathInfo);
		when(request.getQueryString()).thenReturn(null);
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
		when(page.getPath()).thenReturn("/content/carnival/en");
		utilityNavDataModel.updateRequestInfo(request);

	}

	/**
	 * Test get Component Name
	 */
	@Test
	public void testgetComponentName() {
		Assert.assertEquals("utilityNav", utilityNavDataModel.getComponentName());
	}

	/**
	 * Test set NavItems
	 */
	@Test
	public void testgetNavItems() {
		Assert.assertEquals(utilityNavItemDataModel.toString(), utilityNavDataModel.getNavItems().get(0).toString());
	}

	/**
	 * Test toString of UtilityNavDataModel
	 */
	@Test
	public void testToString() {
		String expectedResult = "UtilityNavDataModel [navItems=[UtilityNavListDataModel [linkUrl=linkUrl, navType=language, showIcon=true, title=title, items=[LanguageModel [name=title, path=null, status=active]]]]]";
		Assert.assertEquals(expectedResult, utilityNavDataModel.toString());
	}

}

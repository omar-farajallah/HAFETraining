package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.data.ArticleDataModel;
import com.carnival.platform.models.data.CopyBlockDataModel;
import com.carnival.platform.models.data.FullWidthImageDataModel;
import com.carnival.platform.models.data.QuotePulloutDataModel;
import com.carnival.platform.models.data.TitleDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class ArticleComponentModelTest.
 *
 * @author ngurr1
 * @version 1.0
 * 
 *          This is a test class for Article Component Model Class
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleComponentModelTest {

	/** The resource. */
	@Mock
	private Resource titleResource;

	/** The resource. */
	@Mock
	private Resource copyBlockResource;

	/** The resource. */
	@Mock
	private Resource quotePulloutResource;

	/** The resource. */
	@Mock
	private Resource fullWidthImageResource;

	/** The title map. */
	@Mock
	private ValueMap titleMap;

	/** The resource. */
	@Mock
	private Resource resource;

	/** The copy block map. */
	@Mock
	private ValueMap copyBlockMap;

	/** The quote pullout map. */
	@Mock
	private ValueMap quotePulloutMap;

	/** The full width image map. */
	@Mock
	private ValueMap fullWidthImageMap;

	/** The child components. */
	private List<Resource> childComponents;

	/** The article component model. */
	private ArticleComponentModel articleComponentModel = new ArticleComponentModel();

	/** The article data model. */
	private ArticleDataModel articleDataModel = new ArticleDataModel();

	/** The copy block data model. */
	private CopyBlockDataModel copyBlockDataModel = null;

	/** The quote pullout data model. */
	private QuotePulloutDataModel quotePulloutDataModel = null;

	/** The title data model. */
	private TitleDataModel titleDataModel = null;

	/** The full width image data model. */
	private FullWidthImageDataModel fullWidthImageDataModel = null;

	/** The copy block component model. */
	private CopyBlockComponentModel copyBlockComponentModel = null;

	/** The quote pullout component model. */
	private QuotePulloutComponentModel quotePulloutComponentModel = null;

	/** The title component model. */
	private TitleComponentModel titleComponentModel = null;

	/** The full width image component model. */
	private FullWidthImageComponentModel fullWidthImageComponentModel = null;

	/** The Constant EXPECTED_COMPONENTMODEL_NULL_STRING. */
	private static final Object EXPECTED_COMPONENTMODEL_NULL_STRING = "ArticleComponentModel [ComponentName=article, ComponentData=[ArticleDataModel [ctaLabel=ctaLabel, backtopLabel=backtopLabel]]]";

	/**
	 * Test component name.
	 */
	@Test
	public void testComponentName() {
		assertTrue(ArticleDataModel.COMPONENT_NAME.equals(articleDataModel.getComponentName()));
	}

	/**
	 * setting up initial required test data.
	 *
	 * @throws Exception
	 *             the exception
	 */
	private void setUpInitialData() throws Exception {

		copyBlockDataModel = new CopyBlockDataModel();

		quotePulloutDataModel = new QuotePulloutDataModel();

		titleDataModel = new TitleDataModel();

		fullWidthImageDataModel = new FullWidthImageDataModel();

		copyBlockComponentModel = new CopyBlockComponentModel();

		quotePulloutComponentModel = new QuotePulloutComponentModel();

		titleComponentModel = new TitleComponentModel();

		fullWidthImageComponentModel = new FullWidthImageComponentModel();

		childComponents = new ArrayList<Resource>();

		// TitleH1 Component Data
		PrivateAccessor.setField(titleDataModel, "title", "Title");
		PrivateAccessor.setField(titleDataModel, "description", "Description");
		PrivateAccessor.setField(titleDataModel, "dividerType", "DividerType");
		PrivateAccessor.setField(titleDataModel, "dividerText", "DividerText");
		PrivateAccessor.setField(titleDataModel, "isCampaignHeaderRequired", "true");
		PrivateAccessor.setField(titleDataModel, "campaignHeaderText", "CampaignHeaderText");
		PrivateAccessor.setField(titleComponentModel, "titleH1", titleDataModel);
		childComponents.add(titleResource);

		// Copy Block Component Data
		PrivateAccessor.setField(copyBlockDataModel, "text", "text");
		PrivateAccessor.setField(copyBlockComponentModel, "copyBlock", copyBlockDataModel);
		childComponents.add(copyBlockResource);

		// Quote Pullout Component Data
		PrivateAccessor.setField(quotePulloutDataModel, "quoteText", "quoteText");
		PrivateAccessor.setField(quotePulloutComponentModel, "quotePullout", quotePulloutDataModel);
		childComponents.add(quotePulloutResource);

		// Full Width Image Component Data
		PrivateAccessor.setField(fullWidthImageDataModel, "desktopImageURL", "content/dam/carnival/hal/test.jpg");
		PrivateAccessor.setField(fullWidthImageDataModel, "tabletImageURL", "content/dam/carnival/hal/test.jpg");
		PrivateAccessor.setField(fullWidthImageDataModel, "mobileImageURL", "content/dam/carnival/hal/test.jpg");
		PrivateAccessor.setField(fullWidthImageDataModel, "alt", "alt");
		PrivateAccessor.setField(fullWidthImageDataModel, "resource", resource);

		PrivateAccessor.setField(fullWidthImageComponentModel, "fullWidthImage", fullWidthImageDataModel);
		childComponents.add(fullWidthImageResource);

		PrivateAccessor.setField(articleDataModel, "ctaLabel", "ctaLabel");
		PrivateAccessor.setField(articleDataModel, "backtopLabel", "backtopLabel");
		PrivateAccessor.setField(articleComponentModel, "article", articleDataModel);

		PrivateAccessor.setField(articleComponentModel, "childComponents", childComponents);
	}

	/**
	 * Test Method for childComponents are null.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testComponentDataNull() throws Exception {
		setUpInitialData();
		PrivateAccessor.setField(articleComponentModel, "childComponents", null);
		assertTrue(EXPECTED_COMPONENTMODEL_NULL_STRING.equals(articleComponentModel.toString()));
	}

	/**
	 * Test Method for componentModel is null.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetComponentModelNull() throws Exception {
		setUpInitialData();
		when(titleResource.getValueMap()).thenReturn(titleMap);
		when(titleMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class))
				.thenReturn(TitleComponentModel.class.getName());
		when(titleResource.adaptTo(TitleComponentModel.class)).thenReturn(null);

		when(copyBlockResource.getValueMap()).thenReturn(copyBlockMap);
		when(copyBlockMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class))
				.thenReturn(CopyBlockComponentModel.class.getName());
		when(copyBlockResource.adaptTo(CopyBlockComponentModel.class)).thenReturn(null);

		when(quotePulloutResource.getValueMap()).thenReturn(quotePulloutMap);
		when(quotePulloutMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class))
				.thenReturn(QuotePulloutComponentModel.class.getName());
		when(quotePulloutResource.adaptTo(QuotePulloutComponentModel.class)).thenReturn(null);

		when(fullWidthImageResource.getValueMap()).thenReturn(fullWidthImageMap);
		when(fullWidthImageMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class))
				.thenReturn(FullWidthImageComponentModel.class.getName());
		when(fullWidthImageResource.adaptTo(FullWidthImageComponentModel.class)).thenReturn(null);

		assertTrue(EXPECTED_COMPONENTMODEL_NULL_STRING.equals(articleComponentModel.toString()));
	}

	/**
	 * Test Method for Model Class Parameter is null.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetComponentModelObj() throws Exception {
		setUpInitialData();
		when(titleResource.getValueMap()).thenReturn(titleMap);
		when(titleMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class)).thenReturn(StringUtils.EMPTY);

		when(copyBlockResource.getValueMap()).thenReturn(copyBlockMap);
		when(copyBlockMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class)).thenReturn(StringUtils.EMPTY);

		when(quotePulloutResource.getValueMap()).thenReturn(quotePulloutMap);
		when(quotePulloutMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class)).thenReturn(StringUtils.EMPTY);

		when(fullWidthImageResource.getValueMap()).thenReturn(fullWidthImageMap);
		when(fullWidthImageMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class)).thenReturn(StringUtils.EMPTY);

		assertTrue(EXPECTED_COMPONENTMODEL_NULL_STRING.equals(articleComponentModel.toString()));
	}

	/**
	 * Test Method for Actual Component Data.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testComponentData() throws Exception {
		setUpInitialData();
		final String expectedString = "ArticleComponentModel [ComponentName=article, ComponentData=[ArticleDataModel [ctaLabel=ctaLabel, backtopLabel=backtopLabel], TitleDataModel [title=Title, description=Description, dividerType=DividerType, dividerText=DividerText, isCampaignHeaderRequired=true, campaignHeaderText=CampaignHeaderText], CopyBlockDataModel [text=text], QuotePulloutDataModel [quoteText=quoteText], FullWidthImageDataModel [image=Image [imgAlt=alt, smallRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.320.214.low.jpg, retina=content/dam/carnival/hal/test.jpg.image.640.428.low.jpg], mediumRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.585.390.medium.jpg, retina=content/dam/carnival/hal/test.jpg.image.1170.780.medium.jpg], largeRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.668.446.high.jpg, retina=content/dam/carnival/hal/test.jpg.image.1336.892.high.jpg]]]]]";
		when(titleResource.getValueMap()).thenReturn(titleMap);
		when(titleMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class))
				.thenReturn(TitleComponentModel.class.getName());
		when(titleResource.adaptTo(TitleComponentModel.class)).thenReturn(titleComponentModel);

		when(copyBlockResource.getValueMap()).thenReturn(copyBlockMap);
		when(copyBlockMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class))
				.thenReturn(CopyBlockComponentModel.class.getName());
		when(copyBlockResource.adaptTo(CopyBlockComponentModel.class)).thenReturn(copyBlockComponentModel);

		when(quotePulloutResource.getValueMap()).thenReturn(quotePulloutMap);
		when(quotePulloutMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class))
				.thenReturn(QuotePulloutComponentModel.class.getName());
		when(quotePulloutResource.adaptTo(QuotePulloutComponentModel.class)).thenReturn(quotePulloutComponentModel);

		when(fullWidthImageResource.getValueMap()).thenReturn(fullWidthImageMap);
		when(fullWidthImageMap.get(ApplicationConstants.MODEL_CLASS_PARAM, String.class))
				.thenReturn(FullWidthImageComponentModel.class.getName());
		when(fullWidthImageResource.adaptTo(FullWidthImageComponentModel.class))
				.thenReturn(fullWidthImageComponentModel);
		assertTrue(expectedString.equals(articleComponentModel.toString()));
	}
}

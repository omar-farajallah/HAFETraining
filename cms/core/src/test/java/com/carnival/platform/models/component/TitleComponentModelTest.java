package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.TitleDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class TitleComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class TitleComponentModelTest {

	/** The title data model. */
	@InjectMocks
	private TitleDataModel titleDataModel;

	/** The title component model. */
	@InjectMocks
	private TitleComponentModel titleComponentModel;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {

		PrivateAccessor.setField(titleDataModel, "title", "title");
		PrivateAccessor.setField(titleDataModel, "description", "description");
		PrivateAccessor.setField(titleDataModel, "dividerType", "dividerType");
		PrivateAccessor.setField(titleDataModel, "dividerText", "dividerText");
		PrivateAccessor.setField(titleDataModel, "isCampaignHeaderRequired", "true");
		PrivateAccessor.setField(titleDataModel, "campaignHeaderText", "Campaign Header Text");
		PrivateAccessor.setField(titleComponentModel, "titleH1", titleDataModel);
	}

	/**
	 * Test component name.
	 */
	@Test
	public void testComponentName() {
		assertTrue(TitleDataModel.COMPONENT_NAME.equals(titleDataModel.getComponentName()));
	}

	/**
	 * Test component data.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testComponentData() throws Exception {
		PrivateAccessor.setField(titleComponentModel, "titleH1", titleDataModel);

		// Expected titleData model
		TitleDataModel expectedTitleDataModel = new TitleDataModel();
		PrivateAccessor.setField(expectedTitleDataModel, "title", "title");
		PrivateAccessor.setField(expectedTitleDataModel, "description", "description");
		PrivateAccessor.setField(expectedTitleDataModel, "dividerType", "dividerType");
		PrivateAccessor.setField(expectedTitleDataModel, "dividerText", "dividerText");
		PrivateAccessor.setField(expectedTitleDataModel, "isCampaignHeaderRequired", "true");
		PrivateAccessor.setField(expectedTitleDataModel, "campaignHeaderText", "Campaign Header Text");

		assertTrue(expectedTitleDataModel.toString().equals(titleComponentModel.getComponentData()[0].toString()));
	}

}

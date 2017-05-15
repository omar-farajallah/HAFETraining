package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * The Class CampignHeaderLandingComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class CampignLandingHeaderComponentModelTest extends AbstractComponentTest {

	/** The CAMPAIGN LANDING HEADER component model. */
	@InjectMocks
	private CampaignLandingHeaderComponentModel componentModel;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();

	}

	/**
	 * Test component name.
	 *
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	@Test
	public void testComponentName() throws NoSuchFieldException {
		assertTrue(CampaignLandingHeaderComponentModel.COMPONENT_NAME.equals(componentModel.getComponentName()));
	}

	/**
	 * Test component model data.
	 *
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	@Override
	public void testComponentModelData() throws NoSuchFieldException {
		final String expectedString = "emptyDataModel []";
		assertTrue(expectedString.equals(componentModel.getComponentData()[0].toString()));
	}

}

/*
 * 
 */
package com.carnival.platform.models.component;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.EditorialContentDataModel;
import com.carnival.platform.models.data.TitleDataModel;

import junitx.util.PrivateAccessor;

/**
 * The Class EditorialContentComponentModelTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class EditorialContentComponentModelTest extends AbstractComponentTest {

    /** The sling model. */
    @InjectMocks
    private EditorialContentDataModel slingModel;

    /** The title data model. */
    @InjectMocks
    private TitleDataModel titleDataModel;

    /** The editorial content component model. */
    @InjectMocks
    private EditorialContentComponentModel editorialContentComponentModel;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.models.component.AbstractBaseComponentTest#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        PrivateAccessor.setField(slingModel, "title", "Title");
        PrivateAccessor.setField(slingModel, "textBlock", "Text Block");
        PrivateAccessor.setField(slingModel, "imageURL", "content/dam/carnival/hal/test.jpg");
        PrivateAccessor.setField(slingModel, "imageAltText", "Alt");
        PrivateAccessor.setField(slingModel, "caption", "Caption");
        PrivateAccessor.setField(titleDataModel, "title", "Title");
        PrivateAccessor.setField(titleDataModel, "dividerType", "icon");
        PrivateAccessor.setField(titleDataModel, "dividerText", "text");
        PrivateAccessor.setField(titleDataModel, "description", "Description");
        PrivateAccessor.setField(titleDataModel, "isCampaignHeaderRequired", "true");
        PrivateAccessor.setField(titleDataModel, "campaignHeaderText", "CampaignHeaderText");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentName()
     */
    @Override
    public void testComponentName() {
        assertTrue(EditorialContentDataModel.COMPONENT_NAME.equals(slingModel.getComponentName()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseComponentTest#
     * testComponentModelData()
     */
    @Override
    public void testComponentModelData() throws NoSuchFieldException {

        final String expetedEditorialString = "EditorialContentDataModel [title=Title, textBlock=Text Block, caption=Caption, image=Image [imgAlt=Alt, smallRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.252.302.low.jpg, retina=content/dam/carnival/hal/test.jpg.image.504.604.low.jpg], mediumRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.396.347.medium.jpg, retina=content/dam/carnival/hal/test.jpg.image.792.694.medium.jpg], largeRendition=Rendition [regular=content/dam/carnival/hal/test.jpg.image.360.416.high.jpg, retina=content/dam/carnival/hal/test.jpg.image.720.832.high.jpg]]]";
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");
        PrivateAccessor.setField(editorialContentComponentModel, "titleH1", titleDataModel);
        PrivateAccessor.setField(editorialContentComponentModel, "editorialContent", slingModel);
        assertTrue(expetedEditorialString.equals(editorialContentComponentModel.getComponentData()[0].toString()));

    }

    /**
     * Test component model title data.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Test
    public void testComponentModelTitleData() throws NoSuchFieldException {
        PrivateAccessor.setField(editorialContentComponentModel, "titleH1", titleDataModel);
        final String expectedTileDataString = "TitleDataModel [title=Title, description=Description, dividerType=icon, dividerText=text, isCampaignHeaderRequired=true, campaignHeaderText=CampaignHeaderText]";
        assertTrue(expectedTileDataString.equals(editorialContentComponentModel.getComponentData()[1].toString()));
    }
}

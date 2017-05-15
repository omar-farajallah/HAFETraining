/**
 * 
 */
package com.carnival.platform.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.AbstractSlingModel;
import com.carnival.platform.models.BaseJson;
import com.carnival.platform.models.component.EditorialContentComponentModel;
import com.carnival.platform.models.component.HeroTileComponentModel;
import com.carnival.platform.models.component.StoryRotatorComponentModel;
import com.carnival.platform.models.data.EditorialContentDataModel;
import com.carnival.platform.models.data.HeroTileDataModel;
import com.carnival.platform.models.data.StoryRotatorDataModel;
import com.carnival.platform.models.data.TitleDataModel;
import com.carnival.platform.services.RenderingService;

import junitx.util.PrivateAccessor;

/**
 * The Class DefaultJsonServiceImplTest.
 *
 * @author ssahu6
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultComponentServiceImplTest extends AbstractServiceTest {

    /** The default component service impl. */
    @InjectMocks
    private DefaultComponentServiceImpl defaultComponentServiceImpl;

    /** The default component service. */
    @Mock
    private DefaultComponentServiceImpl defaultComponentService;

    /** The rendering service. */
    @Mock
    private RenderingService renderingService = new RenderingServiceImpl();

    /** The src model. */
    @InjectMocks
    private StoryRotatorComponentModel srcModel;

    /** The src data model. */
    @InjectMocks
    private StoryRotatorDataModel srcDataModel;

    /** The hero tile comp model. */
    @InjectMocks
    private HeroTileComponentModel heroTileCompModel;

    /** The hero tile data model. */
    @InjectMocks
    private HeroTileDataModel heroTileDataModel;

    /** The resource. */
    @Mock
    private Resource resource;

    /** The json. */
    private String json;

    /**
     * Sets the up.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Before
    public void setUp() throws NoSuchFieldException {
        super.setUp();
        srcModel.getId();
        json = "{\"type\":\"storyRotator\",\"id\":\"{id}\",\"attributes\":{\"childComponents\":[],\"dividertext\":\"\",\"showTexture\":\"\",\"texture\":\"\",\"storyList\":{},\"infoCardAlignement\":\"\"},\"meta\":{\"render\":\"static\"}}";
        PrivateAccessor.setField(srcModel, "storyRotator", srcDataModel);
        PrivateAccessor.setField(srcDataModel, "resource", resource);
        PrivateAccessor.setField(heroTileCompModel, "heroTile", heroTileDataModel);
        when(defaultComponentServiceImpl.isComponentStatic(anyString())).thenReturn(Boolean.TRUE);
    }

    /**
     * Dynamic json available.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Test
    public void dynamicJsonAvailable() throws NoSuchFieldException {
        when(defaultComponentServiceImpl.isComponentStatic(anyString())).thenReturn(Boolean.TRUE);
        defaultComponentServiceImpl.updateModel(heroTileCompModel);
        json = "{\"type\":\"heroTile\",\"id\":\"{id}\",\"attributes\":{\"type\":\"\",\"videoURL\":\"\",\"cardAlignment\":\"\",\"logo\":\"\",\"logoAlt\":\"\",\"title\":\"\",\"description\":\"\",\"ctaIcon\":\"\",\"ctaAlt\":\"\",\"ctaLabel\":\"\",\"ctaType\":\"\",\"ctaURL\":\"\",\"image\":{\"alt\":\"\",\"0\":{\"1x\":\"null.image.320.240.low.jpg\",\"2x\":\"null.image.640.480.low.jpg\"},\"376\":{\"1x\":\"null.image.768.575.medium.jpg\",\"2x\":\"null.image.1536.1150.medium.jpg\"},\"769\":{\"1x\":\"null.image.1480.538.high.jpg\",\"2x\":\"null.image.2960.1076.high.jpg\"}}},\"meta\":{\"render\":\"dynamic\"}}";
        json = json.replace("{id}", heroTileCompModel.getId());
        assertEquals(json, heroTileCompModel.getJson());
    }

    /**
     * Static json available.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Test
    public void staticJsonAvailable() throws NoSuchFieldException {
        when(defaultComponentServiceImpl.isComponentStatic(anyString())).thenReturn(Boolean.FALSE);
        defaultComponentServiceImpl.updateModel(heroTileCompModel);
        json = "{\"type\":\"heroTile\",\"id\":\"{id}\",\"attributes\":{\"type\":\"\",\"videoURL\":\"\",\"cardAlignment\":\"\",\"logo\":\"\",\"logoAlt\":\"\",\"title\":\"\",\"description\":\"\",\"ctaIcon\":\"\",\"ctaAlt\":\"\",\"ctaLabel\":\"\",\"ctaType\":\"\",\"ctaURL\":\"\",\"image\":{\"alt\":\"\",\"0\":{\"1x\":\"null.image.320.240.low.jpg\",\"2x\":\"null.image.640.480.low.jpg\"},\"376\":{\"1x\":\"null.image.768.575.medium.jpg\",\"2x\":\"null.image.1536.1150.medium.jpg\"},\"769\":{\"1x\":\"null.image.1480.538.high.jpg\",\"2x\":\"null.image.2960.1076.high.jpg\"}}},\"meta\":{\"render\":\"dynamic\"}}";
        json = json.replace("{id}", heroTileCompModel.getId());
        assertEquals(json, heroTileCompModel.getJson());
    }

    /**
     * Static child json available.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Test
    public void staticChildJsonAvailable() throws NoSuchFieldException {
        String json = "{\"type\":\"editorialContent\",\"id\":\"{ecdid}\",\"attributes\":{\"childComponents\":[{\"type\":\"titleH1\",\"id\":\"{tdid}\",\"attributes\":{\"title\":\"\",\"description\":\"\",\"dividerType\":\"\",\"dividerText\":\"\",\"isCampaignHeaderRequired\":\"\",\"campaignHeaderText\":\"\"},\"meta\":{\"render\":\"static\"}}],\"title\":\"\",\"textBlock\":\"\",\"caption\":\"\",\"image\":{\"alt\":\"\",\"0\":{\"1x\":\"null.image.252.302.low.jpg\",\"2x\":\"null.image.504.604.low.jpg\"},\"376\":{\"1x\":\"null.image.396.347.medium.jpg\",\"2x\":\"null.image.792.694.medium.jpg\"},\"769\":{\"1x\":\"null.image.360.416.high.jpg\",\"2x\":\"null.image.720.832.high.jpg\"}}},\"meta\":{\"render\":\"static\"}}";

        EditorialContentComponentModel eccModel = new EditorialContentComponentModel();
        EditorialContentDataModel dataModel = new EditorialContentDataModel();
        TitleDataModel dataModel1 = new TitleDataModel();
        dataModel.setChildComponents(new BaseJson[] { new BaseJson("type", "id", "render", new TitleDataModel()) });
        PrivateAccessor.setField(eccModel, "editorialContent", dataModel);
        PrivateAccessor.setField(eccModel, "titleH1", dataModel1);
        PrivateAccessor.setField(eccModel, "defaultJsonServiceImpl", defaultComponentServiceImpl);
        PrivateAccessor.setField(dataModel1, "resource", resource);
        when(defaultComponentServiceImpl.isComponentStatic("editorialContent")).thenReturn(Boolean.TRUE);
        defaultComponentServiceImpl.updateModel(eccModel);
        json = json.replace("{ecdid}", eccModel.getId()).replace("{tdid}", dataModel.getChildComponents()[0].getId());
        assertEquals(json, eccModel.getJson());
    }

    /**
     * Dynamic child json available.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Test
    public void dynamicChildJsonAvailable() throws NoSuchFieldException {
        String json = "{\"type\":\"editorialContent\",\"id\":\"{ecdid}\",\"attributes\":{\"childComponents\":[{\"type\":\"titleH1\",\"id\":\"{tdid}\",\"attributes\":{\"title\":\"\",\"description\":\"\",\"dividerType\":\"\",\"dividerText\":\"\",\"isCampaignHeaderRequired\":\"\",\"campaignHeaderText\":\"\"},\"meta\":{\"render\":\"dynamic\"}}],\"title\":\"\",\"textBlock\":\"\",\"caption\":\"\",\"image\":{\"alt\":\"\",\"0\":{\"1x\":\"null.image.252.302.low.jpg\",\"2x\":\"null.image.504.604.low.jpg\"},\"376\":{\"1x\":\"null.image.396.347.medium.jpg\",\"2x\":\"null.image.792.694.medium.jpg\"},\"769\":{\"1x\":\"null.image.360.416.high.jpg\",\"2x\":\"null.image.720.832.high.jpg\"}}},\"meta\":{\"render\":\"dynamic\"}}";

        EditorialContentComponentModel eccModel = new EditorialContentComponentModel();
        EditorialContentDataModel dataModel = new EditorialContentDataModel();
        TitleDataModel dataModel1 = new TitleDataModel();
        dataModel.setChildComponents(new BaseJson[] { new BaseJson("type", "id", "render", new TitleDataModel()) });
        PrivateAccessor.setField(eccModel, "editorialContent", dataModel);
        PrivateAccessor.setField(eccModel, "titleH1", dataModel1);
        PrivateAccessor.setField(dataModel1, "resource", resource);
        PrivateAccessor.setField(eccModel, "defaultJsonServiceImpl", defaultComponentServiceImpl);
        when(defaultComponentServiceImpl.isComponentStatic(anyString())).thenReturn(Boolean.FALSE);
        defaultComponentServiceImpl.updateModel(eccModel);
        json = json.replace("{ecdid}", eccModel.getId()).replace("{tdid}", dataModel.getChildComponents()[0].getId());
        assertEquals(json, eccModel.getJson());
    }

    /**
     * Component html.
     */
    @Test
    public void componentHtml() {
        EditorialContentComponentModel slingModel = new EditorialContentComponentModel();
        when(renderingService.renderComponentHTML(slingModel)).thenReturn("html");
        assertEquals("html", defaultComponentServiceImpl.getComponentHtml(slingModel));
    }

    /**
     * Invalid model update.
     */
    @Override
    @Test
    public void testUpdateSlingModel() {
        AbstractSlingModel slingModel = new EditorialContentDataModel();
        defaultComponentServiceImpl.updateModel(slingModel);
        assertNotNull(slingModel);
    }
}

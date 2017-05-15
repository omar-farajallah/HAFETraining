package com.carnival.platform.services.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.component.TitleComponentModel;
import com.carnival.platform.models.data.TitleDataModel;
import com.carnival.platform.services.NashornEngineService;

import junitx.util.PrivateAccessor;

@RunWith(MockitoJUnitRunner.class)
public class RenderingServiceImplTest {

    @InjectMocks
    private RenderingServiceImpl renderingServiceImpl;

    @Mock
    private NashornEngineService nashornEngineService;
    
    @Mock
    private Resource resource;

    @Mock
    private SlingHttpServletRequest request;
    
    private TitleComponentModel slingModel;

    private List<String> renderingTypes;

    private Map<String, String> mockValueMapData;
    
    @Before
    public void setUp() throws Exception {
        mockValueMapData = new HashMap<String, String>();
        renderingTypes = new ArrayList<>();
        mockValueMapData.put(RenderingServiceImpl.RENDERING_TYPE, "destinationtile, title, herotile");
        renderingTypes.add("destinationtile");
        renderingTypes.add("title");
        PrivateAccessor.setField(renderingServiceImpl, "renderingTypes", renderingTypes);
        
        slingModel = new TitleComponentModel();
        PrivateAccessor.setField(slingModel, "titleH1", new TitleDataModel());
        PrivateAccessor.setField(slingModel, "slingRequest", request);
        
        when(request.getResource()).thenReturn(resource);
        when(resource.getResourceType()).thenReturn("");
    }

    /**
     * Test update sling model.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testDoActivate() throws Exception {

        renderingServiceImpl.activate(mockValueMapData);
        Assert.assertFalse(renderingServiceImpl.isComponentStatic("storyrotator"));
    }

    @Test
    public void testIsComponentStatic() throws Exception {

        Assert.assertTrue(renderingServiceImpl.isComponentStatic("destinationtile"));
        Assert.assertFalse(renderingServiceImpl.isComponentStatic("storyrotator"));
    }

    @Test
    public void testRenderComponentHTML() throws Exception {
        when(resource.getResourceType()).thenReturn("/apps/carnival/platform/components/content/article");
        String html = renderingServiceImpl.renderComponentHTML(slingModel);
        Assert.assertNull(html);
    }

    @Test
    public void testRenderComponentHTMLRelativeResourceType() throws Exception {
        when(resource.getResourceType()).thenReturn("carnival/platform/components/content/article");
        String html = renderingServiceImpl.renderComponentHTML(slingModel);
        Assert.assertNull(html);
    }
}

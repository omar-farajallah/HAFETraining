package com.carnival.platform.servlets;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.services.LabelConfigurationService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

/**
 * The Class SearchFiltersGeneratorServletTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class LabelConfigValuesGeneratorServletTest {

    /** The search filter servlet. */
    @InjectMocks
    private LabelConfigValuesGeneratorServlet searchFiltersGeneratorServlet;

    /** The sling request. */
    @Mock
    private SlingHttpServletRequest slingRequest;

    /** The sling response. */
    @Mock
    private SlingHttpServletResponse slingResponse;

    /** The resolver. */
    @Mock
    private ResourceResolver resolver;

    /** The page manager. */
    @Mock
    private PageManager pageManager;

    /** The page. */
    @Mock
    private Page page;

    /** The parent page 1. */
    @Mock
    private Page parentPage1;

    /** The parent page 2. */
    @Mock
    private Page parentPage2;

    /** The resource. */
    @Mock
    private Resource resource;

    /** The value map. */
    @Mock
    private ValueMap valueMap;

    /** The mock value map data. */
    private Map<String, String> mockValueMapData;

    /** The label configuration service. */
    @Mock
    private LabelConfigurationService labelConfigurationService;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        mockValueMapData = new HashMap<String, String>();
        mockValueMapData.put("arrivessText", "Arrives");
        mockValueMapData.put("departsText", "Departs From");
        mockValueMapData.put("resetFilterCopy", "Reset Filter");
    }

    /**
     * Test do get with query builder.
     *
     * @throws ServletException
     *             the servlet exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testDoGetWithQueryBuilder() throws ServletException, IOException {
        when(resolver.getResource("/content/carnival/sample-page/jcr:content")).thenReturn(resource);
        when(resolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(resource.getPath()).thenReturn("/content/carnival/sample-page");
        when(pageManager.getContainingPage("/content/carnival/sample-page")).thenReturn(page);
        when(page.getAbsoluteParent(ApplicationConstants.INT_1)).thenReturn(parentPage1);
        when(parentPage1.getName()).thenReturn("hal");
        when(page.getAbsoluteParent(ApplicationConstants.INT_2)).thenReturn(parentPage2);
        when(parentPage2.getName()).thenReturn("en_us");

        MyRequest request = new MyRequest(slingRequest);
        when(request.getResource().getResourceType()).thenReturn("apps/labelConfigMap.search.json");

        valueMap = new ValueMapDecorator(new HashMap<String, Object>());
        List<String> sampleList = new ArrayList<>();
        sampleList.add("text");
        sampleList.add("sample");
        valueMap.put("text", "Select");
        valueMap.put("value", "select");

        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource);
        
        Iterator<Resource> resourceItr = resourceList.iterator();
        Iterable<Resource> resourceItrable = new Iterable<Resource>() {

            @Override
            public Iterator<Resource> iterator() {
                return resourceItr;
            }
        };
        when(resource.adaptTo(ValueMap.class)).thenReturn(valueMap);
        when(labelConfigurationService.getLabelValuesByComponentResource("hal", "en_us", "search")).thenReturn(resource);
        when(labelConfigurationService.getPropertyValues("search")).thenReturn(sampleList);
        when(resource.getChildren()).thenReturn(resourceItrable);
        
        searchFiltersGeneratorServlet.doGet(request, slingResponse);
        Iterator<Resource> sources = ((SimpleDataSource) request.getAttribute(DataSource.class.getName())).iterator();
        List<Resource> output = new ArrayList<>();
        sources.forEachRemaining(resource -> output.add(resource));
        assertTrue(output.size() > 0);
    }

    /**
     * The Class MyRequest.
     */
    private class MyRequest extends SlingHttpServletRequestWrapper {

        /** The attributes. */
        Map<String, Object> attributes = new LinkedHashMap<>();

        /**
         * Instantiates a new my request.
         *
         * @param wrappedRequest
         *            the wrapped request
         */
        public MyRequest(SlingHttpServletRequest wrappedRequest) {
            super(wrappedRequest);
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.servlet.http.HttpServletRequestWrapper#getRequestURI()
         */
        @Override
        public String getRequestURI() {
            return "/mnt/override/apps/carnival/platform/components/content/search/_cq_dialog.html/content/carnival/sample-page/jcr:content/contentpar/search";
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper#
         * getResourceResolver()
         */
        @Override
        public ResourceResolver getResourceResolver() {
            return resolver;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper#
         * getResource()
         */
        @Override
        public Resource getResource() {
            return resource;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.servlet.ServletRequestWrapper#getAttribute(java.lang.String)
         */
        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.servlet.ServletRequestWrapper#setAttribute(java.lang.String,
         * java.lang.Object)
         */
        @Override
        public void setAttribute(String name, Object o) {
            attributes.put(name, o);
        }
    }
}

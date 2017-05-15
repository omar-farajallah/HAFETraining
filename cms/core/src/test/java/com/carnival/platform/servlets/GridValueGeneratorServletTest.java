package com.carnival.platform.servlets;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.data.CarouselEditorialDataModel;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * The Class GridValueGeneratorServletTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class GridValueGeneratorServletTest {

    /** The grid servlet. */
    @InjectMocks
    private GridValueGeneratorServlet gridServlet;

    /** The query builder. */
    @Mock
    private QueryBuilder queryBuilder;

    /** The sling request. */
    @Mock
    private SlingHttpServletRequest slingRequest;

    /** The sling response. */
    @Mock
    private SlingHttpServletResponse slingResponse;

    /** The resolver. */
    @Mock
    private ResourceResolver resolver;

    /** The resource. */
    @Mock
    private Resource resource;

    /** The session. */
    @Mock
    private Session session;

    /** The query. */
    @Mock
    private Query query;

    /** The search results. */
    @Mock
    private SearchResult searchResults;

    /** The value map. */
    @Mock
    private ValueMap valueMap;

    @Test
    public void testDoGetWithNullQueryBuilder() throws ServletException, IOException {
        when(resolver.adaptTo(Session.class)).thenReturn(session);
        MyRequest request = new MyRequest(slingRequest);
        gridServlet.doGet(request, slingResponse);
        Iterator<Resource> sources = ((SimpleDataSource) request.getAttribute(DataSource.class.getName())).iterator();
        List<Resource> output = new ArrayList<>();
        sources.forEachRemaining(resource -> output.add(resource));
        assertEquals(1, output.size());
    }

    @Test
    public void testDoGetWithQueryBuilder() throws ServletException, IOException {
        when(resolver.adaptTo(Session.class)).thenReturn(session);
        when(resolver.adaptTo(QueryBuilder.class)).thenReturn(queryBuilder);
        when(queryBuilder.createQuery(Mockito.any(PredicateGroup.class), Mockito.any(Session.class))).thenReturn(query);
        when(query.getResult()).thenReturn(searchResults);
        when(searchResults.getResources()).thenReturn(Arrays.asList(resource).iterator());
        MyRequest request = new MyRequest(slingRequest);
        gridServlet.doGet(request, slingResponse);
        Iterator<Resource> sources = ((SimpleDataSource) request.getAttribute(DataSource.class.getName())).iterator();
        List<Resource> output = new ArrayList<>();
        sources.forEachRemaining(resource -> output.add(resource));
        assertEquals(1, output.size());
    }

    @Test
    public void testDoGetWithChildValueMap() throws ServletException, IOException {
        when(resolver.adaptTo(Session.class)).thenReturn(session);
        when(resolver.adaptTo(QueryBuilder.class)).thenReturn(queryBuilder);
        when(queryBuilder.createQuery(Mockito.any(PredicateGroup.class), Mockito.any(Session.class))).thenReturn(query);
        when(query.getResult()).thenReturn(searchResults);
        when(searchResults.getResources()).thenReturn(Arrays.asList(resource).iterator());
        when(resource.getChild(CarouselEditorialDataModel.COMPONENT_NAME)).thenReturn(resource);
        when(resource.adaptTo(ValueMap.class)).thenReturn(valueMap);
        when(valueMap.get(ApplicationConstants.TITLE, String.class)).thenReturn("Editorial");
        when(resource.getPath()).thenReturn("/content/carnival/sample-page/jcr:content/headerpar/gridimagecopyblock");
        MyRequest request = new MyRequest(slingRequest);
        gridServlet.doGet(request, slingResponse);
        Iterator<Resource> sources = ((SimpleDataSource) request.getAttribute(DataSource.class.getName())).iterator();
        List<Resource> output = new ArrayList<>();
        sources.forEachRemaining(resource -> output.add(resource));
        assertEquals(2, output.size());
    }

    private class MyRequest extends SlingHttpServletRequestWrapper {
        Map<String, Object> attributes = new LinkedHashMap<>();

        public MyRequest(SlingHttpServletRequest wrappedRequest) {
            super(wrappedRequest);
        }

        @Override
        public String getRequestURI() {
            return "/mnt/override/apps/carnival/platform/components/content/gridImageCopyBlock/_cq_dialog.html/content/carnival/sample-page/jcr:content/headerpar/gridimagecopyblock";
        }

        @Override
        public ResourceResolver getResourceResolver() {
            return resolver;
        }

        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        @Override
        public void setAttribute(String name, Object o) {
            attributes.put(name, o);
        }
    }
}

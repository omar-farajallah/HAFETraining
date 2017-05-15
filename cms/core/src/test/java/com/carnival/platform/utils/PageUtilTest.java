package com.carnival.platform.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.jcr.resource.JcrResourceConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.data.CarouselEditorialDataModel;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * The Class PageUtilTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class PageUtilTest {

    /** The resolver. */
    @Mock
    ResourceResolver resolver;

    /** The resolver. */
    @Mock
    Resource resource;

    /** The query. */
    @Mock
    Query query;

    /** The query builder. */
    @Mock
    QueryBuilder queryBuilder;

    /** The search results. */
    @Mock
    SearchResult searchResults;

    /** The session. */
    @Mock
    Session session;

    /**
     * Setup.
     */
    @Before
    public void setUp() {

        resolver = mock(ResourceResolver.class);
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource);
        Iterator<Resource> resourceItr = resourceList.iterator();
        // new try - BDDMockito
        given(queryBuilder.createQuery(Mockito.any(PredicateGroup.class), Mockito.any(Session.class))).willReturn(query);
        given(query.getResult()).willReturn(searchResults);
        query = mock(Query.class);
        session = mock(Session.class);
        queryBuilder = mock(QueryBuilder.class);
        searchResults = mock(SearchResult.class);
        when(query.getResult()).thenReturn(searchResults);
        when(searchResults.getResources()).thenReturn(resourceItr);
    }

    /**
     * Test get page path for dialog ref.
     */
    @Test
    public void testGetPagePathForDialogRef() {
        String requestUri = "/mnt/override/apps/carnival/platform/components/content/gridImageCopyBlock/_cq_dialog.html/content/carnival/sample-page/jcr:content/headerpar/gridimagecopyblock";
        String path = PageUtil.getPagePathForDialogRef(requestUri);
        assertEquals(path, "/content/carnival/sample-page/jcr:content");
    }

    /**
     * Test get query results.
     */
    @Test
    public void testGetQueryResults() {
        Map<String, Object> queryMap = new HashMap<>();
        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("1_property", JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY);
        propertiesMap.put("1_property.value", "carnival/platform/components/content/".concat(CarouselEditorialDataModel.COMPONENT_NAME));

        queryMap.put(ApplicationConstants.QUERY_PATH, "/content/carnival/sample-page/jcr:content");
        queryMap.put(ApplicationConstants.QUERY_NODE_TYPE, JcrConstants.NT_UNSTRUCTURED);
        queryMap.put(ApplicationConstants.QUERY_LIMIT, "-1");
        List<Resource> queryResourceList = PageUtil.getQueryResults(resolver, queryMap);
        assertTrue(queryResourceList.size() <= 0);

        when(resolver.adaptTo(QueryBuilder.class)).thenReturn(queryBuilder);
        given(queryBuilder.createQuery(Mockito.any(PredicateGroup.class), Mockito.any(Session.class))).willReturn(query);

        queryResourceList = PageUtil.getQueryResults(resolver, queryMap);
        assertTrue(queryResourceList.size() > 0);

    }

    /**
     * Test get query with results.
     */
    @Test
    public void testGetQueryWithResults() {
        Map<String, Object> queryMap = new HashMap<>();
        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("1_property", JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY);
        propertiesMap.put("1_property.value", "carnival/platform/components/content/".concat(CarouselEditorialDataModel.COMPONENT_NAME));

        queryMap.put(ApplicationConstants.QUERY_PATH, "/content/carnival/sample-page/jcr:content");
        queryMap.put(ApplicationConstants.QUERY_NODE_TYPE, JcrConstants.NT_UNSTRUCTURED);
        queryMap.put(ApplicationConstants.QUERY_LIMIT, "-1");
        when(resolver.adaptTo(QueryBuilder.class)).thenReturn(queryBuilder);
        given(queryBuilder.createQuery(Mockito.any(PredicateGroup.class), Mockito.any(Session.class))).willReturn(query);

        List<Resource> queryResourceList = PageUtil.getQueryResults(resolver, queryMap);
        assertTrue(queryResourceList.size() > 0);

    }

    /**
     * Test shorten url.
     */
    @Test
    public void testShortenUrl() {
        String url = "/content/hal/en/story";
        when(resolver.map(url)).thenReturn("/hal/en/story");
        String shortenUrl = PageUtil.shortenUrl(resolver, url);
        assertEquals(shortenUrl, "/hal/en/story.html");

        // covering DAM case for coverage
        url = "/content/dam/hal/en/desktop.png";
        shortenUrl = PageUtil.shortenUrl(resolver, url);
        assertEquals(shortenUrl, url);

        // covering external URL for case coverage
        url = "http://carnival.com/story.html";
        shortenUrl = PageUtil.shortenUrl(resolver, url);
        assertEquals(shortenUrl, url);
    }
}

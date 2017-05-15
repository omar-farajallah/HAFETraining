package com.carnival.platform.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.constants.ApplicationConstants;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

/**
 * The Class PageUtils.
 * 
 * @author gnatas
 * 
 */
public final class PageUtil {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(PageUtil.class);

    /**
     * Instantiates a new page util.
     */
    private PageUtil() {
        // Hide default constructor
    }

    /**
     * Gets the query results.
     *
     * @param resolver
     *            the resolver
     * @param predicateMap
     *            the predicate map val
     * @return the query results
     */
    public static List<Resource> getQueryResults(ResourceResolver resolver, Map<String, Object> predicateMap) {
        List<Resource> resouceList = new ArrayList<>();
        final Session session = resolver.adaptTo(Session.class);
        final QueryBuilder queryBuilder = resolver.adaptTo(QueryBuilder.class);
        if (queryBuilder != null) {
            final Query query = queryBuilder.createQuery(PredicateGroup.create(predicateMap), session);
            final SearchResult result = query.getResult();
            result.getResources().forEachRemaining(resouceList::add);
            LOG.debug("resource list size - {}", resouceList.size());
        }
        return resouceList;
    }

    /**
     * Gets the page path for dialog ref.
     *
     * @param requestURI
     *            the request URI
     * @return the page path for dialog ref
     */
    public static String getPagePathForDialogRef(String requestURI) {
        final int startIndex = requestURI.indexOf(ApplicationConstants.HTML) + ApplicationConstants.INT_4;
        final int endIndex = requestURI.indexOf(JcrConstants.JCR_CONTENT) + JcrConstants.JCR_CONTENT.length();
        return requestURI.substring(startIndex, endIndex);
    }

    /**
     * Shorten the given URL if it is internal AEM URL also append
     * <code> ApplicationConstants.HTML_EXTN </code> otherwise return same URL
     *
     * @param resolver
     *            the resolver
     * @param url
     *            the url
     * @return the string
     */
    public static String shortenUrl(ResourceResolver resolver, String url) {
        String updatedUrl = url;
        if (StringUtils.startsWith(url, ApplicationConstants.BASE_PATH) && (!StringUtils.contains(url, ApplicationConstants.DAM_PATH))) {
            String shortUrl = resolver.map(url);
            updatedUrl = StringUtils.appendIfMissing(shortUrl, ApplicationConstants.HTML_EXTN);
        }
        return updatedUrl;
    }
    
    /**
     * Gets the brand name for dialog ref.
     *
     * @param requestURI
     *            the request URI
     * @return the brand name for dialog ref
     */
    public static String getBrandNameForCurrentPage(String requestURI, String texturePath) {
        final String pagePathURI = getPagePathForDialogRef(requestURI);
        String[] brandName = pagePathURI.split(Pattern.quote("/"));
        return "hal".equals(brandName[2])? "platform": brandName[2];
    }

}

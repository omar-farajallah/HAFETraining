package com.carnival.platform.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.services.LabelConfigurationService;
import com.carnival.platform.utils.PageUtil;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

/**
 * The Class SearchFiltersGeneratorServlet.
 *
 * This servlet is used to list out the drop down filters along with label key
 * and value from the Label configuration.
 */
@SlingServlet(resourceTypes = { "apps/labelConfigMap.search.searchfilter.json" , "apps/labelConfigMap.search.subfilter.json" })
public class LabelConfigValuesGeneratorServlet extends SlingSafeMethodsServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4956902639136729218L;

    /** The label configuration service. */
    @Reference
    private LabelConfigurationService labelConfigurationService;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(LabelConfigValuesGeneratorServlet.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.sling.api.servlets.SlingSafeMethodsServlet#doGet(org.apache.
     * sling.api.SlingHttpServletRequest,
     * org.apache.sling.api.SlingHttpServletResponse)
     */
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        String componentName = request.getResource().getResourceType().split("\\.")[1];
        String filterNodeName = request.getResource().getResourceType().split("\\.")[2];
        LOGGER.debug("Inside servlet - {}", componentName);

        final List<Resource> resourceList = new ArrayList<>();
        final ResourceResolver resolver = request.getResourceResolver();
        final String path = PageUtil.getPagePathForDialogRef(request.getRequestURI());
        Resource resource = resolver.getResource(path);

        Resource labelFilterResource = labelConfigurationService.getLabelValuesByComponentResource(
                getAbsoluteParentName(resolver, resource, ApplicationConstants.INT_1),
                getAbsoluteParentName(resolver, resource, ApplicationConstants.INT_2), componentName);

        if (!"json".equals(filterNodeName) && null != labelFilterResource) {
            labelFilterResource = labelFilterResource.getChild(filterNodeName);

        }
        if (null != labelFilterResource) {
            Iterable<Resource> resourceItr = labelFilterResource.getChildren();
            ValueMap valueMap = new ValueMapDecorator(new HashMap<String, Object>());
            valueMap.put("text", "Select");
            valueMap.put("value", "select");
            resourceList.add(new ValueMapResource(resolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, valueMap));
            resourceItr.forEach(childResource -> {
                ValueMap childValueMap = new ValueMapDecorator(new HashMap<String, Object>());
                ValueMap childResourceValueMap = childResource.adaptTo(ValueMap.class);
                childValueMap.put("text", (String) childResourceValueMap.get("filterTitle"));
                childValueMap.put("value", childResourceValueMap.get("filterAPIKey"));
                resourceList.add(new ValueMapResource(resolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, childValueMap));
            });
        }
        DataSource dataSoruce = new SimpleDataSource(resourceList.iterator());
        request.setAttribute(DataSource.class.getName(), dataSoruce);

    }

    /**
     * returns the absolute parent based on input page level.
     *
     * @param resourceResolver
     *            the resource resolver
     * @param resource
     *            the resource
     * @param level
     *            the level
     * @return page name
     */
    String getAbsoluteParentName(ResourceResolver resourceResolver, Resource resource, int level) {
        String pageName = StringUtils.EMPTY;
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        // get the absolute parent level to know the 'brand' and 'locale'
        if (null != pageManager) {
            Page page = pageManager.getContainingPage(resource.getPath());
            if (null != page) {
                Page parnetPage = page.getAbsoluteParent(level);
                if (null != parnetPage) {
                    pageName = parnetPage.getName();
                }
            }
        }
        return pageName;
    }

}

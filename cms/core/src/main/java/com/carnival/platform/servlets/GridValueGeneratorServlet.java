package com.carnival.platform.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.jcr.resource.JcrResourceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.data.CarouselEditorialDataModel;
import com.carnival.platform.utils.PageUtil;
import com.day.cq.commons.jcr.JcrConstants;

/**
 * The Class GridValueGeneratorServlet.
 *
 * @author spati9 This class is used to retrieve the C041 components in the
 *         current invoking page. It is used to populate the drop-down in C040
 *         component
 */
@SlingServlet(resourceTypes = "component/c040")
public class GridValueGeneratorServlet extends SlingSafeMethodsServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4956902639136729218L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GridValueGeneratorServlet.class);

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
        LOGGER.debug("Inside servlet");

        final List<Resource> resourceList = new ArrayList<>();
        final String path = PageUtil.getPagePathForDialogRef(request.getRequestURI());
        final ResourceResolver resolver = request.getResourceResolver();
        final Map<String, String> resourceData = populateResource(resolver, path);
        final Set<Entry<String, String>> list = resourceData.entrySet();
        ValueMap valueMap = new ValueMapDecorator(new HashMap<String, Object>());
        valueMap.put("text", "Select");
        valueMap.put("value", "select");

        resourceList.add(new ValueMapResource(resolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, valueMap));
        for (final Entry<String, String> data : list) {
            valueMap = new ValueMapDecorator(new HashMap<String, Object>());
            valueMap.put("text", data.getKey());
            valueMap.put("value", data.getValue());
            resourceList.add(new ValueMapResource(resolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, valueMap));
        }

        DataSource dataSoruce = new SimpleDataSource(resourceList.iterator());
        request.setAttribute(DataSource.class.getName(), dataSoruce);

    }

    /**
     * Populate resource.
     *
     * @param resolver
     *            the resolver
     * @param path
     *            the path
     * @return the map
     */
    private Map<String, String> populateResource(ResourceResolver resolver, String path) {
        final Map<String, String> finalMap = new HashMap<>();

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("1_property", JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY);
        queryMap.put("1_property.value",
                "carnival/platform/components/content/".concat(CarouselEditorialDataModel.COMPONENT_NAME));
        queryMap.put(ApplicationConstants.QUERY_PATH, path);
        queryMap.put(ApplicationConstants.QUERY_NODE_TYPE, JcrConstants.NT_UNSTRUCTURED);
        queryMap.put(ApplicationConstants.QUERY_LIMIT, "-1");

        List<Resource> queryResourceList = PageUtil.getQueryResults(resolver, queryMap);
        queryResourceList.forEach(resultResouce -> {
            final Resource resource = resultResouce.getChild(CarouselEditorialDataModel.COMPONENT_NAME);
            if (resource != null) {
                final ValueMap valueMap = resource.adaptTo(ValueMap.class);
                final String name = valueMap.get(ApplicationConstants.TITLE, String.class);
                final String templatePath = resource.getPath();
                finalMap.put(name, templatePath);
            }
        });
        return finalMap;
    }
}

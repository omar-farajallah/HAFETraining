/**
 * 
 */
package com.carnival.platform.servlets;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.component.AbstractBaseTest;

/**
 * The Class BrandTextureGeneratorServletTest.
 *
 * @author ppriy4
 */
@RunWith(MockitoJUnitRunner.class)
public class BrandTextureGeneratorServletTest extends AbstractBaseTest {

    /** The grid Servlet. */
    @InjectMocks
    private BrandTextureGeneratorServlet textureServlet;

    /** The sources. */
    private Iterator<Resource> sources;

    /** The resourcelist. */
    private List<Resource> resourcelist = new ArrayList<>();

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractBaseTest#setUp()
     */
    @Override
    public void setUp() throws Exception {
        when(resource.adaptTo(ValueMap.class)).thenReturn(valueMap);
        when(resource.getPath()).thenReturn("/content/carnival/sample-page/jcr:content/headerpar/gridimagecopyblock");
        when(resourceResolver.getResource("/etc/designs/carnival/carnival/images/texture")).thenReturn(resource);

        resourcelist.add(resource);
        when(resource.listChildren()).thenReturn(resourcelist.iterator());
        when(resource.getName()).thenReturn("blackTexture.txt");
        when(resource.getPath()).thenReturn("/etc/designs/carnival/carnival/images/texture/blackTexture.txt");
    }

    /**
     * Test do get with child value map.
     *
     * @throws ServletException
     *             the servlet exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testDoGetWithChildValueMap() throws ServletException, IOException {

        MyRequest request = new MyRequest(slingRequest);
        textureServlet.doGet(request, slingResponse);
        sources = resourcelist.iterator();
        List<Resource> output = new ArrayList<>();
        sources.forEachRemaining(resource -> output.add(resource));
        Assert.assertNotNull(output);
    }

    /**
     * The Class MyRequest.
     */
    private class MyRequest extends SlingHttpServletRequestWrapper {

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
            return "/mnt/override/apps/carnival/platform/components/content/gridImageCopyBlock/_cq_dialog.html/content/carnival/sample-page/jcr:content/headerpar/gridimagecopyblock";
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper#
         * getResourceResolver()
         */
        @Override
        public ResourceResolver getResourceResolver() {
            return resourceResolver;
        }

    }

}

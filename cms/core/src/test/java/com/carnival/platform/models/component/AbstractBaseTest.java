package com.carnival.platform.models.component;

import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Before;
import org.mockito.Mock;

import com.carnival.platform.services.LabelConfigurationService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

/**
 * The Class AbstractBaseTest.
 * 
 * @author gnatas
 */
public abstract class AbstractBaseTest {

    /** The resource resolver. */
    @Mock
    public ResourceResolver resourceResolver;

    /** The resource. */
    @Mock
    public Resource resource;

    /** The page mang. */
    @Mock
    public PageManager pageMang;

    /** The label configuration service. */
    @Mock
    public LabelConfigurationService labelConfigurationService;

    /** The page. */
    @Mock
    public Page page;

    /** The value map. */
    @Mock
    public ValueMap valueMap;

    /** The parent page 1. */
    @Mock
    public Page parentPage1;

    /** The parent page 2. */
    @Mock
    public Page parentPage2;
    
    @Mock
    public Session session;
    
    @Mock
    public SlingHttpServletRequest slingRequest;
    
    @Mock
    public SlingHttpServletResponse slingResponse;

    @Before
    abstract public void setUp() throws Exception;

}

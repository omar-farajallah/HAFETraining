package com.carnival.platform.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.service.component.ComponentContext;

import com.day.cq.commons.jcr.JcrConstants;

import junitx.util.PrivateAccessor;

@RunWith(MockitoJUnitRunner.class)
public class LabelConfigurationServiceTest {

    private static final String LABEL_CONFIG_PATH = "/content/hal/en-us";

    private static final String OF_LABEL_VALUE = "of";

    private static final String OF_LABEL = "ofLabel";

    private static final String LABEL_CONFIG_URL = "/content/{brand}/{locale}";

    /** The resource resolver. */
    @Mock
    private ResourceResolver resourceResolver;

    /** The resource. */
    @Mock
    private Resource resource;

    /** The Child resource. */
    @Mock
    private Resource childResource;

    @Mock
    private Iterable<Resource> labelContentResource;

    @Mock
    private Iterator<Resource> labelContentIterator;

    @Mock
    private ResourceResolver loginServiceResolver;

    @Mock
    private ValueMap labelModel;

    @Mock
    private ComponentContext componentContext;

    @InjectMocks
    private LabelConfigurationServiceImpl labelConfigurationServiceImpl;

    private Map<String, Object> mockValueMapData;

    @Before
    public void setUp() throws Exception {
        mockValueMapData = new HashMap<String, Object>();
        mockValueMapData.put(OF_LABEL, OF_LABEL_VALUE);
        mockValueMapData.put("contact", "Contact");
        mockValueMapData.put("login", "Login");
        mockValueMapData.put("arrivessText", "Arrives");
        mockValueMapData.put("departsText", "Departs From");
        mockValueMapData.put("resetFilterCopy", "Reset Filter");
    }

    /**
     * Test get label values.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testGetLabelValues() throws Exception {

        PrivateAccessor.setField(labelConfigurationServiceImpl, "labelPagePathPattern", LABEL_CONFIG_URL);
        when(loginServiceResolver.getResource(LABEL_CONFIG_PATH)).thenReturn(resource);
        when(resource.getChild(JcrConstants.JCR_CONTENT)).thenReturn(resource);
        when(resource.getValueMap()).thenReturn(labelModel);
        Set<Entry<String, Object>> entrySet = mockValueMapData.entrySet();
        when(labelModel.entrySet()).thenReturn(entrySet);
        when(resource.getChildren()).thenReturn(labelContentResource);

        Map<String, String> labelValue = labelConfigurationServiceImpl.getLabelValues("hal", "en-us", OF_LABEL);

        Assert.assertEquals(labelValue.get(OF_LABEL), OF_LABEL_VALUE);
    }

    /**
     * Test get label value.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testGetLabelValue() throws Exception {

        PrivateAccessor.setField(labelConfigurationServiceImpl, "labelPagePathPattern", LABEL_CONFIG_URL);
        when(loginServiceResolver.getResource(LABEL_CONFIG_PATH)).thenReturn(resource);
        when(resource.getChild(JcrConstants.JCR_CONTENT)).thenReturn(resource);
        when(resource.getValueMap()).thenReturn(labelModel);
        Set<Entry<String, Object>> entrySet = mockValueMapData.entrySet();
        when(labelModel.entrySet()).thenReturn(entrySet);
        when(resource.getChildren()).thenReturn(labelContentResource);

        String labelValue = labelConfigurationServiceImpl.getLabelValue("hal", "en-us", OF_LABEL);

        Assert.assertEquals(labelValue, OF_LABEL_VALUE);
    }

    /**
     * Test to getLabelByComponent Name
     * 
     * @throws Exception
     */
    @Test
    public void testGetLabelValuesForComponent() throws Exception {

        PrivateAccessor.setField(labelConfigurationServiceImpl, "labelPagePathPattern", LABEL_CONFIG_URL);
        when(loginServiceResolver.getResource(LABEL_CONFIG_PATH)).thenReturn(resource);
        when(resource.getChild(JcrConstants.JCR_CONTENT)).thenReturn(resource);
        List<Resource> resourceList = new ArrayList<Resource>();
        resourceList.add(childResource);
        when(resource.listChildren()).thenReturn(resourceList.iterator());

        when(childResource.getName()).thenReturn("search");
        when(childResource.getValueMap()).thenReturn(labelModel);
        Set<Entry<String, Object>> entrySet = mockValueMapData.entrySet();
        when(labelModel.entrySet()).thenReturn(entrySet);

        Resource labelResource = labelConfigurationServiceImpl.getLabelValuesByComponentResource("hal", "en-us", "search");
        Assert.assertNotNull(labelResource);
    }

    @Test
    public void testDoActivate() {
        Dictionary<String, String> propeties = new Hashtable<>();
        propeties.put("commonLabelPagePath", "/content/{brand}/{locale}");
        when(componentContext.getProperties()).thenReturn(propeties);
        labelConfigurationServiceImpl.doActivate(componentContext);
        assertNotNull(propeties.get("commonLabelPagePath"));
    }
}

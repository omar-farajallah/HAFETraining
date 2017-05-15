package com.carnival.platform.services.impl;


import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

import com.carnival.platform.constants.ApplicationConstants;
import com.day.cq.commons.jcr.JcrConstants;

import junitx.util.PrivateAccessor;

@RunWith(MockitoJUnitRunner.class)
public class NashornEngineServiceImplTest {

    /** The resource resolver. */
    @Mock
    private ResourceResolver resourceResolver;

    /** The resource. */
    @Mock
    private Resource contentResource;
    
    /** The resource. */
    @Mock
    private Resource loginResource;
    
    @Mock
    private ValueMap contentMap;
    
    @Mock
    private InputStream file;
    
    @Mock
    private ResourceResolver loginServiceResolver;
    
    @Mock
    private Reader reader;
    
    @Mock
    private Invocable invocable;

    @Mock
    private Object reactHTMLObject;
    
    @Mock
    private ComponentContext context;
    
    @Mock
    private BundleContext bundleContext;
    
    @Mock
    private ServiceReference serviceReference;
    
    @Mock
    private ResourceResolverFactory resourceResolverFactory;
    
    @InjectMocks
    private NashornEngineServiceImpl nashornEngineServiceImpl;
    
    private ScriptEngine nashorn;
    
    private static final String NASHORON_ENGINE_NAME = "nashorn";
    
    @Before
    public void setUp() throws ScriptException, NoSuchMethodException, NoSuchFieldException, LoginException{
        
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager(null);
        nashorn = scriptEngineManager.getEngineByName(NASHORON_ENGINE_NAME);
        String serverJs = "var server=(function renderReact(props) {})";
        nashorn.eval(serverJs);

        when(context.getBundleContext()).thenReturn(bundleContext);
        when(bundleContext.getServiceReference(ResourceResolverFactory.class.getName())).thenReturn(serviceReference);
        when(bundleContext.getService(serviceReference)).thenReturn(resourceResolverFactory);
        when(resourceResolverFactory.getServiceResourceResolver(Matchers.any())).thenReturn(loginServiceResolver);
        
        when(loginServiceResolver.getResource(ApplicationConstants.POLYFILL_SCRIPT)).thenReturn(loginResource);
        when(loginServiceResolver.getResource(ApplicationConstants.REACT_SERVER_SCRIPT)).thenReturn(loginResource);
        when(loginResource.getChild(JcrConstants.JCR_CONTENT)).thenReturn(contentResource);
        when(loginResource.getChild(JcrConstants.JCR_CONTENT)).thenReturn(contentResource);
        when(contentResource.getValueMap()).thenReturn(contentMap);
        when(contentMap.get(JcrConstants.JCR_DATA, InputStream.class)).thenReturn(file); 
        
        nashornEngineServiceImpl.activate(context);
    }
    
    @Test
    public void testHTML() throws ScriptException, NoSuchFieldException {

        String serverJs = "var server=(function renderReact(props) {})";
        nashorn.eval(serverJs);
        PrivateAccessor.setField(nashornEngineServiceImpl, "nashorn", nashorn);
        String html = nashornEngineServiceImpl.evaluateSSRComponentHTML("testComponent", "testJSON");
        Assert.assertNotNull(html);
    }
    
    @After
    public void testDeactivate(){
        nashornEngineServiceImpl.deActivate(context);
    }


}

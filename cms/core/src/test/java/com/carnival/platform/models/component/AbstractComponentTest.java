package com.carnival.platform.models.component;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.day.cq.wcm.api.PageManager;

/**
 * The Class AbstractBaseComponentTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractComponentTest extends AbstractBaseTest {

    /**
     * Test component model data.
     *
     * @throws NoSuchFieldException
     *             the no such field exception
     */
    @Test
    public abstract void testComponentModelData() throws NoSuchFieldException;

    /**
     * Test component name.
     * 
     * @throws NoSuchFieldException
     */
    @Test
    public abstract void testComponentName() throws NoSuchFieldException;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");
        when(resourceResolver.map("/content/carnival/hal/en")).thenReturn("/hal/en.html");
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageMang);
    }
}

/**
 * 
 */
package com.carnival.platform.services.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.carnival.platform.models.component.AbstractBaseTest;
import com.day.cq.wcm.api.PageManager;

/**
 * The Class AbstractBaseServiceTest.
 * 
 * @author gnatas
 */
public abstract class AbstractServiceTest extends AbstractBaseTest {

    /**
     * Sets the up.
     * 
     * @throws NoSuchFieldException
     */
    @Before
    public void setUp() throws NoSuchFieldException {
        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        when(labelConfigurationService.getLabelValue(anyString(), anyString(), anyString())).thenReturn("of");
        when(resourceResolver.map("/content/carnival/hal/en")).thenReturn("/hal/en.html");
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageMang);
        
        when(page.getAbsoluteParent(1)).thenReturn(parentPage1);
        when(page.getAbsoluteParent(2)).thenReturn(parentPage2);

        when(parentPage2.getName()).thenReturn("en");
        when(parentPage1.getName()).thenReturn("platform");
    }

    /**
     * Test update sling model method.
     */
    @Test
    public abstract void testUpdateSlingModel();
}

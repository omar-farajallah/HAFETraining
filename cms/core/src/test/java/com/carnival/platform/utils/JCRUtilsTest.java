package com.carnival.platform.utils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.FilterDetailsModel;

/**
 * The Class JCRUtilsTest.
 * 
 * @author gnatas
 */
@RunWith(MockitoJUnitRunner.class)
public class JCRUtilsTest {

    /** The resolver. */
    @Mock
    Resource resource;

    /** The resource itr. */
    private Iterator<Resource> resourceItr;

    /** The value map. */
    @Mock
    private ValueMap valueMap;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {

        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource);
        resourceItr = resourceList.iterator();

        when(resource.listChildren()).thenReturn(resourceItr);
        when(resource.getValueMap()).thenReturn(valueMap);
        when(valueMap.get("filterAPIKey")).thenReturn("longest");
    }

    /**
     * Test fetch filter details.
     */
    @Test
    public void testFetchFilterDetails() {
        List<FilterDetailsModel> filterModelList = new ArrayList<>();
        filterModelList = JCRUtils.fetchFilterDetails(new String[] { "longest" }, resource);
        assertTrue(null != filterModelList);
    }
}

package com.carnival.platform.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.BaseJson;

/**
 * JSON Transformer Utility class for easy marshaling and un-marshaling.
 * 
 * @author msajja
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class JSONTransformerTest {

    @Test
    public void nullSerializer() {
    	String expectedResult = "{\"type\":\"type\",\"id\":\"id\",\"attributes\":{},\"meta\":{\"render\":\"\"}}";
    	BaseJson baseJson = new BaseJson("type", "id", null, null);
    	String jsonString = JSONTransformer.convertToJson(baseJson);
    	assertEquals(expectedResult, jsonString);
    }
    
    @Test
    public void emptyMultifield() {
    	String[] multifield = new String[]{"", ""};
    	List<Map<String, Object>> output = JSONTransformer.getJSONObject(multifield);
    	assertEquals(0, output.size());
    }

    @Test
    public void nullMultifield() {
    	List<Map<String, Object>> output = JSONTransformer.getJSONObject(null);
    	assertNull(output);
    }

    @Test
    public void validMultifield() {
    	String[] multifield = new String[]{"{\"type\":\"type\",\"id\":\"id\",\"attributes\":\"\",\"meta\":{\"render\":\"\"}}"};
    	List<Map<String, Object>> output = JSONTransformer.getJSONObject(multifield);
    	assertEquals(1, output.size());
    }
    
    @Test
    public void nullObjectToJson() {
    	Object input = null;
    	String output = JSONTransformer.convertToJson(input);
    	assertEquals(StringUtils.EMPTY, output);
    }

    @Test
    public void invalidJsonToNullObject() {
    	BaseJson output = JSONTransformer.getJSONObject("", BaseJson.class);
    	assertEquals(null, output);
    }
}

/**
 * 
 */
package com.carnival.platform.services.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.carnival.platform.models.data.TitleDataModel;

/**
 * The Class DefaultDataServiceImplTest.
 *
 * @author ssahu6
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultDataServiceImplTest {

    @InjectMocks
    private DefaultDataServiceImpl defaultDataServiceImpl;
    
    @Test
    public void noModelUpdate() {
    	TitleDataModel model = new TitleDataModel();
    	TitleDataModel expectedModel = new TitleDataModel();
    	defaultDataServiceImpl.updateModel(model);
    	assertEquals(expectedModel.getTitle(), model.getTitle());
    }
    
}

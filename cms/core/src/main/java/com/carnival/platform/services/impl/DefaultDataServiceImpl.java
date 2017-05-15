/**
 * 
 */
package com.carnival.platform.services.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.carnival.platform.models.data.AbstractDataModel;

/**
 * The Class DefaultDataServiceImpl.
 *
 * @author ssahu6
 */
@Component
@Service(DefaultDataServiceImpl.class)
public class DefaultDataServiceImpl extends AbstractDataService<AbstractDataModel> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.services.impl.AbstractDataService#updateSlingModel(
     * com.carnival.platform.models.data.AbstractDataModel)
     */
    @Override
    protected void updateSlingModel(AbstractDataModel slingModel) {
        // Default Empty Implementation

    }

}

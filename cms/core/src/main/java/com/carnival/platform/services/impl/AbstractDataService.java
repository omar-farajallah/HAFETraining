/**
 * 
 */
package com.carnival.platform.services.impl;

import com.carnival.platform.models.AbstractSlingModel;
import com.carnival.platform.models.data.AbstractDataModel;
import com.carnival.platform.services.SlingModelService;

/**
 * The Class AbstractDataService.
 *
 * @author ssahu6
 * @param <T>
 *            the generic type
 */
public abstract class AbstractDataService<T extends AbstractDataModel> implements SlingModelService {

    /**
     * Update data model.
     *
     * @param slingModel
     *            the data sling model
     */
    protected abstract void updateSlingModel(T slingModel);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.services.DataService#populateModel(com.carnival.
     * platform.models.AbstractDataModel)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void updateModel(AbstractSlingModel slingModel) {
        updateSlingModel((T) slingModel);
    }

}

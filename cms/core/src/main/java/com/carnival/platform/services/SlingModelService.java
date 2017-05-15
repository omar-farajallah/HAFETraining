/**
 * 
 */
package com.carnival.platform.services;

import com.carnival.platform.models.AbstractSlingModel;

/**
 * The Interface SlingModelService
 *
 * @author ssahu6
 */
@FunctionalInterface
public interface SlingModelService {

    /**
     * Update the sling model.
     *
     * @param slingModel
     *            the sling model to update
     */
    void updateModel(AbstractSlingModel slingModel);

}

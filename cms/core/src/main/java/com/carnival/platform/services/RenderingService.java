/**
 * 
 */
package com.carnival.platform.services;

import com.carnival.platform.models.component.AbstractComponentModel;

/**
 * The Interface RenderingService.
 *
 * @author ssahu6
 */
public interface RenderingService {

    /**
     * Check if given component is static with no specific client side behavior.
     *
     * @param componentName
     *            the component name
     * @return true if component is static with no specific client side
     *         behavior, false otherwise
     */
    boolean isComponentStatic(String componentName);

    /**
     * Transform data in model class to match JSON Data contract, generate final
     * JSON Data and invoke Server side Rendering service to get HTML DOM
     * element.
     *
     * @param slingModel
     *            the sling model
     * @return html string.
     */
    String renderComponentHTML(AbstractComponentModel slingModel);
}

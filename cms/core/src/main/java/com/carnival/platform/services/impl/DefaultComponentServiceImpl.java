/**
 * 
 */
package com.carnival.platform.services.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.AbstractSlingModel;
import com.carnival.platform.models.BaseJson;
import com.carnival.platform.models.component.AbstractComponentModel;
import com.carnival.platform.models.data.AbstractDataModel;
import com.carnival.platform.services.RenderingService;
import com.carnival.platform.services.SlingModelService;
import com.carnival.platform.utils.JSONTransformer;

/**
 * The Class DefaultComponentServiceImpl.
 *
 * @author ssahu6
 */
@Component
@Service(DefaultComponentServiceImpl.class)
public class DefaultComponentServiceImpl implements SlingModelService {

    /** The rendering service. */
    @Reference
    private RenderingService renderingService;

    /**
     * @param componentName
     *            name of the component
     * @return whether the component is static or has dynamic FE behavior
     */
    public boolean isComponentStatic(String componentName) {
        return renderingService.isComponentStatic(componentName);
    }

    /**
     * @param componentModel
     *            the component sling model
     * @return the html from server side JS compilation
     */
    public String getComponentHtml(AbstractComponentModel componentModel) {
        return renderingService.renderComponentHTML(componentModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.services.SlingModelService#updateModel(com.
     * carnival. platform.models.AbstractSlingModel)
     */
    @Override
    public void updateModel(AbstractSlingModel slingModel) {
        if (!(slingModel instanceof AbstractComponentModel)) {
            return;
        }
        AbstractComponentModel componentModel = (AbstractComponentModel) slingModel;
        AbstractDataModel[] dataModels = componentModel.getComponentData();
        AbstractDataModel parentData = dataModels[0];
        parentData.updateRequestInfo(componentModel.getRequest());
        AbstractDataModel[] childData = ArrayUtils.subarray(dataModels, 1, dataModels.length);

        // Rendering the child component json
        if (childData.length > 0) {
            BaseJson[] childModels = new BaseJson[childData.length];
            for (int i = 0; i < childData.length; i++) {
                String componentName = childData[i].getComponentName();
                childData[i].updateRequestInfo(componentModel.getRequest());
                childModels[i] = new BaseJson(componentName, String.valueOf(System.currentTimeMillis()),
                        renderingService.isComponentStatic(componentName) ? ApplicationConstants.RENDER_STATIC
                                : ApplicationConstants.RENDER_DYNAMIC,
                        childData[i]);
            }
            parentData.setChildComponents(childModels);
        }

        // Constructing base json
        BaseJson baseDataJSON = new BaseJson(componentModel.getComponentName(), componentModel.getId(),
                componentModel.isStatic() ? ApplicationConstants.RENDER_STATIC : ApplicationConstants.RENDER_DYNAMIC, parentData);

        componentModel.setJson(JSONTransformer.convertToJson(baseDataJSON));
    }
}

package com.carnival.platform.models.component;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import com.carnival.platform.models.data.AbstractDataModel;
import com.carnival.platform.models.data.HalTitleDataModel;


@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HalTitleComponentModel extends AbstractComponentModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4156776419993966064L;

    /** The title. */
    @ChildResource
    private HalTitleDataModel halTitle;

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.models.component.AbstractComponentModel#
     * getComponentData()
     */
    @Override
    public AbstractDataModel[] getComponentData() {
        return new AbstractDataModel[] { halTitle };
    }

}

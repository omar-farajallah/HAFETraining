package com.carnival.platform.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.carnival.platform.models.data.GlobalHeaderDataModel;
import com.carnival.platform.models.data.NavItemDataModel;

/**
 * The Class GlobalHeaderDataServiceImpl.
 *
 * @author vaddur
 */
@Component
@Service(value = GlobalHeaderDataServiceImpl.class)
public class GlobalHeaderDataServiceImpl extends AbstractDataService<GlobalHeaderDataModel> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.services.impl.AbstractDataService#updateSlingModel(
     * com.carnival.platform.models.data.AbstractDataModel)
     */

    @Override
    protected void updateSlingModel(GlobalHeaderDataModel globalHeaderModel) {
        List<NavItemDataModel> navigationItems = new ArrayList<>();
        globalHeaderModel.getNavigationItems().forEach(navItems -> {
            if ((navItems.getGlobalNavigationTitle()) != null
                    && (navigationItems.size() < Integer.parseInt(globalHeaderModel.getNumberOfItems()))) {
                navigationItems.add(navItems);
            }
        });

        Collections.sort(navigationItems,
                (NavItemDataModel one, NavItemDataModel other) -> one.getOrderOfItem().compareTo(other.getOrderOfItem()));

        globalHeaderModel.setNavigationItems(navigationItems);

    }

}

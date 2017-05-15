package com.carnival.platform.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.models.data.UtilityNavItemDataModel;
import com.carnival.platform.models.data.UtilityNavItemDataModel.LanguageModel;
import com.carnival.platform.utils.PageUtil;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

/**
 * The Class UtilityNavListServiceImpl.
 * 
 * @author gnatas
 */
@Component
@Service(value = UtilityNavItemDataServiceImpl.class)
public class UtilityNavItemDataServiceImpl {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilityNavItemDataServiceImpl.class);

    /**
     * Sets the language drop down items.
     *
     * @param slingModel
     *            the sling model
     * @param request
     */
    public void setLanguageDropDownItems(UtilityNavItemDataModel slingModel, SlingHttpServletRequest request) {

        LOGGER.debug("inside setLanguageDropDownItems method");
        List<UtilityNavItemDataModel.LanguageModel> langModelList;
        if (ApplicationConstants.NAVTYPE_LANGUAGE.equals(slingModel.getNavType()) && null != slingModel.getLangPaths()) {
            ResourceResolver resourceResolver = slingModel.getResourceResolver();
            if (!request.getRequestURI().contains(request.getResource().getPath())) {
                String currenPagePath = request.getRequestURI().split("\\.")[0];
                String selectors = request.getRequestPathInfo().getSelectorString();
                String queryParams = request.getQueryString();
                langModelList = new ArrayList<>();
                String[] langPaths = slingModel.getLangPaths();
                for (String path : langPaths) {
                    langModelList.add(getLangaugeModel(resourceResolver, path, currenPagePath, selectors, queryParams));
                }
                slingModel.setItems(langModelList);

            }

        }

    }

    /**
     * Gets the LangaugeModel.
     * 
     * @param resourceResolver
     *
     * @param path
     *            the path
     * @param currenPagePath
     *            the curren page path
     * @param selectors
     *            the selectors
     * @return the Language Model
     */
    public LanguageModel getLangaugeModel(ResourceResolver resourceResolver, String path, String currenPagePath, String selectors,
            String queryParams) {
        LOGGER.debug("inside getLanguageModel method");
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        String pageUrlPath = PageUtil.shortenUrl(resourceResolver, path);
        UtilityNavItemDataModel.LanguageModel langModel = new LanguageModel();

        if (pageManager != null) {
            Page currentPage = pageManager.getPage(currenPagePath);
            Page homePage = currentPage.getAbsoluteParent(ApplicationConstants.INT_2);
            String suffixPath = currentPage.getPath().replaceAll(homePage.getPath(), "");
            if (null != pageManager.getPage(path + suffixPath)) {
                pageUrlPath = pageManager.getPage(path + suffixPath).getPath();
                StringBuffer stringBuffer = new StringBuffer();
                pageUrlPath = StringUtils.isNotEmpty(selectors)
                        ? (stringBuffer.append(pageUrlPath).append(".").append(selectors).toString()) : pageUrlPath;
                pageUrlPath = PageUtil.shortenUrl(resourceResolver, pageUrlPath);
                stringBuffer = new StringBuffer();
                pageUrlPath = StringUtils.isNotEmpty(queryParams)
                        ? (stringBuffer.append(pageUrlPath).append("?").append(queryParams).toString()) : pageUrlPath;
            }

        }

        Resource pathResource = resourceResolver.getResource(path + "/" + JcrConstants.JCR_CONTENT);
        String activeStatus = currenPagePath.contains(path) ? ApplicationConstants.STATUS_ACTIVE : ApplicationConstants.STATUS_INACTIVE;
        langModel.setStatus(activeStatus);
        if (null != pathResource) {
            ValueMap valueMap = pathResource.adaptTo(ValueMap.class);
            if (valueMap != null) {
                langModel.setName((String) valueMap.get(JcrConstants.JCR_TITLE));
                langModel.setPath(pageUrlPath);
            }
        }
        return langModel;
    }

}

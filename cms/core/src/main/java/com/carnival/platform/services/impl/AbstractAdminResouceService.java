/**
 * 
 */
package com.carnival.platform.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.constants.ApplicationConstants;
import com.day.cq.commons.jcr.JcrConstants;

/**
 * The Class AbstractAdminResouceService.
 *
 * @author ssahu6
 */
public abstract class AbstractAdminResouceService {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAdminResouceService.class);

    /** The login service resolver. */
    private ResourceResolver loginServiceResolver;

    /**
     * Provides the service user's resolver object. Getting
     * getAdministrativeResourceResolver is deprecated since AEM 6.0 and hence
     * it is proposed to use the authorized service user resolver for any CRX
     * operations which requires Admin privileges.
     *
     * @param resourcePath
     *            the resource path
     * @return resource with service user's privileges
     */
    protected Resource getAdminResource(String resourcePath) {
        LOGGER.info("getAdminResource for: {}", resourcePath);
        return loginServiceResolver.getResource(resourcePath);
    }

    /**
     * Returns resource of jcr:content node if present under the given
     * resourcePath.
     *
     * @param resourcePath
     *            path under which a jcr:content node will be looked up and if
     *            present will be returned
     * @return resource of jcr:content node if present under the given
     *         resourcePath
     */
    protected Resource getAdminContentResource(String resourcePath) {
        LOGGER.info("getAdminContentResource START");
        Resource resource = getAdminResource(resourcePath);
        Resource contentResource = null;
        if (resource != null) {
            LOGGER.info("Found Resource with admin access at {}", resource.getPath());
            contentResource = resource.getChild(JcrConstants.JCR_CONTENT);
        }
        LOGGER.info("getAdminContentResource STOP");
        return contentResource;
    }

    /**
     * Initialize the Nashorn engine and install the default scripts.
     *
     * @param context
     *            the context
     */
    protected void activate(ComponentContext context) {
        LOGGER.info("Activating service - START");
        BundleContext bundleContext = context.getBundleContext();
        ServiceReference serviceReference = bundleContext.getServiceReference(ResourceResolverFactory.class.getName());
        ResourceResolverFactory resourceResolverFactory = (ResourceResolverFactory) bundleContext.getService(serviceReference);

        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, ApplicationConstants.PLATFORM_SERVICENAME);
        try {
            loginServiceResolver = resourceResolverFactory.getServiceResourceResolver(param);
            LOGGER.info("admin resource resolver is created {}", loginServiceResolver.getUserID());
        } catch (LoginException ex) {
            LOGGER.error("Exception getting login resource resolver", ex);
        }
        doActivate(context);
        LOGGER.info("Activating service - END");
    }

    /**
     * Triggered on bundle de-activate event.
     *
     * @param context
     *            the context
     */
    protected void deActivate(ComponentContext context) {
        LOGGER.info("Deactivating service - START - {}", context);
        if (loginServiceResolver != null) {
            loginServiceResolver.close();
        }
        LOGGER.info("Deactivating service - END");
    }

    /**
     * Do activate.
     *
     * @param context
     *            the context
     */
    protected abstract void doActivate(ComponentContext context);
}

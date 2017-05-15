package com.carnival.platform.services.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.constants.ApplicationConstants;
import com.carnival.platform.services.NashornEngineService;
import com.day.cq.commons.jcr.JcrConstants;

/**
 * Service Implementation responsible for evaluating the react scripts and
 * render the output HTML for AEM components.
 * 
 * @author bmuthu
 *
 */
@Component
@Service(value = NashornEngineService.class)
public class NashornEngineServiceImpl extends AbstractAdminResouceService implements NashornEngineService {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(NashornEngineServiceImpl.class);

    /** The Constant NASHORON_ENGINE_NAME. */
    private static final String NASHORON_ENGINE_NAME = "nashorn";

    /** The Constant SSR_REACT_METHOD. */
    private static final String SSR_REACT_METHOD = "renderReact";

    /** The Constant REACT_SCRIPT_NAMESPACE. */
    private static final String REACT_SCRIPT_NAMESPACE = "server";

    /** The Constant REACT_JS_FILES. */
    private static final String[] REACT_JS_FILES = { ApplicationConstants.POLYFILL_SCRIPT, ApplicationConstants.REACT_SERVER_SCRIPT };
    
    /** The nashorn. */
    private ScriptEngine nashorn;

    /**
     * Method reads the JS file provided as input argument and evaluates with
     * the Nashorn engine.
     *
     * @param jsFilePath
     *            the js file path
     */
    private void javascriptLoader(String jsFilePath) {
        LOGGER.info("Java script loader - START");
        InputStream file = null;
        Resource contentResource = getAdminContentResource(jsFilePath);
        try {
            if (contentResource != null) {
                LOGGER.info("contentResource is {}", contentResource.getPath());
                ValueMap contentMap = contentResource.getValueMap();
                file = contentMap.get(JcrConstants.JCR_DATA, InputStream.class);
                if (null != file) {
                    Reader reader = new InputStreamReader(file, StandardCharsets.UTF_8);
                    this.nashorn.eval(reader);
                }
            }
        } catch (ScriptException e) {
            LOGGER.error("Script Exception during loading js file: " + jsFilePath, e);
        }
        LOGGER.info("Java script loader - END");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.services.NashornEngineService#
     * evaluateSSRComponentHTML(java.lang.String, java.lang.String)
     */
    @Override
    public String evaluateSSRComponentHTML(String componentJSFileName, String componentJSON) {
        Object reactHTMLObject = null;

        try {
            Object server = nashorn.eval(REACT_SCRIPT_NAMESPACE);
            javascriptLoader(componentJSFileName);
            Invocable invocable = (Invocable) nashorn;
            reactHTMLObject = invocable.invokeMethod(server, SSR_REACT_METHOD, componentJSON);
        } catch (ScriptException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null == reactHTMLObject ? "" : reactHTMLObject.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.carnival.platform.services.impl.AbstractAdminResouceService#
     * doActivate(org.osgi.service.component.ComponentContext)
     */
    @Override
    protected void doActivate(ComponentContext context) {
        LOGGER.info("Activating the Nashorn Engine - START");
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager(null);
        this.nashorn = scriptEngineManager.getEngineByName(NASHORON_ENGINE_NAME);
        for (String jsFile : REACT_JS_FILES) {
            LOGGER.info("Evaluating {}", jsFile);
            javascriptLoader(jsFile);
        }
        LOGGER.info("Activating the Nashorn Engine - END");

    }

}

package com.carnival.platform.services;

/**
 * Interface provides methods for components to invoke 
 * React components
 * @author bmuthu
 *
 */
@FunctionalInterface
public interface NashornEngineService {
    
    /**
     * Method evaluates the SSR component HTML by the JSON passed as argument
     * 
     * @param componentJSFileName
     * @param componentJSON
     * @return
     */
    String evaluateSSRComponentHTML(final String componentJSFileName, final String componentJSON);
}

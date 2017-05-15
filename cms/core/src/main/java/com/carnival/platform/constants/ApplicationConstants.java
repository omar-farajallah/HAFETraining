package com.carnival.platform.constants;

/**
 * Application constants.
 *
 * @author msajja
 */
public interface ApplicationConstants {

    /* Constants for Global */

    /** The platform service name. */
    String PLATFORM_SERVICENAME = "platformService";

    /** The polyfill script. */
    String POLYFILL_SCRIPT = "/apps/carnival/platform/components/ssr/polyfill.js";

    /** The react server script. */
    String REACT_SERVER_SCRIPT = "/apps/carnival/platform/components/ssr/server.js";

    /** The base path. */
    String BASE_PATH = "/content";

    /** The dam path. */
    String DAM_PATH = "/dam/";

    /** The html extn. */
    String HTML_EXTN = ".html";

    /** The render dynamic. */
    String RENDER_DYNAMIC = "dynamic";

    /** The render static. */
    String RENDER_STATIC = "static";

    /* Image rendition Servlet constant */
    /** The int 0. */
    int INT_0 = 0;

    /** The int 1. */
    int INT_1 = 1;

    /** The int 2. */
    int INT_2 = 2;

    /** The int 3. */
    int INT_3 = 3;

    /** The int 4. */
    int INT_4 = 4;

    /** The rendition. */
    String RENDITION = "rendition";

    /** The error 404. */
    int ERROR_404 = 404;

    /** The html. */
    String HTML = "html";

    /** The query path. */
    String QUERY_PATH = "path";

    /** The query node type. */
    String QUERY_NODE_TYPE = "type";

    /** The query limit. */
    String QUERY_LIMIT = "p.limit";

    /* Constants common across components */
    /** The title. */
    String TITLE = "title";

    /** Of label */
    String OF_LABEL = "ofLabel";

    /** Search Bar Label constants */
    String CLOSE_BTN_LABEL = "closeBtnLabel";

    String SHOW_LESS_LABEL = "showLessLabel";

    String SHOW_MORE_LABEL = "showMoreLabel";

    String PAGE_TITLE = "pageTitle";

    String SEARCH_CTA_LABEL = "searchCtaLabel";

    String DURATION_LABEL = "durationLabel";

    String MONTHS_LABEL = "monthsLabel";

    String DEPARTURE_LABEL = "departureLabel";

    String DESTINATION_LABEL = "destinationLabel";

    String CONSTANT_TRUE = "true";

    /** The Model Class Property Name */
    String MODEL_CLASS_PARAM = "modelClass";

    /* Constants for Brand Story Rotator Component */
    /** The Full Width. */
    String FULL_WIDTH = "fullwidth";
    /* Constants for UtilityNav Component */
    /** The Langauge Nav Type */
    String NAVTYPE_LANGUAGE = "language";
    /** The Active Status for Language Dropdown Options */
    String STATUS_ACTIVE = "active";
    /** The InActive Status for Language Dropdown Options */
    String STATUS_INACTIVE = "inactive";

}

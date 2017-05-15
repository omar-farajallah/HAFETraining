package com.carnival.platform.models;

import java.io.Serializable;

import com.carnival.platform.models.data.AbstractDataModel;

/**
 * Base JSON for FE JSON Construction.
 *
 * @author msajja
 */
public final class BaseJson implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8722816091997220233L;

    /** The type. */
    private final String type;

    /** The id. */
    private final String id;

    /** The attributes. */
    private final AbstractDataModel attributes;

    /** The meta. */
    private final Meta meta;

    /**
     * Instantiates a new base json.
     *
     * @param type
     *            the type
     * @param id
     *            the id
     * @param render
     *            the render
     * @param attributes
     *            the attributes
     * @param childComponents
     *            the child components
     */
    public BaseJson(String type, String id, String render, AbstractDataModel attributes) {
        this.type = type;
        this.id = id;
        this.meta = new Meta(render);
        this.attributes = attributes;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the attributes.
     *
     * @return the attributes
     */
    public AbstractDataModel getAttributes() {
        return attributes;
    }

    /**
     * Gets the meta.
     *
     * @return the attributes
     */
    public Meta getMeta() {
        return meta;
    }


    /**
     * The Class Meta.
     */
    private static final class Meta implements Serializable {

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = -3775765836036951341L;

        /** The render. */
        private final String render;

        /**
         * Instantiates a new meta.
         *
         * @param render
         *            the render
         */
        public Meta(String render) {
            this.render = render;
        }

        /**
         * Gets the render.
         *
         * @return the render
         */
        @SuppressWarnings("unused")
        public String getRender() {
            return render;
        }

    }

}

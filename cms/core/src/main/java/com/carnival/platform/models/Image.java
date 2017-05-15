/**
 * 
 */
package com.carnival.platform.models;

import java.io.Serializable;

import com.carnival.platform.constants.ImageSize;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Image.
 *
 * @author ssahu6
 */
public final class Image implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4333474177461216511L;

    /** The Constant DEFAULT_EXT. */
    private static final String DEFAULT_EXT = ".jpg";

    /** The Constant HIGH_QUALITY. */
    private static final String HIGH_QUALITY = "high";

    /** The Constant MEDIUM_QUALITY. */
    private static final String MEDIUM_QUALITY = "medium";

    /** The Constant LOW_QUALITY. */
    private static final String LOW_QUALITY = "low";

    /** The Constant PERIOD. */
    private static final String PERIOD = ".";

    /** The Constant IMAGE. */
    private static final String SELECTOR = ".image.";

    /** The img alt. */
    @JsonProperty("alt")
    private final String imgAlt;

    /** The small rendition. */
    @JsonProperty("0")
    private final Rendition smallRendition;

    /** The medium rendition. */
    @JsonProperty("376")
    private final Rendition mediumRendition;

    /** The large rendition. */
    @JsonProperty("769")
    private final Rendition largeRendition;

    /**
     * Instantiates a new image.
     *
     * @param imgPath
     *            the img path
     * @param renditions
     *            the renditions
     * @param imgAlt
     *            the img alt
     */
    public Image(String imgPath, ImageSize[] renditions, String imgAlt) {
        this(new String[] { imgPath, imgPath, imgPath }, renditions, imgAlt);
    }

    /**
     * Instantiates a new image.
     *
     * @param imgPaths
     *            the img paths
     * @param renditions
     *            the renditions
     * @param imgAlt
     *            the img alt
     */
    public Image(String[] imgPaths, ImageSize[] renditions, String imgAlt) {
        this.smallRendition = new Rendition(imgPaths[0], renditions[0], LOW_QUALITY);
        this.mediumRendition = new Rendition(imgPaths[1], renditions[1], MEDIUM_QUALITY);
        this.largeRendition = new Rendition(imgPaths[2], renditions[2], HIGH_QUALITY);
        this.imgAlt = imgAlt;
    }

    /**
     * The Class Rendition.
     */
    private static final class Rendition implements Serializable {

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = 6227045228726387763L;

        /** The regular. */
        @JsonProperty("1x")
        private final String regular;

        /** The retina. */
        @JsonProperty("2x")
        private final String retina;

        /**
         * Instantiates a new rendition.
         *
         * @param imgPath
         *            the img path
         * @param size
         *            the size
         * @param quality
         *            the quality
         */
        public Rendition(String imgPath, ImageSize size, String quality) {
            this.regular = imgPath + SELECTOR + size.getNormalWidth() + PERIOD + size.getNormalHeight() + PERIOD + quality + DEFAULT_EXT;
            this.retina = imgPath + SELECTOR + size.getRetinaWidth() + PERIOD + size.getRetinaHeight() + PERIOD + quality + DEFAULT_EXT;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Rendition [regular=" + regular + ", retina=" + retina + "]";
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Image [imgAlt=" + imgAlt + ", smallRendition=" + smallRendition.toString() + ", mediumRendition="
                + mediumRendition.toString() + ", largeRendition=" + largeRendition.toString() + "]";
    }

}

/**
 * 
 */
package com.carnival.platform.constants;

/**
 * The Enum ImageSize.
 *
 * @author ssahu6
 */
public enum ImageSize {

    /** The rend 0 0. */
    REND_0_0(0, 0),

    /** The rend 750 0. */
    REND_750_0(750, 0),

    /** The rend 750 200. */
    REND_750_200(750, 200),

    /** The rend 1536 400. */
    REND_1536_400(1536, 400),

    /** The rend 1536 400. */
    REND_1536_400_766_200(1536, 400, 766, 200),

    /** The rend 2880 600. */
    REND_2880_600(2880, 600),

    /** The rend 2960 1076. */
    REND_2960_1076(2960, 1076),

    /** The rend 1536 728. */
    REND_1536_728(1536, 728),

    /** The rend 640 480. */
    REND_640_480(640, 480),

    /** The rend 648 488. */
    REND_648_488(648, 488),

    /** The rend 880 660. */
    REND_880_660(880, 660),

    /** The rend 520 696. */
    REND_520_696(520, 696),

    /** The rend 510 382. */
    REND_510_382(510, 382),

    /** The rend 1536 1150. */
    REND_1536_1150(1536, 1150),

    /** The rend 1880 800. */
    REND_1880_800(1880, 800),

    /** The rend 720 832. */
    REND_720_832(720, 832),

    /** The rend 792 694. */
    REND_792_694(792, 694),

    /** The rend 504 604. */
    REND_504_604(504, 604),

    /** The rend 880 1174. */
    REND_880_1174(880, 1174),

    /** The rend 420 560. */
    REND_420_560(420, 560),

    /** The rend 696 928. */
    REND_696_928(696, 928),

    /** The rend 336 448. */
    REND_336_448(336, 448),

    /** The rend 513 681. */
    REND_513_681(513, 681),

    /** The rend 640 640. */
    REND_640_640(640, 640),

    /** The rend 1536 1536. */
    REND_1536_1536(1536, 1536),

    /** The rend 2960 2960. */
    REND_2960_2960(2960, 2960),

    /** The rend 1536 1152. */
    REND_1536_1152(1536, 1152),

    /** The rend 2340 984. */
    REND_2340_984(2340, 984),

    /** The rend 640 1028. */
    REND_640_1028(640, 1028),

    /** The rend 1536 1872. */
    REND_1536_1872(1536, 1872),

    /** The rend 2960 1376. */
    REND_2960_1376(2960, 1376),

    /** The rend 2960 2080. */
    REND_2960_2080(2960, 2080),

    /** The rend 1480 1672. */
    REND_1480_1672(1480, 1672),

    /** The rend 640 428. */
    REND_640_428(640, 428),

    /** The rend 1336 892. */
    REND_1336_892(1336, 892),

    /** The rend 1170 780. */
    REND_1170_780(1170, 780),

    /** The rend 572 386. */
    REND_572_386(572, 386),

    /** The rend 696 466. */
    REND_696_466(696, 466),

    /** The rend 576 386. */
    REND_576_386(576, 386),

    /** The rend 934 980. */
    REND_934_980(934, 980),

    /** The rend 2340 980. */
    REND_2340_980(2340, 980),

    /** The rend 1526 980. */
    REND_1526_980(1526, 980),

    /** The rend 934 1244. */
    REND_934_1244(934, 1244),

    /** The rend 1000 1340. */
    REND_1000_1340(1000, 1340),

    /** The rend 2880 600. */
    REND_2880_1140(2880, 1140),

    /** The rend 768 490. */
    REND_768_490(768, 490),

    /** The rend 320 238. */
    REND_320_238(320, 238),

    /** The rend 1060 1060. */
    REND_1060_1060(1060, 1060),

    /** The rend 696 696. */
    REND_696_696(696, 696),

    /** The rend 502 502. */
    REND_502_502(502, 502),

    /** The rend 1192 980. */
    REND_1192_980(1192, 980),

    /** The rend 1008 754. */
    REND_1008_754(1008, 754),

    /** The rend 696 520 */
    REND_696_520(696, 520),

    /** The rend 640 490 */
    REND_640_490(640, 490),

    /** The rend 720 720 */
    REND_720_720(720, 720),

    /** The rend 932 932 */
    REND_932_932(932, 932),

    /** The rend 572 572 */
    REND_572_572(572, 572);

    /** The retina width. */
    private final int retinaWidth;

    /** The retina height. */
    private final int retinaHeight;

    /** The normal width. */
    private final int normalWidth;

    /** The normal height. */
    private final int normalHeight;

    /**
     * Instantiates a new image size.
     *
     * @param retinaWidth
     *            the retinal width
     * @param retinaHeight
     *            the retinal height
     */
    ImageSize(int retinaWidth, int retinaHeight) {
        this.retinaWidth = retinaWidth;
        this.retinaHeight = retinaHeight;
        this.normalWidth = retinaWidth / ApplicationConstants.INT_2;
        this.normalHeight = retinaHeight / ApplicationConstants.INT_2;
    }

    /**
     * Instantiates a new image size.
     *
     * @param retinaWidth
     *            the retina width
     * @param retinaHeight
     *            the retina height
     * @param normalWidth
     *            the normal width
     * @param normalHeight
     *            the normal height
     */
    ImageSize(int retinaWidth, int retinaHeight, int normalWidth, int normalHeight) {
        this.retinaWidth = retinaWidth;
        this.retinaHeight = retinaHeight;
        this.normalWidth = normalWidth;
        this.normalHeight = normalHeight;
    }

    /**
     * Gets the retina width.
     *
     * @return the retina width
     */
    public int getRetinaWidth() {
        return retinaWidth;
    }

    /**
     * Gets the retina height.
     *
     * @return the retina height
     */
    public int getRetinaHeight() {
        return retinaHeight;
    }

    /**
     * Gets the normal width.
     *
     * @return the normal width
     */
    public int getNormalWidth() {
        return normalWidth;
    }

    /**
     * Gets the normal height.
     *
     * @return the normal height
     */
    public int getNormalHeight() {
        return normalHeight;
    }

}

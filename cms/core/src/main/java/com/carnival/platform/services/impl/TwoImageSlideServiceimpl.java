package com.carnival.platform.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carnival.platform.constants.ImageSize;
import com.carnival.platform.models.Image;
import com.carnival.platform.models.data.TwoImageSlideModel;

/**
 * The Class TwoImageSlideServiceimpl.
 * 
 * @author gnatas
 */
@Component
@Service(value = TwoImageSlideServiceimpl.class)
public class TwoImageSlideServiceimpl extends AbstractDataService<TwoImageSlideModel> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TwoImageSlideServiceimpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.services.impl.AbstractDataService#updateSlingModel(
     * com.carnival.platform.models.data.AbstractDataModel)
     */
    @Override
    protected void updateSlingModel(TwoImageSlideModel slingModel) {
        LOGGER.debug("Updating image object of the sling model");

        List<Map<String,Object>> imageList = new ArrayList<>();
        // setting the image object
        Image imageObj1 = new Image(slingModel.getImage1(),
                new ImageSize[] { ImageSize.REND_513_681, ImageSize.REND_696_928, ImageSize.REND_880_1174 }, slingModel.getImage1Alt());

        Image imageObj2 = new Image(slingModel.getImage2(),
                new ImageSize[] { ImageSize.REND_513_681, ImageSize.REND_336_448, ImageSize.REND_420_560 }, slingModel.getImage2Alt());
        Map<String, Object> imageMap =  new HashMap<>();
        imageMap.put("image", imageObj1);
        imageList.add(imageMap);
        Map<String, Object> image2Map =  new HashMap<>();
        image2Map.put("image", imageObj2);
        image2Map.put("pullquote", (slingModel.getPullquote() == null)? StringUtils.EMPTY : slingModel.getPullquote());
        image2Map.put("quotedesc", (slingModel.getQuotedesc() == null)? StringUtils.EMPTY : slingModel.getQuotedesc().trim());
        imageList.add(image2Map);
        
        slingModel.setImages(imageList);
    }
}

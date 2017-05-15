package com.carnival.platform.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.carnival.platform.constants.ImageSize;
import com.carnival.platform.models.Image;
import com.carnival.platform.models.data.GalleryDetailsDataModel;
import com.carnival.platform.models.data.GalleryTabDataModel;
import com.carnival.platform.models.data.MediaGalleryDataModel;

/**
 * The Class MediaGalleryDataModelServiceImpl.
 * 
 * @author gnatas
 */
@Component
@Service(value = MediaGalleryDataModelServiceImpl.class)
public class MediaGalleryDataModelServiceImpl extends AbstractDataService<MediaGalleryDataModel> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.carnival.platform.services.impl.AbstractDataService#updateSlingModel(
     * com.carnival.platform.models.data.AbstractDataModel)
     */
    @Override
    protected void updateSlingModel(MediaGalleryDataModel slingModel) {
        List<GalleryTabDataModel> medialRowList = slingModel.getMediaRow();
        Map<Integer, GalleryTabDataModel> mediaMap = new TreeMap<>();
        // Sorting the gallery data according to order set on the tab
        medialRowList.forEach(model -> {
            if (null != model.getMedia()) {
                setImageRenditons(model);
                mediaMap.put(Integer.valueOf(model.getOrder()), model);
            }

        });
        // setting back the list
        final List<GalleryTabDataModel> galleryTabDataModelList = new ArrayList<>();
        mediaMap.forEach((order, galleryTabDataModel) -> {
            galleryTabDataModelList.add(galleryTabDataModel);
        });
        slingModel.setMediaRow(galleryTabDataModelList);
    }

    private void setImageRenditons(GalleryTabDataModel model) {
        String tileType = model.getTileType();
        List<GalleryDetailsDataModel> mediaModelList = model.getMedia();

        if ("2:1".equals(tileType)) {

            GalleryDetailsDataModel mediaModel1 = mediaModelList.get(0);
            mediaModel1.setMediaSize("8");
            mediaModel1.setImage(new Image(mediaModel1.getDesktopImage(),
                    new ImageSize[] { ImageSize.REND_1000_1340, ImageSize.REND_934_1244, ImageSize.REND_1526_980 },
                    mediaModel1.getImageAltText()));

            GalleryDetailsDataModel mediaModel2 = mediaModelList.get(1);
            mediaModel2.setMediaSize("4");
            mediaModel2.setImage(new Image(mediaModel2.getDesktopImage(),
                    new ImageSize[] { ImageSize.REND_1000_1340, ImageSize.REND_934_1244, ImageSize.REND_934_980 },
                    mediaModel2.getImageAltText()));

        } else if ("1:2".equals(tileType)) {

            GalleryDetailsDataModel mediaModel1 = mediaModelList.get(0);
            mediaModel1.setMediaSize("4");
            mediaModel1.setImage(new Image(mediaModel1.getDesktopImage(),
                    new ImageSize[] { ImageSize.REND_1000_1340, ImageSize.REND_934_1244, ImageSize.REND_934_980 },
                    mediaModel1.getImageAltText()));

            GalleryDetailsDataModel mediaModel2 = mediaModelList.get(1);
            mediaModel2.setMediaSize("8");
            mediaModel2.setImage(new Image(mediaModel2.getDesktopImage(),
                    new ImageSize[] { ImageSize.REND_1000_1340, ImageSize.REND_934_1244, ImageSize.REND_1526_980 },
                    mediaModel2.getImageAltText()));

        } else if ("full".equals(tileType)) {
            GalleryDetailsDataModel mediaModel = mediaModelList.get(0);
            mediaModel.setMediaSize("12");
            mediaModel.setImage(new Image(mediaModel.getDesktopImage(),
                    new ImageSize[] { ImageSize.REND_1000_1340, ImageSize.REND_934_1244, ImageSize.REND_2340_980 },
                    mediaModel.getImageAltText()));

        }
    }

}

package com.assignment1.imagesservice.util;

import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import com.assignment1.imagesservice.dataLayer.Image;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

import java.io.IOException;

public class MapperUtil {

    public static ImageResponseModel entityToResponseModel(Image image){
        ImageResponseModel imageResponseModel = new ImageResponseModel();
        imageResponseModel.setImageId(image.getImageId());
        imageResponseModel.setBytes(image.getImage().getData());
        return imageResponseModel;
    }

    public static Image requestModelToEntity(ImageRequestModel imageRequestModel){
        Image image = new Image();
        image.setImage(imageRequestModel.getFile());
        image.setImageId(ShortIdGen.getShortId());
        return image;
    }

    public static Integer getImageId(ImageResponseModel imageResponseModel){
        return imageResponseModel.getImageId();
    }
}

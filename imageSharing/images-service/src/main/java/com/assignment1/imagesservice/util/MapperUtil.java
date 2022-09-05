package com.assignment1.imagesservice.util;

import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import com.assignment1.imagesservice.datalayer.Image;
import com.assignment1.imagesservice.datalayer.ImageRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;

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
        try {
            image.setImage(new Binary(BsonBinarySubType.BINARY, imageRequestModel.getFile().getBytes()));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        image.setImageId(ShortIdGen.getShortId());
        return image;
    }
}

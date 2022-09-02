package com.assignment1.imagesservice.businessLayer;

import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import com.assignment1.imagesservice.datalayer.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageResponseModel addPhoto(ImageRequestModel imageRequestModel) throws IOException;
    ImageResponseModel getImage(Integer id);
}

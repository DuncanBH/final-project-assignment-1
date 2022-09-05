package com.assignment1.imagesservice.businessLayer;

import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import com.assignment1.imagesservice.datalayer.Image;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface ImageService {
    Mono<ImageResponseModel> addPhoto(Mono<ImageRequestModel> imageRequestModel) throws IOException;
    Mono<ImageResponseModel> getImage(Integer id);
}

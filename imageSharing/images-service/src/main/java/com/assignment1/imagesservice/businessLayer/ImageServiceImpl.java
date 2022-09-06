package com.assignment1.imagesservice.businessLayer;

import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import com.assignment1.imagesservice.dataLayer.ImageRepository;
import com.assignment1.imagesservice.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Mono<ImageResponseModel> addPhoto(Mono<ImageRequestModel> imageRequestModel) {
            return  imageRequestModel
                .map(MapperUtil::requestModelToEntity)
                .flatMap(imageRepository::insert)
                .map(MapperUtil::entityToResponseModel);
    }

    @Override
    public Mono<ImageResponseModel> getImage(Integer imageId) {
        return imageRepository.findImageByImageId(imageId)
                .map(MapperUtil::entityToResponseModel);
    }
}

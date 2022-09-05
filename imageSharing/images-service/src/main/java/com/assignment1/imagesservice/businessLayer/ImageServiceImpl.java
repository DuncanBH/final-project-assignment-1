package com.assignment1.imagesservice.businessLayer;

import com.assignment1.imagesservice.dataMappingLayer.ImageRequestMapper;
import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseMapper;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import com.assignment1.imagesservice.datalayer.Image;
import com.assignment1.imagesservice.datalayer.ImageRepository;
import com.assignment1.imagesservice.util.MapperUtil;
import com.assignment1.imagesservice.util.ShortIdGen;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

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

package com.assignment1.imagesservice.businessLayer;

import com.assignment1.imagesservice.dataMappingLayer.ImageRequestMapper;
import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseMapper;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import com.assignment1.imagesservice.datalayer.Image;
import com.assignment1.imagesservice.datalayer.ImageRepository;
import com.assignment1.imagesservice.util.ShortIdGen;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageResponseMapper imageResponseMapper;
    @Autowired
    private ImageRequestMapper imageRequestMapper;

    @Override
    public ImageResponseModel addPhoto(ImageRequestModel imageRequestModel) throws IOException {
        Image image = new Image();
        image.setImage(new Binary(BsonBinarySubType.BINARY, imageRequestModel.getFile().getBytes()));

        Integer shortID;

        do{
            shortID = ShortIdGen.getShortId();
        } while (imageRepository.existsImageByImageId(shortID));

        image.setImageId(shortID);

        image = imageRepository.insert(image);
        return imageResponseMapper.entityToResponseModel(image);
    }

    @Override
    public ImageResponseModel getImage(Integer imageId) {
        Image image = imageRepository.findImageByImageId(imageId);

        ImageResponseModel imageResponseModel = imageResponseMapper.entityToResponseModel(image);

        String binString = image.getImage().toString();

        int binInt = Integer.parseInt(binString, 2);
        ByteBuffer bytes = ByteBuffer.allocate(2).putInt(binInt);

        byte[] bytesArr = bytes.array();

        imageResponseModel.setBytes(bytesArr);

        return imageResponseMapper.entityToResponseModel(imageRepository.findImageByImageId(imageId));
    }
}

package com.assignment1.imagesservice.businessLayer;

import com.assignment1.imagesservice.datalayer.Image;
import com.assignment1.imagesservice.datalayer.ImageRepository;
import com.assignment1.imagesservice.util.ShortIdGen;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Integer addPhoto(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

        Integer shortID;

        do{
            shortID = ShortIdGen.getShortId();
        } while (imageRepository.existsImageByImageId(shortID));

        image.setImageId(shortID);

        image = imageRepository.insert(image);
        return image.getImageId();
    }

    @Override
    public Image getImage(Integer imageId) {
        return imageRepository.findImageByImageId(imageId);
    }
}

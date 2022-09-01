package com.assignment1.imagesservice.businessLayer;

import com.assignment1.imagesservice.datalayer.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Integer addPhoto(MultipartFile file) throws IOException;
    Image getImage(Integer id);
}

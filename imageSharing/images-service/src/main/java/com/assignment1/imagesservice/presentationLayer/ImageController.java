package com.assignment1.imagesservice.presentationLayer;

import com.assignment1.imagesservice.businessLayer.ImageService;
import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import com.assignment1.imagesservice.datalayer.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageResponseModel> getImage(@PathVariable Integer imageId){
        return ResponseEntity.status(HttpStatus.OK).body(imageService.getImage(imageId));
    }

    @PostMapping()
    public ResponseEntity<ImageResponseModel> addPhoto(@RequestBody ImageRequestModel file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(imageService.addPhoto(file));
    }
}

package com.assignment1.imagesservice.presentationLayer;

import com.assignment1.imagesservice.businessLayer.ImageService;
import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/{imageId}")
    public Mono<ResponseEntity<ImageResponseModel>> getImage(@PathVariable Integer imageId){
        //return ResponseEntity.status(HttpStatus.OK).body(imageService.getImage(imageId));
        return imageService.getImage(imageId).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<ResponseEntity<Integer>> addPhoto(@RequestBody byte[] file) throws IOException {
        //return ResponseEntity.status(HttpStatus.OK).body(imageService.addPhoto(file));
        return imageService.addPhoto(Mono.just(new ImageRequestModel(file))).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

package com.assignment1.imagesservice.presentationLayer;

import com.assignment1.imagesservice.businessLayer.ImageService;
import com.assignment1.imagesservice.dataMappingLayer.ImageRequestModel;
import com.assignment1.imagesservice.dataMappingLayer.ImageResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public Mono<ResponseEntity<ImageResponseModel>> addPhoto(@RequestBody Mono<ImageRequestModel> file) throws IOException {
        //return ResponseEntity.status(HttpStatus.OK).body(imageService.addPhoto(file));
        return imageService.addPhoto(file).map(ResponseEntity::ok);
    }
}

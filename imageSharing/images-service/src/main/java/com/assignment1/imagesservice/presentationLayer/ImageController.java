package com.assignment1.imagesservice.presentationLayer;

import com.assignment1.imagesservice.businessLayer.ImageService;
import com.assignment1.imagesservice.datalayer.Image;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getImage(@PathVariable Integer imageId){
        Image image = imageService.getImage(imageId);

        return Base64.getEncoder().encodeToString(image.getImage().getData());
    }

    @PostMapping()
    public Integer addPhoto(@RequestBody MultipartFile file) throws IOException {
        Integer imageId = imageService.addPhoto(file);
        return imageId;
    }
}

package com.assignment1.imagesservice.dataMappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class ImageResponseModel extends RepresentationModel<ImageResponseModel> {
    private Integer imageId;
    private byte[] bytes;
}

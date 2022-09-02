package com.assignment1.imagesservice.dataMappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class ImageRequestModel {
    private MultipartFile file;
}

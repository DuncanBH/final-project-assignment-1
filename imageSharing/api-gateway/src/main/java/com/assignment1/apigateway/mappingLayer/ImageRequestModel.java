package com.assignment1.apigateway.mappingLayer;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class ImageRequestModel {
    private MultipartFile file;
}
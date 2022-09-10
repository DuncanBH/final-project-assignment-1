package com.assignment1.apigateway.mappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class PostAggregateRequestModel {
    private String caption;
    private Integer channel;
    private Integer imageId;
}

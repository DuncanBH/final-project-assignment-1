package com.assignment1.apigateway.mappingLayer;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostAggregateRequestModel {
    private String caption;
    private Integer channel;
    private Integer imageId;
}

package com.assignment1.apigateway.mappingLayer;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class ImageResponseModel {
    private Integer imageId;
    private byte[] bytes;
}
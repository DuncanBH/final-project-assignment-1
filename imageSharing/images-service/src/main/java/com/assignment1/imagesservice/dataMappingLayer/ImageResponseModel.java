package com.assignment1.imagesservice.dataMappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ImageResponseModel {
    private Integer imageId;
    private byte[] bytes;
}

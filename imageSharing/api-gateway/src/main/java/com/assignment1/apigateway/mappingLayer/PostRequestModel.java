package com.assignment1.apigateway.mappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostRequestModel {
    private Integer imageId;
    private String caption;
    private Integer channel;
}


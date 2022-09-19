package com.assignment1.apigateway.mappingLayer;

import lombok.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Setter
@Getter
public class PostRequestModel {
    private Integer imageId;
    private String caption;
    private Integer channel;
}


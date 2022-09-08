package com.assignment1.apigateway.mappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@Setter
@Getter
public class PostResponseModel {
    private Integer postId;
    private Integer imageId;
    private String caption;
}


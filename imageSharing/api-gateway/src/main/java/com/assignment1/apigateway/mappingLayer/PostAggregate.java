package com.assignment1.apigateway.mappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostAggregate {
    private Integer postId;
    private String caption;
    private byte[] bytes;
}

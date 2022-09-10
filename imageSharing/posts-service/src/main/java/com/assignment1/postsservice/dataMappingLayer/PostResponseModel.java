package com.assignment1.postsservice.dataMappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponseModel {
    private Integer postId;
    private Integer imageId;
    private String caption;
}

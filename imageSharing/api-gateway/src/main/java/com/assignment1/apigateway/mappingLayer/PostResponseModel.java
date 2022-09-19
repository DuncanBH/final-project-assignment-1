package com.assignment1.apigateway.mappingLayer;

import lombok.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Setter
@Getter
public class PostResponseModel {
    private Integer postId;
    private Integer imageId;
    private String caption;

    public PostResponseModel(Integer imageId){
        this.imageId = imageId;
    }

    public PostResponseModel setPostId(Integer postId){
        this.postId = postId;
        return this;
    }

    public PostResponseModel setCaption(String caption){
        this.caption = caption;
        return this;
    }
}


package com.assignment1.apigateway.mappingLayer;

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


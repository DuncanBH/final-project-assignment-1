package com.assignment1.postsservice.util;

import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import com.assignment1.postsservice.dataLayer.Post;
import org.springframework.beans.BeanUtils;

public class MapperUtil {
    public static PostResponseModel entityToResponseModel(Post post){
        PostResponseModel responseModel = new PostResponseModel();
        BeanUtils.copyProperties(post, responseModel);
        return responseModel;
    }

    public static Post requestModelToEntity(PostRequestModel postRequestModel){
        Post post = new Post();
        BeanUtils.copyProperties(postRequestModel, post);
        return post;
    }
}

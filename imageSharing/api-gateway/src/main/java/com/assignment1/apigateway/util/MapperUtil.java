package com.assignment1.apigateway.util;

import com.assignment1.apigateway.mappingLayer.ImageRequestModel;
import com.assignment1.apigateway.mappingLayer.PostAggregateRequestModel;
import com.assignment1.apigateway.mappingLayer.PostResponseModel;
import com.assignment1.apigateway.mappingLayer.PostRequestModel;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

public class MapperUtil {
    public static PostRequestModel getPostRequestModel(PostAggregateRequestModel postAggregateRequestModel){
        PostRequestModel postRequestModel = new PostRequestModel();
        BeanUtils.copyProperties(postAggregateRequestModel, postRequestModel);
        return postRequestModel;
    }

    public static ImageRequestModel getImageRequestModel(MultipartFile file){
        return new ImageRequestModel(file);
    }

    public static PostResponseModel createPostResponseModel(Integer imageId){
        return new PostResponseModel(imageId);
    }

}

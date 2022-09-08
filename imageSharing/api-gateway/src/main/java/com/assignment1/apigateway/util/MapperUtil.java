package com.assignment1.apigateway.util;

import com.assignment1.apigateway.mappingLayer.PostAggregate;
import com.assignment1.apigateway.mappingLayer.PostResponseModel;
import org.springframework.beans.BeanUtils;

public class MapperUtil {
    public static PostAggregate responseModelToPostAggregate(PostResponseModel responseModel){
        PostAggregate postAggregate = new PostAggregate();
        BeanUtils.copyProperties(responseModel, postAggregate);
        return postAggregate;
    }
}

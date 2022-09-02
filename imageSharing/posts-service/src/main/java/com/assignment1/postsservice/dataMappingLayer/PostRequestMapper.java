package com.assignment1.postsservice.dataMappingLayer;

import com.assignment1.postsservice.datalayer.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "postId", ignore = true)
    })
    Post requestModelToEntity(PostRequestModel requestModel);
}

package com.assignment1.postsservice.dataMappingLayer;


import com.assignment1.postsservice.datalayer.Post;
import com.assignment1.postsservice.presentationLayer.PostController;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostResponseMapper {
    PostResponseModel entityToResponseModel(Post post);
    List<PostResponseModel> entityListToResponseModelList(List<Post> posts);

    @AfterMapping
    default void addLinks(@MappingTarget PostResponseModel model, Post entity){
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostController.class).getPost(entity.getPostId())).withSelfRel();
        model.add(selfLink);
    }
}

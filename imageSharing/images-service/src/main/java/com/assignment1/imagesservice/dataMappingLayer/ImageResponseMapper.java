package com.assignment1.imagesservice.dataMappingLayer;

import com.assignment1.imagesservice.datalayer.Image;
import com.assignment1.imagesservice.presentationLayer.ImageController;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Mapper(componentModel = "spring")
public interface ImageResponseMapper {
    @Mapping(target = "file", ignore = true)
    ImageResponseModel entityToResponseModel(Image image);

    @AfterMapping
    default void addLinks(@MappingTarget ImageResponseModel model, Image entity){
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ImageController.class).getImage(entity.getImageId())).withSelfRel();
        model.add(selfLink);
    }
}

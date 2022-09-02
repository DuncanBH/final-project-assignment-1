package com.assignment1.imagesservice.dataMappingLayer;

import com.assignment1.imagesservice.datalayer.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ImageRequestMapper {
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "imageId", ignore = true)
    })
    Image requestModelToEntity(ImageRequestModel imageRequestModel);
}

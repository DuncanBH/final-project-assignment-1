package com.assignment1.imagesservice.datalayer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
    boolean existsImageByImageId(Integer imageId);
    Image findImageByImageId(Integer imageId);
}

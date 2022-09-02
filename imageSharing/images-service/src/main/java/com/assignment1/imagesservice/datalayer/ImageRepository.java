package com.assignment1.imagesservice.datalayer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    boolean existsImageByImageId(Integer imageId);
    Image findImageByImageId(Integer imageId);
}

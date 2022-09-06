package com.assignment1.imagesservice.datalayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ImageRepository extends ReactiveMongoRepository<Image, String> {
    Mono<Image> findImageByImageId(Integer imageId);
}

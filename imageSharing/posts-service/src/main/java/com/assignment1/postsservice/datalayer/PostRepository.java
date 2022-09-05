package com.assignment1.postsservice.datalayer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String> {
    Mono<Post> findPostByPostId(Integer postId);
    Mono<Void> deletePostByPostId(Integer postId);
}

package com.assignment1.postsservice.dataLayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String> {
    Mono<Post> findPostByPostId(Integer postId);
    Mono<Void> deletePostByPostId(Integer postId);

    Flux<Post> findPostsByChannel(Integer channel);
}

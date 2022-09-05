package com.assignment1.postsservice.businessLayer;

import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostsService {
    Flux<PostResponseModel> findAllPosts();
    Mono<PostResponseModel> findPostByPostId(Integer postId);
    Mono<PostResponseModel> createPost(Mono<PostRequestModel> post);
    Mono<PostResponseModel> updatePost(Mono<PostRequestModel> post, Integer postId);
    Mono<Void> deletePost(Integer postId);
}

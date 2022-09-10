package com.assignment1.apigateway.businessLayer;

import com.assignment1.apigateway.mappingLayer.ImageResponseModel;
import com.assignment1.apigateway.mappingLayer.PostAggregateRequestModel;
import com.assignment1.apigateway.mappingLayer.PostResponseModel;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostAggregateService {
    Flux<PostResponseModel> getAllPosts();

    Mono<PostResponseModel> getPostByPostId(Integer postId);

    Mono<PostResponseModel> createPost(Mono<PostAggregateRequestModel> postAggregateRequestModelMono);

    Mono<PostResponseModel> updatePost(Mono<PostAggregateRequestModel> postAggregateRequestModelMono, Integer postId);

    Mono<Void> deletePost(Integer postId);

    Flux<PostResponseModel> getPostByChannel(Integer channel);

    Mono<ImageResponseModel> getImage(Integer imageId);

    Mono<Integer> addImage(MultipartFile multipartFile);
}

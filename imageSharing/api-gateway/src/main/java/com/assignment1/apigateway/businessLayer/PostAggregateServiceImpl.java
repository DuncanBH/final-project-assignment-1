package com.assignment1.apigateway.businessLayer;

import com.assignment1.apigateway.domainClientLayer.ImageServiceClient;
import com.assignment1.apigateway.domainClientLayer.PostServiceClient;
import com.assignment1.apigateway.mappingLayer.*;
import com.assignment1.apigateway.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class PostAggregateServiceImpl implements PostAggregateService{

    @Autowired
    private PostServiceClient postServiceClient;

    @Autowired
    private ImageServiceClient imageServiceClient;

    @Override
    public Flux<PostResponseModel> getAllPosts() {
        return postServiceClient
                .getAll();

    }

    @Override
    public Mono<PostResponseModel> getPostByPostId(Integer postId) {
        return postServiceClient
                .getPostByPostId(postId);
    }

    @Override
    public Mono<PostResponseModel> createPost(Mono<PostAggregateRequestModel> postAggregateRequestModelMono) {
        return postAggregateRequestModelMono
                .map(MapperUtil::getPostRequestModel).flatMap(postServiceClient::createPost);
    }

    //UNCERTAIN
    @Override
    public Mono<PostResponseModel> updatePost(Mono<PostAggregateRequestModel> postAggregateRequestModelMono, Integer postId) {
        return postAggregateRequestModelMono
                .map(MapperUtil::getPostRequestModel).flatMap(p -> postServiceClient.updatePost(postId, p));
    }

    @Override
    public Mono<Void> deletePost(Integer postId) {
        return postServiceClient.deletePost(postId);
    }

    @Override
    public Flux<PostResponseModel> getPostByChannel(Integer channel) {
        return postServiceClient.getPostsByChannel(channel);
    }

    public Mono<ImageResponseModel> getImage(Integer imageId){
        return imageServiceClient.getImage(imageId);
    }

    public Mono<Integer> addImage(MultipartFile multipartFile){
        try {
            return imageServiceClient.addImage(multipartFile.getBytes());
        } catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

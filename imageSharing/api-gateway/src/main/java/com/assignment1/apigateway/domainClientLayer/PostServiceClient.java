package com.assignment1.apigateway.domainClientLayer;

import com.assignment1.apigateway.exceptions.HttpErrorInfo;
import com.assignment1.apigateway.exceptions.InvalidInputException;
import com.assignment1.apigateway.mappingLayer.PostRequestModel;
import com.assignment1.apigateway.mappingLayer.PostResponseModel;
import com.assignment1.apigateway.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class PostServiceClient {
    private String url;
    private final ObjectMapper objectMapper;
    private final WebClient.Builder webClient;

    public PostServiceClient(@Value("${app.posts.host}") String postServiceClientHost, @Value("${app.posts.port}") String postServiceClientPort, ObjectMapper objectMapper, WebClient.Builder webClient){
        this.objectMapper = objectMapper;
        this.webClient = webClient;
        this.url = "http://" + postServiceClientHost + ":" + postServiceClientPort + "/api";
    }

    public Flux<PostResponseModel> getAll(){
        try{
            return webClient
                    .build()
                    .get()
                    .uri(url + "/posts")
                    .retrieve()
                    .bodyToFlux(PostResponseModel.class);
        } catch (HttpClientErrorException e){
            throw  handleHttpClientException(e);
        }
    }

    public Mono<PostResponseModel> getPostByPostId(Integer postId){
        try{
            return webClient
                    .build()
                    .get()
                    .uri(url + "/posts/" + postId)
                    .retrieve()
                    .bodyToMono(PostResponseModel.class);
        } catch (HttpClientErrorException e){
            throw  handleHttpClientException(e);
        }
    }

    public Mono<PostResponseModel> createPost(PostRequestModel postRequestModel){
        try{
            return webClient
                    .build()
                    .post()
                    .uri(url + "/posts")
                    .body(Mono.just(postRequestModel), PostRequestModel.class)
                    .retrieve()
                    .bodyToMono(PostResponseModel.class);
        } catch (HttpClientErrorException e){
            throw  handleHttpClientException(e);
        }
    }

    public Mono<PostResponseModel> updatePost(Integer postId, PostRequestModel postRequestModel){
        try{
            return webClient
                    .build()
                    .put()
                    .uri(url + "/posts/" + postId)
                    .body(Mono.just(postRequestModel), PostRequestModel.class)
                    .retrieve()
                    .bodyToMono(PostResponseModel.class);
        } catch (HttpClientErrorException e){
            throw  handleHttpClientException(e);
        }
    }

    public Mono<Void> deletePost(Integer postId){
        try{
            return webClient
                    .build()
                    .delete()
                    .uri(url + "/posts/" + postId)
                    .retrieve()
                    .bodyToMono(Void.class);
        } catch (HttpClientErrorException e){
            throw  handleHttpClientException(e);
        }
    }

    public Flux<PostResponseModel> getPostsByChannel(Integer channelId){
        try{
            return webClient
                    .build()
                    .get()
                    .uri(url + "/channels/" + channelId + "/posts")
                    .retrieve()
                    .bodyToFlux(PostResponseModel.class);
        } catch (HttpClientErrorException e){
            throw  handleHttpClientException(e);
        }
    }
    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        switch (ex.getStatusCode()) {
            case NOT_FOUND:
                return new NotFoundException(getErrorMessage(ex));
            case UNPROCESSABLE_ENTITY :
                return new InvalidInputException(getErrorMessage(ex));
            default:
                log.warn("Got an unexpected HTTP error: {}, will rethrow it",
                        ex.getStatusCode());
                log.warn("Error body: {}", ex.getResponseBodyAsString());
                return ex;
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return objectMapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}

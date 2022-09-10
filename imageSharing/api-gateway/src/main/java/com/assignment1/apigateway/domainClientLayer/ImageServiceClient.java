package com.assignment1.apigateway.domainClientLayer;

import com.assignment1.apigateway.exceptions.HttpErrorInfo;
import com.assignment1.apigateway.exceptions.InvalidInputException;
import com.assignment1.apigateway.exceptions.NotFoundException;
import com.assignment1.apigateway.mappingLayer.ImageRequestModel;
import com.assignment1.apigateway.mappingLayer.ImageResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceClient {
    private String url;

    private final ObjectMapper objectMapper;

    private final WebClient.Builder webClient;

    public ImageServiceClient(@Value("${app.images.host}") String imageServiceClientHost, @Value("${app.images.port}") String imageServiceClientPort, ObjectMapper objectMapper, WebClient.Builder webClient){
        this.objectMapper = objectMapper;
        this.webClient = webClient;
        this.url = "http://" + imageServiceClientHost + ":" + imageServiceClientPort + "/api/images";
    }

    public Mono<ImageResponseModel> getImage(Integer imageId){
        try{
            return webClient
                    .build()
                    .get()
                    .uri(url + "/" + imageId)
                    .retrieve()
                    .bodyToMono(ImageResponseModel.class);
        }   catch (HttpClientErrorException e){
            System.out.println("Error: Object is null");
            throw  handleHttpClientException(e);
        }
    }

    public Mono<Integer> addImage(byte[] bytes){
        System.out.println("ImageServiceClient");
        try{
            return webClient
                    .build()
                    .post()
                    .uri(url)
                    .bodyValue(bytes)
                    .retrieve()
                    .bodyToMono(Integer.class);
        }   catch (HttpClientErrorException e){
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

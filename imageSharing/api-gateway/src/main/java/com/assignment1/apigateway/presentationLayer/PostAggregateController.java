package com.assignment1.apigateway.presentationLayer;

import com.assignment1.apigateway.businessLayer.PostAggregateService;
import com.assignment1.apigateway.mappingLayer.ImageResponseModel;
import com.assignment1.apigateway.mappingLayer.PostAggregateRequestModel;
import com.assignment1.apigateway.mappingLayer.PostResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PostAggregateController {
    @Autowired
    private PostAggregateService postAggregateService;

    @GetMapping("/posts")
    public Flux<PostResponseModel> getAllPosts(){
        return postAggregateService.getAllPosts();
    }

    @GetMapping("/posts/{postId}")
    public Mono<ResponseEntity<PostResponseModel>> getPostByPostId(@PathVariable Integer postId){
        return postAggregateService.getPostByPostId(postId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping( "/posts")
    public Mono<ResponseEntity<PostResponseModel>> createPost(@RequestBody PostAggregateRequestModel postAggregateRequestModel){
        System.out.println("PostAggregateController");
        return postAggregateService.createPost(Mono.just(postAggregateRequestModel))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/posts/{postId}")
    public Mono<ResponseEntity<PostResponseModel>> updatePost(@RequestBody PostAggregateRequestModel postAggregateRequestModel, @PathVariable Integer postId){
        return postAggregateService.updatePost(Mono.just(postAggregateRequestModel), postId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/posts/{postId}")
    public Mono<Void> deletePost(@PathVariable Integer postId){
        return postAggregateService.deletePost(postId);
    }

    @GetMapping("/channel/{channel}/posts")
    public Flux<PostResponseModel> getPostsByChannel(@PathVariable Integer channel){
        return postAggregateService.getPostByChannel(channel);
    }

    @GetMapping("/images/{imageId}")
    public Mono<ResponseEntity<ImageResponseModel>> getImage(@PathVariable Integer imageId){
        return postAggregateService.getImage(imageId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Integer>> createImage(@RequestParam("file") MultipartFile file){
        return postAggregateService.addImage(file)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

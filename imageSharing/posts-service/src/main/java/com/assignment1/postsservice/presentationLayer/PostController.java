package com.assignment1.postsservice.presentationLayer;

import com.assignment1.postsservice.businessLayer.PostsService;
import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import com.assignment1.postsservice.datalayer.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostsService postsService;

    @GetMapping()
    public Flux<PostResponseModel> getAll(){
        return postsService.findAllPosts();
    }

    @GetMapping("/{postId}")
    public Mono<ResponseEntity<PostResponseModel>> getPost(@PathVariable Integer postId){
        return postsService.findPostByPostId(postId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public  Mono<ResponseEntity<PostResponseModel>> createPost(@RequestBody Mono<PostRequestModel> post){
        return postsService.createPost(post)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{postId}")
    public  Mono<ResponseEntity<PostResponseModel>> updatePost(@PathVariable Integer postId, @RequestBody Mono<PostRequestModel> post){
        return postsService.updatePost(post, postId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{postId}")
    public Mono<Void> deletePost(@PathVariable Integer postId){
        return postsService.deletePost(postId);
    }
}

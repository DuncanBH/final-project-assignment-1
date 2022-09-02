package com.assignment1.postsservice.presentationLayer;

import com.assignment1.postsservice.businessLayer.PostsService;
import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import com.assignment1.postsservice.datalayer.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostsService postsService;

    @GetMapping()
    public ResponseEntity<List<PostResponseModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(postsService.findAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseModel> getPost(@PathVariable Integer postId){
        return ResponseEntity.status(HttpStatus.OK).body(postsService.findPostByPostId(postId));
    }

    @PostMapping()
    public ResponseEntity<PostResponseModel> createPost(@RequestBody PostRequestModel post){
        return ResponseEntity.status(HttpStatus.CREATED).body(postsService.createPost(post));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseModel> updatePost(@PathVariable Integer postId, @RequestBody PostRequestModel post){
        return ResponseEntity.status(HttpStatus.OK).body(postsService.updatePost(post, postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId){
        postsService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

package com.assignment1.postsservice.presentationLayer;

import com.assignment1.postsservice.businessLayer.PostsService;
import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import com.assignment1.postsservice.datalayer.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostsService postsService;

    @GetMapping()
    public List<PostResponseModel> getAll(){
        return postsService.findAllPosts();
    }

    @GetMapping("/{postId}")
    public PostResponseModel getPost(@PathVariable Integer postId){
        return postsService.findPostByPostId(postId);
    }

    @PostMapping()
    public PostResponseModel createPost(@RequestBody PostRequestModel post){
        return postsService.createPost(post);
    }

    @PutMapping("/{postId}")
    public PostResponseModel updatePost(@PathVariable Integer postId, @RequestBody PostRequestModel post){
        return postsService.updatePost(post, postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Integer postId){
        postsService.deletePost(postId);
    }
}

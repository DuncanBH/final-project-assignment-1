package com.assignment1.postsservice.presentationLayer;

import com.assignment1.postsservice.businessLayer.PostsService;
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
    public List<Post> getAll(){
        return postsService.findAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Integer postId){
        return postsService.findPostByPostId(postId);
    }

    @PostMapping()
    public Post createPost(@RequestBody Post post){
        return postsService.createPost(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Integer postId, @RequestBody Post post){
        return postsService.updatePost(post, postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Integer postId){
        postsService.deletePost(postId);
    }
}

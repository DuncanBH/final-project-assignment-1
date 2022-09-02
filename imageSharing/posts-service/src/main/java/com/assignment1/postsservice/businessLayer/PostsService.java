package com.assignment1.postsservice.businessLayer;

import com.assignment1.postsservice.datalayer.Post;

import java.util.List;

public interface PostsService {
    List<Post> findAllPosts();
    Post findPostByPostId(Integer postId);
    Post createPost(Post post);
    Post updatePost(Post post, Integer postId);
    void deletePost(Integer postId);
}

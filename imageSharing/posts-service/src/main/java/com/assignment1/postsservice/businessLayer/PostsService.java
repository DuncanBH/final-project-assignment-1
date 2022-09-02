package com.assignment1.postsservice.businessLayer;

import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import com.assignment1.postsservice.datalayer.Post;

import java.util.List;

public interface PostsService {
    List<PostResponseModel> findAllPosts();
    PostResponseModel findPostByPostId(Integer postId);
    PostResponseModel createPost(PostRequestModel post);
    PostResponseModel updatePost(PostRequestModel post, Integer postId);
    void deletePost(Integer postId);
}

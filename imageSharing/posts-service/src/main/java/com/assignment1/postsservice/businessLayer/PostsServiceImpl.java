package com.assignment1.postsservice.businessLayer;

import com.assignment1.postsservice.dataMappingLayer.PostRequestMapper;
import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import com.assignment1.postsservice.dataMappingLayer.PostResponseMapper;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import com.assignment1.postsservice.datalayer.Post;
import com.assignment1.postsservice.datalayer.PostRepository;
import com.assignment1.postsservice.util.ShortIdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostResponseMapper postResponseMapper;
    @Autowired
    private PostRequestMapper postRequestMapper;

    @Override
    public List<PostResponseModel> findAllPosts() {
        return postResponseMapper.entityListToResponseModelList(postRepository.findAll());
    }

    @Override
    public PostResponseModel findPostByPostId(Integer postId) {
        return postResponseMapper.entityToResponseModel(postRepository.findPostByPostId(postId));
    }

    @Override
    public PostResponseModel createPost(PostRequestModel postRequestModel) {
        Post post = new Post();

        Integer postId;

        do {
            postId = ShortIdGen.getShortId();
        } while (postRepository.existsPostByPostId(postId));

        post.setPostId(postId);
        post.setImageId(postRequestModel.getImageId());
        post.setCaption(postRequestModel.getCaption());

        return postResponseMapper.entityToResponseModel(postRepository.save(post));
    }

    @Override
    public PostResponseModel updatePost(PostRequestModel post, Integer postId) {
        Post inDb = postRepository.findPostByPostId(postId);

        inDb.setCaption(post.getCaption());
        inDb.setImageId(post.getImageId());

        inDb = postRepository.save(inDb);

        return postResponseMapper.entityToResponseModel(inDb);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deletePostByPostId(postId);
    }
}

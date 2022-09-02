package com.assignment1.postsservice.businessLayer;

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

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post findPostByPostId(Integer postId) {
        return postRepository.findPostByPostId(postId);
    }

    @Override
    public Post createPost(Post post) {
        Integer postId;

        do {
            postId = ShortIdGen.getShortId();
        } while (postRepository.existsPostByPostId(postId));

        post.setPostId(postId);

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post, Integer postId) {
        Post inDb = postRepository.findPostByPostId(postId);

        inDb.setCaption(post.getCaption());
        inDb.setImageId(post.getImageId());

        return postRepository.save(inDb);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deletePostByPostId(postId);
    }
}

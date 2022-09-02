package com.assignment1.postsservice.datalayer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    boolean existsPostByPostId(Integer postId);
    Post findPostByPostId(Integer postId);
    void deletePostByPostId(Integer postId);
}

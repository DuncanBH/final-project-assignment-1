package com.assignment1.postsservice.businessLayer;

import com.assignment1.postsservice.dataLayer.Post;
import com.assignment1.postsservice.dataLayer.PostRepository;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PostsServiceImplTest {

    @MockBean
    PostRepository postRepository;

    @Autowired
    PostsService postsService;

    private final int VALID_POST_ID = 1;
    private final int VALID_CHANNEL = 1;
    private final int VALID_IMAGE_ID = 1;
    private final String VALID_CAPTION = "";

    @BeforeEach
    void setup(){
        Post setupPost = new Post();

        setupPost.setPostId(VALID_POST_ID);
        setupPost.setCaption(VALID_CAPTION);
        setupPost.setChannel(VALID_CHANNEL);
        setupPost.setImageId(VALID_IMAGE_ID);

        when(postRepository.findPostByPostId(VALID_POST_ID)).thenReturn(Mono.just(setupPost));
        when(postRepository.findAll()).thenReturn(Flux.just(setupPost));
        when(postRepository.findPostsByChannel(VALID_CHANNEL)).thenReturn(Flux.just(setupPost));
        when(postRepository.deletePostByPostId(VALID_POST_ID)).thenReturn(Mono.empty());
    }

    private PostResponseModel CheckPostResponseModel(PostResponseModel postResponseModel) {
        assertEquals(postResponseModel.getPostId(), VALID_POST_ID);
        assertEquals(postResponseModel.getCaption(), VALID_CAPTION);
        assertEquals(postResponseModel.getImageId(), VALID_IMAGE_ID);
        return postResponseModel;
    }

    @Test
    void findAllPosts() {
        postsService.findAllPosts()
                .map(this::CheckPostResponseModel);
    }

    @Test
    void findPostByPostId() {
        postsService.findPostByPostId(VALID_POST_ID)
                .map(this::CheckPostResponseModel);
    }

    @Test
    void deletePost() {
        postsService.deletePost(VALID_POST_ID);
        verify(postRepository, times(1)).deletePostByPostId(VALID_POST_ID);
    }

    @Test
    void getPostsByChannel() {
        postsService.getPostsByChannel(VALID_CHANNEL)
                .map(this::CheckPostResponseModel);
    }
}
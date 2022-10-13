package com.assignment1.postsservice.presentationLayer;

import com.assignment1.postsservice.dataLayer.Post;
import com.assignment1.postsservice.dataLayer.PostRepository;
import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class PostControllerTest {

    private static final Integer VALID_POST_ID = 1;
    private static final Integer VALID_CHANNEL = 1;
    private static final Integer VALID_IMAGE_ID = 1;
    private static final Integer SECOND_VALID_IMAGE_ID = 2;
    private static final String VALID_CAPTION = "";

    private static final PostRequestModel VALID_REQUEST_MODEL = getPostRequestModel();
    private static final PostRequestModel SECOND_VALID_REQUEST_MODEL = getSecondPostRequestModel();

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    PostRepository postRepository;

    private final String BASE_URI = "/api";


    @Test
    void getAll() {
        Post setupPost = getSetupPost();

        Publisher<Post> setup = postRepository.deleteAll().thenMany(postRepository.save(setupPost));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        webTestClient.get()
                .uri(BASE_URI + "/posts")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].postId").isEqualTo(VALID_POST_ID)
                .jsonPath("$[0].imageId").isEqualTo(VALID_IMAGE_ID)
                .jsonPath("$[0].caption").isEqualTo(VALID_CAPTION);
    }

    @Test
    void getPost() {
        Post setupPost = getSetupPost();

        Publisher<Post> setup = postRepository.deleteAll().thenMany(postRepository.save(setupPost));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        webTestClient.get()
                .uri(BASE_URI + "/posts/" + VALID_POST_ID)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.postId").isEqualTo(VALID_POST_ID)
                .jsonPath("$.imageId").isEqualTo(VALID_IMAGE_ID)
                .jsonPath("$.caption").isEqualTo(VALID_CAPTION);
    }

    @Test
    void createPost() {
        Publisher<Void> setup = postRepository.deleteAll();

        StepVerifier
                .create(setup)
                .expectNextCount(0)
                .verifyComplete();

        webTestClient.post()
                .uri(BASE_URI + "/posts")
                .body(Mono.just(VALID_REQUEST_MODEL), PostRequestModel.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.postId").isNotEmpty()
                .jsonPath("$.imageId").isEqualTo(VALID_IMAGE_ID)
                .jsonPath("$.caption").isEqualTo(VALID_CAPTION);
    }

    @Test
    void updatePost() {
        Post setupPost = getSetupPost();

        Publisher<Post> setup = postRepository.deleteAll().thenMany(postRepository.save(setupPost));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        webTestClient.put()
                .uri(BASE_URI + "/posts/" + VALID_POST_ID)
                .body(Mono.just(SECOND_VALID_REQUEST_MODEL), PostRequestModel.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.postId").isEqualTo(VALID_POST_ID)
                .jsonPath("$.imageId").isEqualTo(SECOND_VALID_IMAGE_ID)
                .jsonPath("$.caption").isEqualTo(VALID_CAPTION);
    }

    @Test
    void deletePost() {
        Post setupPost = getSetupPost();

        Publisher<Post> setup = postRepository.deleteAll().thenMany(postRepository.save(setupPost));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        webTestClient.delete()
                .uri(BASE_URI + "/posts/" + VALID_POST_ID)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void getPostsByChannel() {
        Post setupPost = getSetupPost();

        Publisher<Post> setup = postRepository.deleteAll().thenMany(postRepository.save(setupPost));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();

        webTestClient.get()
                .uri(BASE_URI + "/channels/" + VALID_CHANNEL + "/posts")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].postId").isEqualTo(VALID_POST_ID)
                .jsonPath("$[0].imageId").isEqualTo(VALID_IMAGE_ID)
                .jsonPath("$[0].caption").isEqualTo(VALID_CAPTION);
    }

    @Test
    void getAllEmpty(){
        Publisher<Void> setup = postRepository.deleteAll();

        StepVerifier
                .create(setup)
                .expectNextCount(0)
                .verifyComplete();

        webTestClient.get()
                .uri(BASE_URI + "/posts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0]").doesNotExist();
    }

    @Test
    void getChannelEmpty(){
        Publisher<Void> setup = postRepository.deleteAll();

        StepVerifier
                .create(setup)
                .expectNextCount(0)
                .verifyComplete();

        webTestClient.get()
                .uri(BASE_URI + "/channels/" + VALID_CHANNEL + "/posts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0]").doesNotExist();
    }

    private Post getSetupPost(){
        Post post = new Post();
        post.setPostId(VALID_POST_ID);
        post.setCaption(VALID_CAPTION);
        post.setImageId(VALID_IMAGE_ID);
        post.setChannel(VALID_CHANNEL);
        return post;
    }

    private static PostRequestModel getPostRequestModel(){
        PostRequestModel postRequestModel = new PostRequestModel();
        postRequestModel.setCaption(VALID_CAPTION);
        postRequestModel.setImageId(VALID_IMAGE_ID);
        postRequestModel.setChannel(VALID_CHANNEL);
        return postRequestModel;
    }

    private static PostRequestModel getSecondPostRequestModel(){
        PostRequestModel postRequestModel = new PostRequestModel();
        postRequestModel.setCaption(VALID_CAPTION);
        postRequestModel.setImageId(SECOND_VALID_IMAGE_ID);
        postRequestModel.setChannel(VALID_CHANNEL);
        return postRequestModel;
    }
}
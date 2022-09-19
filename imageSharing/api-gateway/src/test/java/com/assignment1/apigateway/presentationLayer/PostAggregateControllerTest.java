package com.assignment1.apigateway.presentationLayer;

import com.assignment1.apigateway.domainClientLayer.ImageServiceClient;
import com.assignment1.apigateway.domainClientLayer.PostServiceClient;
import com.assignment1.apigateway.mappingLayer.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
class PostAggregateControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    PostServiceClient postServiceClient;

    @MockBean
    ImageServiceClient imageServiceClient;

    final String BASE_URI = "/api";
    final String CAPTION = "ckcdbshj";
    final Integer POST_ID = 15487;
    final Integer IMAGE_ID = 172737;
    final byte[] BYTES = "random".getBytes();
    final Integer CHANNEL = 2345;

    final PostResponseModel POST_RESPONSE_MODEL = buildPostResponseModel();
    final PostAggregateRequestModel POST_AGGREGATE_REQUEST_MODEL = buildPostAggregateRequestModel();
    final ImageResponseModel IMAGE_RESPONSE_MODEL = buildImageResponseModel();
    final ImageRequestModel IMAGE_REQUEST_MODEL = buildImageRequestModel();

    @Test
    void getAllPosts() {

    }

    @Test
    void getPostByPostId() {
        when(postServiceClient.getPostByPostId(any(Integer.class))).thenReturn(Mono.just(POST_RESPONSE_MODEL));

        webTestClient.get()
                .uri(BASE_URI + "/posts/" + POST_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.postId").isEqualTo(POST_ID);
    }

    @Test
    void createPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void getPostsByChannel() {
    }

    @Test
    void getImage() {
    }

    @Test
    void createImage() {
    }

    private PostResponseModel buildPostResponseModel() {
        return PostResponseModel.builder()
                .caption(CAPTION)
                .postId(POST_ID)
                .imageId(IMAGE_ID)
                .build();
    }

    private PostAggregateRequestModel buildPostAggregateRequestModel(){
        return PostAggregateRequestModel.builder()
                .caption(CAPTION)
                .imageId(IMAGE_ID)
                .channel(CHANNEL)
                .build();
    }

    private ImageResponseModel buildImageResponseModel(){
        return ImageResponseModel.builder()
                .imageId(IMAGE_ID)
                .bytes(BYTES)
                .build();
    }

    private ImageRequestModel buildImageRequestModel(){
        return ImageRequestModel.builder()
                .file(null)
                .build();
    }
}
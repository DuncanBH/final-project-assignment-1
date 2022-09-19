package com.assignment1.apigateway.presentationLayer;

import com.assignment1.apigateway.domainClientLayer.ImageServiceClient;
import com.assignment1.apigateway.domainClientLayer.PostServiceClient;
import com.assignment1.apigateway.mappingLayer.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
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
        when(postServiceClient.updatePost(any(Integer.class), any(PostRequestModel.class))).thenReturn(Mono.just(POST_RESPONSE_MODEL));

        webTestClient.put()
                .uri(BASE_URI + "/posts/" + POST_ID)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(POST_RESPONSE_MODEL), PostResponseModel.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.postId").isEqualTo(POST_ID);
    }

    @Test
    void deletePost() {
        when(postServiceClient.deletePost(any(Integer.class))).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri(BASE_URI + "/posts/" + POST_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    void getPostsByChannel() {
    }

    @Test
    void getImage() {
        when(imageServiceClient.getImage(any(Integer.class))).thenReturn(Mono.just(IMAGE_RESPONSE_MODEL));

        webTestClient.get()
                .uri(BASE_URI + "/images/" + IMAGE_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.imageId").isEqualTo(IMAGE_ID);
    }

    @Test
    void createImage() {
        when(imageServiceClient.addImage(any(byte[].class))).thenReturn(Mono.just(IMAGE_ID));

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        String header = String.format("form-data; name=%s; filename=%s", "file", "test.txt");
        builder.part("file", new ByteArrayResource("ttt".getBytes())).header("Content-Disposition", header);

        webTestClient.post()
                .uri(BASE_URI + "/images")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody();
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
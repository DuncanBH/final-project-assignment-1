package com.assignment1.postsservice.dataLayer;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class PostRepositoryTest {
    @Autowired
    public PostRepository postRepository;


    @Test
    void findPostByPostId() {
        Post post = new Post();
        post.setPostId(1);
        post.setCaption("Amaziiin Time");
        post.setChannel(10);
        post.setImageId(100);

        Publisher<Post> setup =
                postRepository.deleteAll().thenMany(postRepository.save(post));

        Publisher<Post> find = postRepository.findPostByPostId(1);

        Publisher<Post> composite = Mono.from(setup).thenMany(find);

        StepVerifier
                .create(composite)
                .consumeNextWith(foundPost ->{
                    assertEquals(post.getPostId(),foundPost.getPostId());
                    assertEquals(post.getImageId(),foundPost.getImageId());
                    assertEquals(post.getCaption(),foundPost.getCaption());
                    assertEquals(post.getChannel(),foundPost.getChannel());
                }).verifyComplete();
    }

    @Test
    void deletePostByPostId() {
        Post post = new Post();
        post.setPostId(1);
        post.setCaption("Amaziiin Time");
        post.setChannel(10);
        post.setImageId(100);

        Publisher<Post> setup =
                postRepository.deleteAll().thenMany(postRepository.save(post));

        Publisher<Void> delete = postRepository.deletePostByPostId(1);

        Publisher<Void> composite = Mono.from(setup).thenMany(delete);

        StepVerifier
                .create(composite)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void findPostsByChannel() {
    }
}
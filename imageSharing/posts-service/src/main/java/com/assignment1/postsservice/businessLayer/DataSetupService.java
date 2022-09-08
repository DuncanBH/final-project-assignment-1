package com.assignment1.postsservice.businessLayer;

import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private PostsService postsService;

    public void run(String... args) throws Exception {

        PostRequestModel p1 = new PostRequestModel();
        p1.setCaption("Caption 1");
        p1.setChannel(1);
        p1.setImageId(1);
        PostRequestModel p2 =  new PostRequestModel();
        p2.setCaption("Caption 2");
        p2.setChannel(1);
        p2.setImageId(1);
        PostRequestModel p3 =  new PostRequestModel();
        p3.setCaption("Caption 3");
        p3.setChannel(1);
        p3.setImageId(1);
        PostRequestModel p4 =  new PostRequestModel();
        p4.setCaption("Caption 4");
        p4.setChannel(1);
        p4.setImageId(1);
        PostRequestModel p5 =  new PostRequestModel();
        p5.setCaption("Caption 5");
        p5.setChannel(1);
        p5.setImageId(1);

        Flux.just(p1,p2,p3,p4,p5)
                .flatMap(p -> postsService.createPost(Mono.just(p))
                        .log(p.toString()))
                .subscribe();
    }
}

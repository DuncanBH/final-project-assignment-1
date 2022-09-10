package com.assignment1.postsservice.businessLayer;

import com.assignment1.postsservice.dataMappingLayer.PostRequestModel;
import com.assignment1.postsservice.dataMappingLayer.PostResponseModel;
import com.assignment1.postsservice.dataLayer.PostRepository;
import com.assignment1.postsservice.util.MapperUtil;
import com.assignment1.postsservice.util.ShortIdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Flux<PostResponseModel> findAllPosts() {
        //return postResponseMapper.entityListToResponseModelList(postRepository.findAll());
        return postRepository
                .findAll()
                .map(MapperUtil::entityToResponseModel);
    }

    @Override
    public Mono<PostResponseModel> findPostByPostId(Integer postId) {
        //return postResponseMapper.entityToResponseModel(postRepository.findPostByPostId(postId));
        return postRepository
                .findPostByPostId(postId)
                .map(MapperUtil::entityToResponseModel);
    }

    @Override
    public Mono<PostResponseModel> createPost(Mono<PostRequestModel> postRequestModel) {
        /*Post post = new Post();

        Integer postId;

        do {
            postId = ShortIdGen.getShortId();
        } while (postRepository.existsPostByPostId(postId));

        post.setPostId(postId);
        post.setImageId(postRequestModel.getImageId());
        post.setCaption(postRequestModel.getCaption());

        return postResponseMapper.entityToResponseModel(postRepository.save(post));*/
        return postRequestModel
                .map(MapperUtil::requestModelToEntity)
                .doOnNext(e -> e.setPostId(ShortIdGen.getShortId()))
                .flatMap(postRepository::insert)
                .map(MapperUtil::entityToResponseModel);
    }

    @Override
    public Mono<PostResponseModel> updatePost(Mono<PostRequestModel> post, Integer postId) {
        /*Post inDb = postRepository.findPostByPostId(postId);

        inDb.setCaption(post.getCaption());
        inDb.setImageId(post.getImageId());

        inDb = postRepository.save(inDb);

        return postResponseMapper.entityToResponseModel(inDb);*/
        return postRepository.findPostByPostId(postId)
                .flatMap(p -> post
                        .map(MapperUtil::requestModelToEntity)
                        .doOnNext(e -> e.setId(p.getId()))
                        .doOnNext(e -> e.setPostId(p.getPostId())))
                .flatMap(postRepository::save)
                .map(MapperUtil::entityToResponseModel);
    }

    @Override
    @Transactional
    public Mono<Void> deletePost(Integer postId) {
        //postRepository.deletePostByPostId(postId);
        return postRepository.deletePostByPostId(postId);
    }

    @Override
    public Flux<PostResponseModel> getPostsByChannel(Integer channel) {
        return postRepository
                .findPostsByChannel(channel)
                .map(MapperUtil::entityToResponseModel);
    }
}

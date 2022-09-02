package com.assignment1.postsservice.dataMappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@Setter
@Getter
public class PostResponseModel extends RepresentationModel<PostResponseModel> {
    private Integer postId;
    private Integer imageId;
    private String caption;
}

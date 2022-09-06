package com.assignment1.postsservice.dataLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Posts")
@NoArgsConstructor
@Setter
@Getter
public class Post {
    @Id
    private String id;

    @Indexed(unique = true)
    private Integer postId;

    private Integer imageId;
    private String caption;
    private Integer channel;
}

package com.assignment1.imagesservice.dataLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
@NoArgsConstructor
@Setter
@Getter
public class Image {
    @Id
    private String id;

    @Indexed(unique = true)
    private Integer imageId;

    private Binary image;
}

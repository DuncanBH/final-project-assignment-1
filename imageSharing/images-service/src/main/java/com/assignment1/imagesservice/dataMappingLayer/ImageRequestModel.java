package com.assignment1.imagesservice.dataMappingLayer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class ImageRequestModel {
    private Binary file;

    public ImageRequestModel(byte[] file){
        this.file = new Binary(BsonBinarySubType.BINARY, file);
    }
}

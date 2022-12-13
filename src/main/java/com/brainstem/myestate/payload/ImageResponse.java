package com.brainstem.myestate.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private String fileName;
    private String downloadURL;
    private String  fileType;
    private Long fileSize;
}

package com.brainstem.myestate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private String fileName;
    private String downloadURL;
    private String  fileType;
    private Long fileSize;
}

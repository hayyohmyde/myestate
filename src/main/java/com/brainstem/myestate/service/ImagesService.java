package com.brainstem.myestate.service;

import com.brainstem.myestate.model.Image;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagesService {
    Image saveImage(@RequestParam("file") MultipartFile file) throws Exception;
    List<Image> saveImages(@RequestParam("files") MultipartFile[] files) throws Exception;
    Image getImage(String fileId) throws Exception;
}

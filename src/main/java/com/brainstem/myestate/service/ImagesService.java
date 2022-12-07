package com.brainstem.myestate.service;

import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.payload.ApartmentDto;
import com.brainstem.myestate.payload.UserDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ImagesService {
    public Image saveImage(@RequestParam("file") MultipartFile file) throws Exception;
    public ArrayList<Image> saveImages(@RequestParam("files") MultipartFile[] files);

    Image getImage(String field) throws Exception;
}

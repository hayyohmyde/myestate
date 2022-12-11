package com.brainstem.myestate.controllers;

import com.brainstem.myestate.payload.ResponseData;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.service.ImagesService;
import com.brainstem.myestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*", origins = "**")
public class UserController {

    @Autowired
    private final UserService userService;
    private final ImagesService imagesService;

    public UserController(UserService userService, ImagesService imagesService) {
        this.userService = userService;
        this.imagesService = imagesService;
    }

    @PostMapping("/v1/user/uploadProfileImage")
   public ResponseData uploadProfileImage(
           @RequestParam("file") MultipartFile file) throws Exception {

        Image image = imagesService.saveImage(file);
        String downloadURL = "";
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/user/download/")
                .path(image.getId())
                .toUriString();

        //return image url in response
        ResponseData response = new ResponseData(
                image.getFileName(),
                downloadURL,
                file.getContentType(),
                file.getSize());
        return response;
   }

    @RequestMapping("/v1/user/download/{field}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String field) throws Exception {
        Image image = null;
        image = imagesService.getImage(field);
        //add header info so browser knows what to do with this data
        return ResponseEntity.ok()
                .contentType(MediaType
                .parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "image; filename=\"" + image.getFileName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

}

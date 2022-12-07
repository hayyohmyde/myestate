package com.brainstem.myestate.controllers;

import com.brainstem.myestate.ResponseData;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.payload.LoginDto;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.service.ImagesService;
import com.brainstem.myestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(allowedHeaders = "*", origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    private ImagesService imagesService;

    public UserController(UserService userService, ImagesService imagesService) {
        this.userService = userService;
        this.imagesService = imagesService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        System.out.println("Form Data => " + userDto);
        if(userDto.getPassword().length() < 6 && userDto.getEmail().isEmpty()){
            throw new RuntimeException("Invalid Input");
        }
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }


    @PostMapping("/uploadProfileImage")
   public ResponseData uploadProfileImage(
           @RequestParam("file") MultipartFile file) throws Exception {

        Image image = null;
        image = imagesService.saveImage(file);
        String downloadURL = "";
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
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



    @RequestMapping("/download/{field}")
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



    @PostMapping("/login")
   public  UserDto login(@RequestBody LoginDto loginDto){

        UserDto loginResponse = userService.login(loginDto);
        if(loginResponse == null){
            throw new RuntimeException("Invalid User details");
        }
        return loginResponse;
    }

}

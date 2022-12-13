package com.brainstem.myestate.controllers;

import com.brainstem.myestate.payload.ImageResponse;
import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.service.ImagesService;
import com.brainstem.myestate.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api("User Controller exposes ")
@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*", origins = "**")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ImagesService imagesService;

    public UserController(UserService userService, ImagesService imagesService) {
        this.userService = userService;
        this.imagesService = imagesService;
    }

    @PostMapping("/v1/user/uploadProfileImage")
   public ResponseEntity<?> uploadProfileImage(
           @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok().body(imagesService.saveImage(file));
   }


    @GetMapping("/v1/user/download/{fileId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileId) throws Exception {
        Image image = imagesService.getImage(fileId);
        //add header info so browser knows what to do with this data
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "image; filename=\"" + image.getFileName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

    public ResponseEntity<?> updateUser(Long userId, @RequestBody UserDto userDto){
        return  ResponseEntity.ok().body(userService.updateProfile(userId, userDto));
    }

}

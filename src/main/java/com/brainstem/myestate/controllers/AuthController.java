package com.brainstem.myestate.controllers;

import com.brainstem.myestate.ResponseObject;
import com.brainstem.myestate.payload.LoginDto;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.security.JwtAuthResponse;
import com.brainstem.myestate.security.JwtTokenProvider;
import com.brainstem.myestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/auth/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(new JwtAuthResponse(userService.login(loginDto)), HttpStatus.OK);
    }

    @PostMapping("/v1/auth/signUp")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.SignUp(userDto), HttpStatus.CREATED);
    }
}

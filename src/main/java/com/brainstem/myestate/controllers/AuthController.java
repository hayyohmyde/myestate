package com.brainstem.myestate.controllers;

import com.brainstem.myestate.payload.LoginDto;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.security.JwtAuthResponse;
import com.brainstem.myestate.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Auth Controlller exposes Sign in and Sign up REST API")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "This is Sign in / log in REST API and " +
            "requires email or username and a password. " +
            "Successful response return a JWT token needed " +
            "to query the rest PUT, PATCH, DELETE and POST")
    @PostMapping("/v1/auth/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(new JwtAuthResponse(userService.login(loginDto)), HttpStatus.OK);
    }

    @ApiOperation(value = "This Sign Up REST API " +
            "requires firstname, lastname, email, username and password for first," +
            " for signing up. The rest of the details will be later updated in UserController")
    @PostMapping("/v1/auth/signUp")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.SignUp(userDto), HttpStatus.CREATED);
    }
}

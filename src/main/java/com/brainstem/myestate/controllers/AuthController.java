package com.brainstem.myestate.controllers;

import com.brainstem.myestate.dto.request.LoginDto;
import com.brainstem.myestate.dto.request.UserDto;
import com.brainstem.myestate.security.JwtAuthResponse;
import com.brainstem.myestate.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Api(value = "Auth Controlller exposes Sign in and Sign up REST API")
@RestController
@RequestMapping("/api")
public class AuthController {

    public final Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "This api is where user can sign-up or sign-in")
    @PostMapping("/v1/auth/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

//    @ApiParam(hidden = true)
    @ApiOperation(value = "Firstname, lastname, email, phone number, username, password, date of birth are required")
    @PostMapping(value = "/v1/auth/signUp",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> signup(@Valid UserDto userDto) throws Exception {
        log.info("USERDTO => " + userDto.toString());
        return new ResponseEntity<>(userService.signUp(userDto), HttpStatus.CREATED);
    }
}

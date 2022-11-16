package com.brainstem.myestate.controllers;

import com.brainstem.myestate.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserController userController;

    public UserController(UserController userController) {
        this.userController = userController;
    }

}

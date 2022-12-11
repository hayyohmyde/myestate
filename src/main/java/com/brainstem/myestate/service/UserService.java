package com.brainstem.myestate.service;

import com.brainstem.myestate.ResponseObject;
import com.brainstem.myestate.model.User;
import com.brainstem.myestate.payload.LoginDto;
import com.brainstem.myestate.payload.UserDto;


public interface UserService {
    String login(LoginDto loginDto);
    ResponseObject updateProfile(long UserId, UserDto userDto);

    ResponseObject SignUp(UserDto userDto);


}

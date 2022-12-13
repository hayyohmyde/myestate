package com.brainstem.myestate.service;

import com.brainstem.myestate.payload.ResponseObject;
import com.brainstem.myestate.payload.LoginDto;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.security.JwtAuthResponse;


public interface UserService {
    JwtAuthResponse login(LoginDto loginDto);
    ResponseObject updateProfile(long UserId, UserDto userDto);
    ResponseObject SignUp(UserDto userDto);


}

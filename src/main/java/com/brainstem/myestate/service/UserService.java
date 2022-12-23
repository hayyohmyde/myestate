package com.brainstem.myestate.service;

import com.brainstem.myestate.dto.ResponseObject;
import com.brainstem.myestate.dto.request.LoginDto;
import com.brainstem.myestate.dto.request.UpateUserDto;
import com.brainstem.myestate.dto.request.UserDto;
import com.brainstem.myestate.security.JwtAuthResponse;


public interface UserService {
    JwtAuthResponse login(LoginDto loginDto);
    ResponseObject updateProfile(long UserId, UpateUserDto userDto) throws Exception;
    ResponseObject signUp(UserDto userDto) throws Exception;


}

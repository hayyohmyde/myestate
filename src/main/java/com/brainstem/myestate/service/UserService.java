package com.brainstem.myestate.service;

import com.brainstem.myestate.payload.LoginDto;
import com.brainstem.myestate.payload.UserDto;


public interface UserService {
    UserDto login(LoginDto loginDto);
    UserDto updateProfile(long UserId, UserDto userDto);

    UserDto createUser(UserDto userDto);


}

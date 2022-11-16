package com.brainstem.myestate.service;

import com.brainstem.myestate.payload.BuildingDto;
import com.brainstem.myestate.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto signUp(UserDto userDto);
    UserDto login(String email, String password);
    UserDto updateProfile(long UserId, UserDto userDto);
}

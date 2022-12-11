package com.brainstem.myestate.utils;

import com.brainstem.myestate.model.User;
import com.brainstem.myestate.payload.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Serialize {

    @Autowired
    PasswordEncoder passwordEncoder;

    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
//                .age(user.getAge())
                .address(user.getAddress())
                .email(user.getEmail())
//                .dob(user.getDob())
                .otherName(user.getOtherName())
                .username(user.getUsername())
                .wallet(user.getWallet())
                .gender(user.gender())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
//                .profileImage(user.getProfileImage().getId())
                .build();
    }

    public User mapToEntity(UserDto userDto) {
        return User.builder()
//                .age(userDto.getAge())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
//                .dob(userDto.getDob())
                .otherName(userDto.getOtherName())
                .username(userDto.getUsername())
                .wallet(userDto.getWallet())
                .gender(userDto.getGender())
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
//                .profileImage(userDto.setProfileImage())
                .build();
    }
}

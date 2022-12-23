package com.brainstem.myestate.security;

import com.brainstem.myestate.dto.request.UserDto;
import com.brainstem.myestate.dto.response.UserDtoResponse;

public class JwtAuthResponse {
    private  String accessToken;
    private final String tokenType = "Bearer";
    private UserDtoResponse userDto;

    public JwtAuthResponse(String accessToken, UserDtoResponse userDto) {
        this.accessToken = accessToken;
        this.userDto = userDto;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserDtoResponse getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDtoResponse userDto) {
        this.userDto = userDto;
    }
}

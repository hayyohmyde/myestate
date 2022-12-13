package com.brainstem.myestate.security;

import com.brainstem.myestate.payload.UserDto;

public class JwtAuthResponse {
    private  String accessToken;
    private final String tokenType = "Bearer";
    private UserDto userDto;

    public JwtAuthResponse(String accessToken, UserDto userDto) {
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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}

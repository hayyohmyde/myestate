package com.brainstem.myestate.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private String usernameOrEmail;
    private String password;

}

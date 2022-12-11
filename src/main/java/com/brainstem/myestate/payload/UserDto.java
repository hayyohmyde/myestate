package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.*;
import com.brainstem.myestate.utils.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto  {
    private Long id;
    @NotNull
    @Size(min = 2, message = "firstName should have atleast 3 characters")
    private String firstName;
    @NotNull
    @Size(min = 2, message = "firstName should have atleast 3 characters")
    private String lastName;
    private String otherName;
//    private LocalDate dob;
    private Gender gender;
    @Email(message = "Email should be a valid email")
    private String email;
    @NotNull(message = "username should not be empty")
    private String username;
    @NotNull@Size(min=8, message = "Password should be series of character, numbers, special and cased character")
    private String password;
    private String phoneNumber;
//    private int age;
    private LocalDateTime registeredDate;
    private Address address;
    private Wallet wallet;
    private String profileImage;

}

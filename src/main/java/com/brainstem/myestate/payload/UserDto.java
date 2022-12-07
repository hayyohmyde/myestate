package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.*;
import com.brainstem.myestate.utils.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto  {
//    private Long id;
    private String firstName;
    private String lastName;
    private String otherName;
//    private LocalDate dob;
    private Gender gender;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
//    private int age;
    private LocalDateTime registeredDate;
    private Address address;
    private Wallet wallet;
    private String profileImage;

}

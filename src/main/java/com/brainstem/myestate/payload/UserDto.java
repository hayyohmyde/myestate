package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.*;
import com.brainstem.myestate.utils.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private LocalDate dob;
    private Gender gender;
    private String email;
    private String username;
    private String password;
    private int age;
    private LocalDateTime registeredDate;
    private Address address;
    private Wallet wallet;

}

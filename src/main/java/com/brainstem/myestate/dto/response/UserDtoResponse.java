package com.brainstem.myestate.dto.response;

import com.brainstem.myestate.model.*;
import com.brainstem.myestate.utils.Gender;
import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoResponse{
    private Long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private String dob;
    private Gender gender;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private int age;
    private boolean isActive;
    private LocalDateTime registeredDate;
    private LocalDateTime updatedDate;
    private Address address;
    private Wallet wallet;
//    private Set<Apartment> apartments;
//    private Set<Building> buildings;
//    private Set<Land> lands;
    private ImageResponse profileImage;
}


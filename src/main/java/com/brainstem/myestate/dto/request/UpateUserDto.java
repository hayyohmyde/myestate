package com.brainstem.myestate.dto.request;

import com.brainstem.myestate.model.*;
import com.brainstem.myestate.utils.Gender;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UpateUserDto{
    private String password;
    private String otherName;
    private Gender gender;
    private String phoneNumber;
    private Address address;
    private MultipartFile file;
}


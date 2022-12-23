package com.brainstem.myestate.dto.request;

import com.brainstem.myestate.dto.response.ImageResponse;
import com.brainstem.myestate.model.*;
import com.brainstem.myestate.utils.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto{

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 2, message = "firstName should have atleast 3 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 3, message = "first Name should have atleast 3 characters")
    private String lastName;

    @NotNull(message = "Date of birth cannot be empty")
    private String dob;

    private Gender gender;

    @ApiModelProperty(required = true)
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "username should not be empty")
    @Size(min = 3, message = "Username must be more than 3 characters")
    private String username;

    @NotNull
    @Size(min=8, message = "Password should be series of character, numbers, special and cased character")
    private String password;

    @NumberFormat(pattern = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]?\\d{4}$")
    @NotNull(message = "Phone number cannot be null")
    @Size(min=9, message = "Phone Number should be numbers of atleast 9 length")
    @Column(name = "mobile_number")
    private String phoneNumber;

    private MultipartFile file;
}

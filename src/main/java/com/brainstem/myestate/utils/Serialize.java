//package com.brainstem.myestate.utils;
//
//import com.brainstem.myestate.dto.response.ImageResponse;
//import com.brainstem.myestate.model.Image;
//import com.brainstem.myestate.model.User;
//import com.brainstem.myestate.dto.request.UserDto;
//import com.brainstem.myestate.service.ImagesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.logging.Logger;
//
// public class Serialize {
//    private static ImagesService imagesService;
//
//    public static Logger log = Logger.getLogger(Serialize.class.getCanonicalName());
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    public static UserDto mapToDto(User user) {
//        Image image = user.getProfileImage();
//        ImageResponse profileImage = new ImageResponse(
//                image.getFileName(), image.getUrl(), image.getFileType(), image.getSize()
//        );
//        return UserDto.builder()
//                .id(user.getId())
//                .age(user.getAge())
//                .address(user.getAddress())
//                .email(user.getEmail())
//                .dob(user.getDob().toString())
//                .otherName(user.getOtherName())
//                .username(user.getUsername())
//                .wallet(user.getWallet())
//                .gender(user.getGender())
//                .lastName(user.getLastName())
//                .firstName(user.getFirstName())
//                .isActive(user.isActive())
//                .email(user.getEmail())
//                .profileImage(profileImage)
//                .build();
//    }
//
//    public static User mapToEntity(UserDto userDto) throws Exception {
//        Image profileImage = imagesService.saveImage(userDto.getFile());
//        log.info(userDto.getDob());
//        log.info("DOB => " + (LocalDate.parse(userDto.getDob())).toString());
//
//        return User.builder()
//                .address(userDto.getAddress())
//                .email(userDto.getEmail())
//                .dob(LocalDate.parse(userDto.getDob()))
//                .otherName(userDto.getOtherName())
//                .username(userDto.getUsername())
//                .wallet(userDto.getWallet())
//                .gender(userDto.getGender())
//                .phoneNumber(userDto.getPhoneNumber())
//                .lastName(userDto.getLastName())
//                .firstName(userDto.getFirstName())
//                .profileImage(profileImage)
//                .password(new Serialize().passwordEncoder.encode(userDto.getPassword()))
//                .email(userDto.getEmail())
//                .build();
//    }
//}

package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.dto.request.UpateUserDto;
import com.brainstem.myestate.dto.response.ImageResponse;
import com.brainstem.myestate.dto.ResponseObject;
import com.brainstem.myestate.dto.response.UserDtoResponse;
import com.brainstem.myestate.model.*;
import com.brainstem.myestate.dto.request.LoginDto;
import com.brainstem.myestate.dto.request.UserDto;
import com.brainstem.myestate.repository.*;
import com.brainstem.myestate.security.JwtAuthResponse;
import com.brainstem.myestate.security.JwtTokenProvider;
import com.brainstem.myestate.service.ImagesService;
import com.brainstem.myestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final RoleRepository roleRepository;
    private final ImagesService imagesService;

    Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //    @Autowired // Not needed as we only need one instance/ constructor of the repo
    public UserServiceImpl(
            UserRepository userRepository,
            WalletRepository walletRepository,
            RoleRepository roleRepository,
            ImagesService imagesService
            ) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.roleRepository = roleRepository;
        this.imagesService = imagesService;
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        Optional<User> user;
        boolean userExist = userRepository.existsByEmail(loginDto.getUsernameOrEmail()) ||
                userRepository.existsByUsername(loginDto.getUsernameOrEmail());
        if(!userExist){
            return new JwtAuthResponse(
                    "User " + loginDto.getUsernameOrEmail() + " does not exist", null);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //generate token from jwtTokenProvider
        String token = jwtTokenProvider.generateToken(authentication);
        if(loginDto.getUsernameOrEmail().contains("@")){
            user = userRepository.findByEmail(loginDto.getUsernameOrEmail());
        }else {
            user = userRepository.findByUsername(loginDto.getUsernameOrEmail());
        }
        return new JwtAuthResponse(token, mapToDto(user.get()));
    }

    @Override
    public ResponseObject updateProfile(long userId, UpateUserDto updateUserDto) throws Exception {
        //save new image in db
        Image image = imagesService.saveImage(updateUserDto.getFile());
        //get user reference from db
        Optional<User> userEntity = userRepository.findById(userId);
        //set new value to user reference
        userEntity.get().setAddress(updateUserDto.getAddress());
        userEntity.get().setGender(updateUserDto.getGender());
        userEntity.get().setOtherName(updateUserDto.getOtherName());
        userEntity.get().setGender(updateUserDto.getGender());
        userEntity.get().setPhoneNumber(updateUserDto.getPhoneNumber());
        userEntity.get().setProfileImage(image);
        //return mapped user dto
        return new ResponseObject(
                mapToDto(userEntity.get()), userEntity.get().getUsername() + " is Successfully updated");
    }

    @Override
    public ResponseObject signUp(UserDto userDto) throws Exception {
        Wallet wallet = new Wallet();

        //check if username already exist
        if(userRepository.existsByUsername(userDto.getUsername())){
            return new ResponseObject(null, userDto.getUsername() + " already exist!");
        }

        //check if email already exist
        if(userRepository.existsByEmail(userDto.getEmail())){
            return new ResponseObject(null, userDto.getEmail() + " already exist!");
        }

        log.info("USER DTO => " + userDto);

        //map userDto to entity
        User user = mapToEntity(userDto);

        //save image
        if(userDto.getFile() != null) {
            Image image = imagesService.saveImage(userDto.getFile());
            //set image to userDto
            user.setProfileImage(image);
        }

        log.info("USER DTO MAP TO USER => " + user);

        //initiate wallet
        user.setWallet(wallet);

        //Add roles
        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));

        //save wallet in db
        walletRepository.save(wallet);

        log.info("USER DTO MAP TO USER => " + user);

        //save user to db
        User entity = userRepository.save(user);

        log.info("USER ENTITY FROM DB => " + entity);
        //return response
        return new ResponseObject(mapToDto(entity), "User " + userDto.getUsername() + " created Successfully");
    }



    private UserDtoResponse mapToDto(User user) {
        ImageResponse profileImage = null;
        if(user.getProfileImage() != null) {
            Image image = user.getProfileImage();
            profileImage = new ImageResponse(image.getFileName(), image.getUrl(), image.getFileType(), image.getSize());
        }
        return UserDtoResponse.builder()
                .id(user.getId())
                .age(user.getAge())
                .address(user.getAddress())
                .email(user.getEmail())
                .dob(user.getDob().toString())
                .otherName(user.getOtherName())
                .username(user.getUsername())
                .wallet(user.getWallet())
                .gender(user.getGender())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .isActive(user.isActive())
                .phoneNumber(user.getPhoneNumber())
                .registeredDate(user.getRegisteredDate())
                .updatedDate(user.getUpdatedDate())
                .email(user.getEmail())
                .profileImage(profileImage)
                .build();
    }

    private User mapToEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .dob(LocalDate.parse(userDto.getDob()))
                .username(userDto.getUsername())
                .gender(userDto.getGender())
                .phoneNumber(userDto.getPhoneNumber())
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .build();
    }

}

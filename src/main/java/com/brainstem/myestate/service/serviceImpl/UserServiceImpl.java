package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.ResponseObject;
import com.brainstem.myestate.model.*;
import com.brainstem.myestate.payload.LoginDto;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.repository.*;
import com.brainstem.myestate.security.JwtTokenProvider;
import com.brainstem.myestate.service.UserService;
import com.brainstem.myestate.utils.Serialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final RoleRepository roleRepository;

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
            RoleRepository roleRepository
            ) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //generate token from jwtTokenProvider
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public ResponseObject updateProfile(long UserId, UserDto userDto) {
        return null;
    }

    @Override
    public ResponseObject SignUp(UserDto userDto) {
        Wallet wallet = new Wallet();

        //check if username already exist
        if(userRepository.existsByUsername(userDto.getUsername())){
            return new ResponseObject(null, userDto.getUsername() + " already exist!");
        }

        //check if email already exist
        if(userRepository.existsByEmail(userDto.getEmail())){
            return new ResponseObject(null, userDto.getEmail() + " already exist!");
        }
        //map userDto to entity
        User user = mapToEntity(userDto);
        //initiate wallet
        user.setWallet(wallet);
        //Add roles
         Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        //save wallet in db
        walletRepository.save(wallet);
        //save user to db
        User entity = userRepository.save(user);
        //return response
        return new ResponseObject(mapToDto(entity), "User " + userDto.getUsername() + " created Successfully");
    }



    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
//                .age(user.getAge())
                .address(user.getAddress())
                .email(user.getEmail())
//                .dob(user.getDob())
                .otherName(user.getOtherName())
                .username(user.getUsername())
                .wallet(user.getWallet())
                .gender(user.gender())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
//                .profileImage(user.getProfileImage().getId())
                .build();
    }

    private User mapToEntity(UserDto userDto) {
        return User.builder()
//                .age(userDto.getAge())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
//                .dob(userDto.getDob())
                .otherName(userDto.getOtherName())
                .username(userDto.getUsername())
                .wallet(userDto.getWallet())
                .gender(userDto.getGender())
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
//                .profileImage(userDto.setProfileImage())
                .build();
    }

}

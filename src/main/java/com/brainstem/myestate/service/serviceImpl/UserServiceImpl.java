package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.model.*;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.repository.*;
import com.brainstem.myestate.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;
    public WalletRepository walletRepository;

//    @Autowired // Not needed as we only need one instance/ constructor of the repo
    public UserServiceImpl(
            UserRepository userRepository,
            WalletRepository walletRepository
            ) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .address(user.getAddress())
                .email(user.getEmail())
                .dob(user.getDob())
                .otherName(user.getOtherName())
                .username(user.getUsername())
                .wallet(user.getWallet())
                .gender(user.gender())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .build();
    }

    private User mapToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .age(userDto.getAge())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .dob(userDto.getDob())
                .otherName(userDto.getOtherName())
                .username(userDto.getUsername())
                .wallet(userDto.getWallet())
                .gender(userDto.getGender())
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .email(userDto.getEmail())
                .build();
    }

    @Override
    public UserDto signUp(UserDto userDto) {
        User user = mapToEntity(userDto);
        User newUser = userRepository.save(user);
        return mapToDto(newUser);
    }

    @Override
    public UserDto login(String email, String password) {
        if(!email.contains("@") && !email.contains(".com") && email.trim().length() < 3 && password.trim().length() < 6){
            return null;
        }

        return null;
    }

    @Override
    public UserDto updateProfile(long UserId, UserDto userDto) {
        return null;
    }

    User validateUserDetails(UserDto userInput){
        if(userInput.getFirstName().length() >= 3
            && userInput.getLastName().length() >= 3
                && userInput.getEmail().contains("@.com")){

        }

        return mapToEntity(userInput);
    }


}

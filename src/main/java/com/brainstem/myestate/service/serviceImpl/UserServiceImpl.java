package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.model.*;
import com.brainstem.myestate.payload.LoginDto;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.repository.*;
import com.brainstem.myestate.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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
//                .id(user.getId())
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
                .password(userDto.getPassword())
                .email(userDto.getEmail())
//                .profileImage(userDto.setProfileImage())
                .build();
    }


    @Override
    public UserDto login(LoginDto loginDto) {
        if(loginDto.getEmail().isEmpty() && loginDto.getPassword().length() < 6){
            throw new RuntimeException("Invalid input");
        }
            User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
            if (user == null) {
                throw new RuntimeException("User is not registered!");
            }
        System.out.println("User Entity" + user);
        return mapToDto(user);
    }

    @Override
    public UserDto updateProfile(long UserId, UserDto userDto) {
        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Wallet wallet = new Wallet();

        Optional<User> isEmailPresent = userRepository.findByEmail(userDto.getEmail());

        if (isEmailPresent.isPresent()){
            throw new RuntimeException("User already registered, please log in");
        };
        User newUser = mapToEntity(userDto);
        newUser.setWallet(wallet);

        wallet.setAmount(BigDecimal.valueOf(0));
        wallet.setWalletAddress(wallet.getWalletAddress());

        walletRepository.save(wallet);
        userRepository.save(newUser);

        System.out.println(newUser);
        System.out.println(newUser);

        return mapToDto(newUser);
    }
}

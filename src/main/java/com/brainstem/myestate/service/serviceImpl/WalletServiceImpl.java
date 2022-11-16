package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.model.Wallet;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.payload.WalletDto;
import com.brainstem.myestate.repository.UserRepository;
import com.brainstem.myestate.repository.WalletRepository;
import com.brainstem.myestate.service.UserService;
import com.brainstem.myestate.service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    UserRepository userRepository;
    WalletRepository walletRepository;

    public WalletServiceImpl(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }


    private WalletDto mapToWalletDto(Wallet wallet) {
        WalletDto walletDto = WalletDto.builder()
                .amount(wallet.getAmount())
                .user(wallet.getUser())
                .walletAddress(wallet.getWalletAddress())
                .build();
        return walletDto;
    }

    private Wallet mapToWalletEntity(WalletDto walletDto) {
        return Wallet.builder()
                .amount(walletDto.getAmount())
                .walletAddress(walletDto.getWalletAddress())
                .user(walletDto.getUser())
                .build();
    }

    @Override
    public WalletDto createWallet(UserDto userDto) {

        return null;
    }

    @Override
    public BigDecimal getBalance(long userId) {

        return null;
    }

    @Override
    public WalletDto getWalletByWalletAddress(String walletAddress) {
        return null;
    }

    @Override
    public BigDecimal deposit(long userId, BigDecimal amount) {
        return null;
    }

    @Override
    public WalletDto withdraw(long userId, BigDecimal amount) {
        return null;
    }
}
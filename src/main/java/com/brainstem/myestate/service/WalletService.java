package com.brainstem.myestate.service;

import com.brainstem.myestate.model.User;
import com.brainstem.myestate.model.Wallet;
import com.brainstem.myestate.payload.UserDto;
import com.brainstem.myestate.payload.WalletDto;

import java.math.BigDecimal;

public interface WalletService {
    WalletDto createWallet(UserDto userDto);
    BigDecimal getBalance(long userId);
    WalletDto getWalletByWalletAddress(String walletAddress);
    BigDecimal deposit(long userId, BigDecimal amount);
    WalletDto withdraw(long userId, BigDecimal amount);
}

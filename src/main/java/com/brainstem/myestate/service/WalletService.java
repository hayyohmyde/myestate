package com.brainstem.myestate.service;

import com.brainstem.myestate.dto.request.UserDto;
import com.brainstem.myestate.dto.request.WalletDto;

import java.math.BigDecimal;

public interface WalletService {
    WalletDto createWallet(UserDto userDto);
    BigDecimal getBalance(long userId);
    WalletDto getWalletByWalletAddress(String walletAddress);
    BigDecimal deposit(long userId, BigDecimal amount);
    WalletDto withdraw(long userId, BigDecimal amount);
}

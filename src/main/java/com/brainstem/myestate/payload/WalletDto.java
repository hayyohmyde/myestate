package com.brainstem.myestate.payload;

import com.brainstem.myestate.model.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class WalletDto {
    private Long id;
    private UUID walletAddress;
    private BigDecimal amount;
    private User user;
}

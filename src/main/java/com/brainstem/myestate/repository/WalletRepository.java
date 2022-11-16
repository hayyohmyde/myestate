package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}

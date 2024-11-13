package com.javacode.wallet.repository;

import com.javacode.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepo extends JpaRepository<Wallet, UUID> {
}

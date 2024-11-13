package com.javacode.wallet.repository;

import com.javacode.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet, String> {
}

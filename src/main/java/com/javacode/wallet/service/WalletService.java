package com.javacode.wallet.service;

import com.javacode.wallet.dto.WalletDto;
import com.javacode.wallet.model.Wallet;
import com.javacode.wallet.repository.WalletRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepo walletRepo;

    public Wallet getWallet(String  walletId) {
        return walletRepo.getReferenceById(walletId);
    }

    public Wallet addAmount(WalletDto walletDto) {

        Wallet wallet = walletRepo.getReferenceById(walletDto.getWalletId());
        wallet.setAmount(wallet.getAmount() + walletDto.getAmount());
        walletRepo.save(wallet);
        return wallet;
    }

}


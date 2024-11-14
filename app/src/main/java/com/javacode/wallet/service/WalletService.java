package com.javacode.wallet.service;

import com.javacode.wallet.custom.Type;
import com.javacode.wallet.dto.RequestWalletDto;
import com.javacode.wallet.model.Wallet;
import com.javacode.wallet.repository.WalletRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepo walletRepo;

    public Optional<Wallet> getWallet(UUID walletId) {
        return walletRepo.findById(walletId);
    }

    public ResponseEntity<String > addAmount(RequestWalletDto requestWalletDto) {

        Optional<Wallet> opt = walletRepo.findById(requestWalletDto.getWalletId());
        if (opt.isEmpty()) {
            if (requestWalletDto.getOperationType().equals(Type.WITHDRAW)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("wallet %s not found", requestWalletDto.getWalletId()));
            } else {
                Wallet wallet = new Wallet(requestWalletDto.getWalletId(), requestWalletDto.getAmount());
                walletRepo.save(wallet);
                return ResponseEntity.status(HttpStatus.CREATED).body(wallet.toString());
            }
        } else {
            Wallet wallet = opt.get();
            if (requestWalletDto.getOperationType().equals(Type.WITHDRAW)) {
                int diff = wallet.getAmount() - requestWalletDto.getAmount();
                if (diff < 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(String.format("In the wallet %s not enough money", requestWalletDto.getWalletId()));
                }
                wallet.setAmount(diff);
                walletRepo.save(wallet);
                return ResponseEntity.ok(wallet.toString());
            } else {
                int summ = wallet.getAmount() + requestWalletDto.getAmount();
                wallet.setAmount(summ);
                walletRepo.save(wallet);
                return ResponseEntity.ok(wallet.toString());
            }
        }
    }

}


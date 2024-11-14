package com.javacode.wallet.controller;

import com.javacode.wallet.dto.RequestWalletDto;
import com.javacode.wallet.dto.WalletDto;
import com.javacode.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {
    @Autowired
    private WalletService service;

    @GetMapping("/wallets/{Wallet_UUID}")
    public ResponseEntity<WalletDto> getWallet(@PathVariable("Wallet_UUID") UUID walletId) {
        return service.getWallet(walletId)
                .map(wallet -> ResponseEntity.ok().body(new WalletDto(wallet.getWalletId(), wallet.getAmount())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/wallet")
    public ResponseEntity<String> addAmount(@RequestBody RequestWalletDto requestWalletDto) {
        if (requestWalletDto.getOperationType() == null
                || requestWalletDto.getWalletId() == null
                || requestWalletDto.getAmount() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Json not valid.");
        }
        return service.addAmount(requestWalletDto);
    }
}

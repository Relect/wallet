package com.javacode.wallet.Controller;

import com.javacode.wallet.dto.WalletDto;
import com.javacode.wallet.model.Wallet;
import com.javacode.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class Controller {
    @Autowired
    private WalletService service;

    @GetMapping("/wallets/{Wallet_UUID}")
    public ResponseEntity<Wallet> getWallet(@PathVariable String walletId) {
        return Optional
                .ofNullable(service.getWallet(walletId))
                .map(wallet -> ResponseEntity.ok().body(wallet))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/wallet")
    public Wallet addAmount(@RequestBody WalletDto walletDto) {
        return service.addAmount(walletDto);
    }
}

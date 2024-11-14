package com.javacode.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class WalletDto {

    private UUID walletId;
    private int amount;
}

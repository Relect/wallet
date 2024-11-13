package com.javacode.wallet.dto;

import com.javacode.wallet.custom.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RequestWalletDto {

    private UUID walletId;
    private Type operationType;
    private int amount;
}

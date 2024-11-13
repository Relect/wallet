package com.javacode.wallet.dto;

import com.javacode.wallet.custom.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class WalletDto {

    private String  walletId;
    private Type operationType;
    private int amount;
}

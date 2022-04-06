package com.example.bankaccount.api.dto;

import lombok.Data;

@Data
public class WithdrawRequest {
    private String accountId;
    private Long amount;
}

package com.example.bankaccount.command.dto;

import lombok.Data;

@Data
public class WithdrawRequest {
    private String accountId;
    private Long amount;
}

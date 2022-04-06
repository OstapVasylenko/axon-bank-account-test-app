package com.example.bankaccount.command.dto;

import lombok.Data;

@Data
public class DepositRequest {

    private String accountId;
    private Long amount;

}

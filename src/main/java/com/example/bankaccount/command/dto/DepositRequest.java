package com.example.bankaccount.api.dto;

import lombok.Data;

@Data
public class DepositRequest {

    private String accountId;
    private Long amount;

}

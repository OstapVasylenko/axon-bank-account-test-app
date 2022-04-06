package com.example.bankaccount.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByIdRequest {

    private String accountId;

}

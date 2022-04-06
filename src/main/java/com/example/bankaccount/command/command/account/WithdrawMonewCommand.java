package com.example.bankaccount.api.command.account;

import com.example.bankaccount.api.command.BaseCommand;
import lombok.Getter;

public class WithdrawMonewCommand extends BaseCommand<String> {

    @Getter private final Long amount;

    public WithdrawMonewCommand(String id, Long amount) {
        super(id);
        this.amount = amount;
    }
}

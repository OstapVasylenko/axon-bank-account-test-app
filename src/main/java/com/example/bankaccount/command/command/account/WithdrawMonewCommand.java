package com.example.bankaccount.command.command.account;

import com.example.bankaccount.command.command.BaseCommand;
import lombok.Getter;

public class WithdrawMonewCommand extends BaseCommand<String> {

    @Getter private final Long amount;

    public WithdrawMonewCommand(String id, Long amount) {
        super(id);
        this.amount = amount;
    }
}

package com.example.bankaccount.command.command.account;

import com.example.bankaccount.command.command.BaseCommand;
import lombok.Getter;

public class CreateAccountCommand extends BaseCommand<String> {

    @Getter private final Long balance;

    public CreateAccountCommand(String id, Long balance) {
        super(id);
        this.balance = balance;
    }
}

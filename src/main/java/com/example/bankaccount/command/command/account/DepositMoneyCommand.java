package com.example.bankaccount.command.command.account;

import com.example.bankaccount.command.event.BaseEvent;
import lombok.Getter;

public class DepositMoneyCommand extends BaseEvent<String> {

    @Getter private final Long amount;

    public DepositMoneyCommand(String id, Long amount) {
        super(id);
        this.amount = amount;
    }
}

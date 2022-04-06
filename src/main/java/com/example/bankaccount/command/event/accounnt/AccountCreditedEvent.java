package com.example.bankaccount.command.event.accounnt;

import com.example.bankaccount.command.event.BaseEvent;
import lombok.Getter;

public class AccountCreditedEvent extends BaseEvent<String> {

    @Getter private final Long balance;

    public AccountCreditedEvent(String id, Long balance) {
        super(id);
        this.balance = balance;
    }
}

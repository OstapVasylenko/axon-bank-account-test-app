package com.example.bankaccount.command.event.accounnt;

import com.example.bankaccount.command.event.BaseEvent;
import lombok.Getter;

public class AccountDebitedEvent extends BaseEvent<String> {

    @Getter private final Long amount;

    public AccountDebitedEvent(String id, Long amount) {
        super(id);
        this.amount = amount;
    }
}

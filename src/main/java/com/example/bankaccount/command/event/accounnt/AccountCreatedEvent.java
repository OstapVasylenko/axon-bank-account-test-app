package com.example.bankaccount.command.event.accounnt;

import com.example.bankaccount.command.event.BaseEvent;
import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String> {

    @Getter
    private final Long balance;
    @Getter
    private final String status;

    public AccountCreatedEvent(String id, Long balance, String status) {
        super(id);
        this.balance = balance;
        this.status = status;
    }
}

package com.example.bankaccount.command.event.accounnt;

import com.example.bankaccount.command.event.BaseEvent;
import lombok.Getter;

public class AccountActivatedEvent extends BaseEvent<String> {

    @Getter
    private final String status;
    @Getter
    private final boolean isActive;

    public AccountActivatedEvent(String id, String status, boolean isActive) {
        super(id);
        this.status = status;
        this.isActive = isActive;
    }

}

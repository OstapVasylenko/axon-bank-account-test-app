package com.example.bankaccount.command.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseEvent<T> {

    @TargetAggregateIdentifier
    private final T id;

    public BaseEvent(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}

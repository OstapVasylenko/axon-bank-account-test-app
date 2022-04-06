package com.example.bankaccount.command.aggregate;

import com.example.bankaccount.command.command.account.CreateAccountCommand;
import com.example.bankaccount.command.command.account.DepositMoneyCommand;
import com.example.bankaccount.command.command.account.WithdrawMonewCommand;
import com.example.bankaccount.command.event.accounnt.AccountActivatedEvent;
import com.example.bankaccount.command.event.accounnt.AccountCreatedEvent;
import com.example.bankaccount.command.event.accounnt.AccountCreditedEvent;
import com.example.bankaccount.command.event.accounnt.AccountDebitedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * Aggregate class that describes the account aggregate model
 * this class contains handlers for commands and events invoked
 * for 'banking-account' business object.
 */
@Aggregate
@Slf4j
public class AccountAggregate {

    @AggregateIdentifier
    private String aggregateId;
    private Long balance;
    private String status;

    public AccountAggregate() {

    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        log.info("CreateAccountCommand invoked");

        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(), createAccountCommand.getBalance(), "CREATED"));
    }

    @EventSourcingHandler
    public void handle(AccountCreatedEvent accountCreatedEvent){
        log.info("AccountCreatedEvent invoked");

        this.aggregateId = accountCreatedEvent.getId();
        this.balance = accountCreatedEvent.getBalance();
        this.status = accountCreatedEvent.getStatus();


        AggregateLifecycle.apply(new AccountActivatedEvent(this.aggregateId, "ACTIVATED", true));

    }

    @EventSourcingHandler
    public void handle(AccountActivatedEvent accountActivatedEvent){
        log.info("AccountActivatedEvent invoked");

        this.status = accountActivatedEvent.getStatus();
    }

    @CommandHandler
    public void handle(DepositMoneyCommand depositMoneyCommand) {

        log.info("DepositMoneyCommand invoked");

        AggregateLifecycle.apply(new AccountCreditedEvent(this.aggregateId, depositMoneyCommand.getAmount()));

    }

    @EventSourcingHandler
    public void handle(AccountCreditedEvent accountCreditedEvent) {
        log.info("AccountCreditedEvent invoked");

        this.balance = Long.sum(this.balance, accountCreditedEvent.getBalance());
    }

    @CommandHandler
    public void handle(WithdrawMonewCommand withdrawMonewCommand) {

        log.info("WithdrawMonewCommand invoked");

        AggregateLifecycle.apply(new AccountDebitedEvent(this.aggregateId, withdrawMonewCommand.getAmount()));

    }

    @EventHandler
    public void handle(AccountDebitedEvent accountDebitedEvent) {
        log.info("AccountDebitedEvent invoked");

        this.balance = this.balance - accountDebitedEvent.getAmount();

    }

}

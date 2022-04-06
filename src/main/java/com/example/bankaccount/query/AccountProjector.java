package com.example.bankaccount.query;

import com.example.bankaccount.command.event.accounnt.AccountActivatedEvent;
import com.example.bankaccount.command.event.accounnt.AccountCreatedEvent;
import com.example.bankaccount.command.event.accounnt.AccountCreditedEvent;
import com.example.bankaccount.command.event.accounnt.AccountDebitedEvent;
import com.example.bankaccount.query.entity.Account;
import com.example.bankaccount.query.query.FindAccountByIdQuery;
import com.example.bankaccount.query.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountProjector {

    private final AccountRepository accountRepository;

    public AccountProjector(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent){
        log.info("Handling account creation");

        Account account = new Account();
        account.setAccountId(accountCreatedEvent.getId());
        account.setBalance(accountCreatedEvent.getBalance());
        account.setStatus(accountCreatedEvent.getStatus());
        account.setEnabled(false);

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent accountActivatedEvent){
        log.info("Handling account activation");

        Account account = accountRepository.findById(accountActivatedEvent.getId()).orElse(null);

        if(account != null) {
            account.setStatus(accountActivatedEvent.getStatus());
            account.setEnabled(accountActivatedEvent.isActive());
            accountRepository.save(account);
        }

    }

    @EventHandler
    public void on(AccountCreditedEvent accountCreditedEvent){
        log.info("Handling account credit adding");

        Account account = accountRepository.findById(accountCreditedEvent.getId()).orElse(null);

        if(account != null) {
            account.setBalance(Long.sum(account.getBalance(), accountCreditedEvent.getBalance()));
            accountRepository.save(account);
        }

    }

    @EventHandler
    public void on(AccountDebitedEvent accountDebitedEvent){
        log.info("Handling account debited action");

        Account account = accountRepository.findById(accountDebitedEvent.getId()).orElse(null);

        if(account != null) {
            account.setBalance(account.getBalance() - accountDebitedEvent.getAmount());
            accountRepository.save(account);
        }

    }

    @QueryHandler
    public Account on(FindAccountByIdQuery findAccountByIdQuery) {
        log.info("Obtain account by id");

        return accountRepository.findById(findAccountByIdQuery.getAccountId()).orElse(null);
    }

}

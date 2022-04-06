package com.example.bankaccount.command.service;

import com.example.bankaccount.command.command.account.CreateAccountCommand;
import com.example.bankaccount.command.command.account.DepositMoneyCommand;
import com.example.bankaccount.command.command.account.WithdrawMonewCommand;
import com.example.bankaccount.command.dto.CreateAccountRequest;
import com.example.bankaccount.command.dto.DepositRequest;
import com.example.bankaccount.command.dto.WithdrawRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createAccount(CreateAccountRequest createAccountRequest){

        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), createAccountRequest.getStartAccountBalance()));

    }

    public CompletableFuture<String> depositeToAccount(DepositRequest depositRequest){

        return commandGateway.send(new DepositMoneyCommand(depositRequest.getAccountId(), depositRequest.getAmount()));

    }

    public CompletableFuture<String> withdrawFromAccount(WithdrawRequest withdrawRequest){

        return commandGateway.send(new WithdrawMonewCommand(withdrawRequest.getAccountId(), withdrawRequest.getAmount()));

    }

}

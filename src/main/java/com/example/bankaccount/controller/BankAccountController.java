package com.example.bankaccount.controller;

import com.example.bankaccount.command.dto.CreateAccountRequest;
import com.example.bankaccount.command.dto.DepositRequest;
import com.example.bankaccount.command.dto.WithdrawRequest;
import com.example.bankaccount.command.service.AccountCommandService;
import com.example.bankaccount.query.entity.Account;
import com.example.bankaccount.query.query.FindAccountByIdQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    private final AccountCommandService accountCommandService;
    private final QueryGateway queryGateway;

    public BankAccountController(AccountCommandService accountCommandService, QueryGateway queryGateway) {
        this.accountCommandService = accountCommandService;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {

        try {
            CompletableFuture<String> account = accountCommandService.createAccount(createAccountRequest);
            return new ResponseEntity<>(account.get(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/deposit")
    public ResponseEntity<String> addDeposit(@RequestBody DepositRequest depositRequest) {

        try {

            CompletableFuture<String> depositeRes = accountCommandService.depositeToAccount(depositRequest);

            return new ResponseEntity<>("AmountCredited " + depositeRes.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestBody WithdrawRequest withdrawRequest) {

        try {
            CompletableFuture<String> withdrawRes = accountCommandService.withdrawFromAccount(withdrawRequest);
            return new ResponseEntity<>("Amount Debited " + withdrawRes.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id){

        Account account = queryGateway.query(new FindAccountByIdQuery(id), Account.class).join();

        if(account == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}

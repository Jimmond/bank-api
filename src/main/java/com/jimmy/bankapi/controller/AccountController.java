package com.jimmy.bankapi.controller;

import com.jimmy.bankapi.dto.CreateAccountRequest;
import com.jimmy.bankapi.entity.Account;
import com.jimmy.bankapi.entity.TransactionHistory;
import com.jimmy.bankapi.service.AccountService;
import org.springframework.web.bind.annotation.*;
import com.jimmy.bankapi.dto.DepositRequest;
import java.util.List;
import com.jimmy.bankapi.dto.WithdrawRequest;
import com.jimmy.bankapi.dto.TransferRequest;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(
            AccountService accountService
    ) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(
            @RequestBody CreateAccountRequest request
    ) {
        return accountService.createAccount(request);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @PostMapping("/{id}/deposit")
    public Account deposit(
            @PathVariable Long id,
            @RequestBody DepositRequest request
    ) {

        return accountService.deposit(
                id,
                request.amount()
        );
    }
    @PostMapping("/{id}/withdraw")
    public Account withdraw(
            @PathVariable Long id,
            @RequestBody WithdrawRequest request
    ) {

        return accountService.withdraw(
                id,
                request.amount()
        );
    }
    @PostMapping("/transfer")
    public String transfer(
            @RequestBody TransferRequest request
    ) {

        accountService.transfer(
                request.sourceAccountId(),
                request.targetAccountId(),
                request.amount()
        );

        return "Transfer completed successfully";
    }
    @GetMapping("/{id}")
    public Account getAccountById(
            @PathVariable Long id
    ) {

        return accountService.getAccountById(id);
    }
    @GetMapping("/number/{accountNumber}")
    public Account getByAccountNumber(
            @PathVariable String accountNumber
    ) {

        return accountService.getByAccountNumber(
                accountNumber
        );
    }
    @GetMapping("/{id}/transactions")
    public List<TransactionHistory> getTransactions(
            @PathVariable Long id
    ) {

        return accountService.getTransactions(id);
    }
}
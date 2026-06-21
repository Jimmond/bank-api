package com.jimmy.bankapi.service;

import com.jimmy.bankapi.dto.CreateAccountRequest;
import com.jimmy.bankapi.entity.Account;
import com.jimmy.bankapi.entity.Customer;
import com.jimmy.bankapi.entity.TransactionHistory;
import com.jimmy.bankapi.entity.TransactionType;
import com.jimmy.bankapi.repository.AccountRepository;
import com.jimmy.bankapi.repository.CustomerRepository;
import com.jimmy.bankapi.repository.TransactionHistoryRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.jimmy.bankapi.exception.AccountNotFoundException;
import com.jimmy.bankapi.exception.InsufficientFundsException;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public AccountService(
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            TransactionHistoryRepository transactionHistoryRepository
    ) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionHistoryRepository =
                transactionHistoryRepository;
    }

    public Account createAccount(
            CreateAccountRequest request
    ) {

        Customer customer =
                customerRepository.findById(
                        request.customerId()
                ).orElseThrow(() ->
                new AccountNotFoundException(
                        "Account not found"
                ));

        Account account = Account.builder()
                .accountNumber(request.accountNumber())
                .balance(request.initialBalance())
                .customer(customer)
                .build();

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public Account deposit(
            Long accountId,
            BigDecimal amount
    ) {

        Account account =
                accountRepository.findById(accountId)
                        .orElseThrow(() ->
                new AccountNotFoundException(
                        "Account not found"
                ));

        account.setBalance(
                account.getBalance().add(amount)
        );

        Account savedAccount =
                accountRepository.save(account);

        TransactionHistory transaction =
                TransactionHistory.builder()
                        .transactionType(
                                TransactionType.DEPOSIT
                        )
                        .amount(amount)
                        .description(
                                "Cash deposit"
                        )
                        .transactionDate(
                                LocalDateTime.now()
                        )
                        .account(savedAccount)
                        .build();

        transactionHistoryRepository.save(
                transaction
        );

        return savedAccount;
    }

    public Account withdraw(
            Long accountId,
            BigDecimal amount
    ) {

        Account account =
                accountRepository.findById(accountId)
                        .orElseThrow(() ->
                                new AccountNotFoundException(
                                        "Account not found"
                                ));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                    "Account balance is insufficient"
            );
        }

        account.setBalance(
                account.getBalance().subtract(amount)
        );

        Account savedAccount =
                accountRepository.save(account);

        TransactionHistory transaction =
                TransactionHistory.builder()
                        .transactionType(
                                TransactionType.WITHDRAW
                        )
                        .amount(amount)
                        .description(
                                "Cash withdrawal"
                        )
                        .transactionDate(
                                LocalDateTime.now()
                        )
                        .account(savedAccount)
                        .build();

        transactionHistoryRepository.save(
                transaction
        );

        return savedAccount;
    }
    @Transactional
    public void transfer(
            Long sourceAccountId,
            Long targetAccountId,
            BigDecimal amount
    ) {

        Account sourceAccount =
                accountRepository.findById(sourceAccountId)
                        .orElseThrow(() ->
                                new AccountNotFoundException(
                                        "Account not found"
                                ));

        Account targetAccount =
                accountRepository.findById(targetAccountId)
                        .orElseThrow(() ->
                                new AccountNotFoundException(
                                        "Account not found"
                                ));

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                    "Account balance is insufficient"
            );
        }

        sourceAccount.setBalance(
                sourceAccount.getBalance().subtract(amount)
        );

        targetAccount.setBalance(
                targetAccount.getBalance().add(amount)
        );

        Account savedSourceAccount =
                accountRepository.save(sourceAccount);

        Account savedTargetAccount =
                accountRepository.save(targetAccount);
        TransactionHistory transferOut =
                TransactionHistory.builder()
                        .transactionType(
                                TransactionType.TRANSFER_OUT
                        )
                        .amount(amount)
                        .description(
                                "Transfer to account "
                                        + targetAccount.getAccountNumber()
                        )
                        .transactionDate(
                                LocalDateTime.now()
                        )
                        .account(savedSourceAccount)
                        .build();

        transactionHistoryRepository.save(
                transferOut
        );

        TransactionHistory transferIn =
                TransactionHistory.builder()
                        .transactionType(
                                TransactionType.TRANSFER_IN
                        )
                        .amount(amount)
                        .description(
                                "Transfer from account "
                                        + sourceAccount.getAccountNumber()
                        )
                        .transactionDate(
                                LocalDateTime.now()
                        )
                        .account(savedTargetAccount)
                        .build();

        transactionHistoryRepository.save(
                transferIn
        );
    }

    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found: " + id
                        ));
    }
    public Account getByAccountNumber(
            String accountNumber
    ) {

        return accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found"
                        ));
    }
    public List<TransactionHistory> getTransactions(
            Long accountId
    ) {

        return transactionHistoryRepository
                .findByAccountId(accountId);
    }
}
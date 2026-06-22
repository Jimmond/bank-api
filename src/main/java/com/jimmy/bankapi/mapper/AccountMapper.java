package com.jimmy.bankapi.mapper;

import com.jimmy.bankapi.dto.response.AccountResponse;
import com.jimmy.bankapi.entity.Account;

import java.util.List;

public class AccountMapper {

    public static AccountResponse toResponse(
            Account account
    ) {

        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCustomer().getFirstName()
                        + " "
                        + account.getCustomer().getLastName()
        );
    }
    public static List<AccountResponse> toResponseList(
            List<Account> accounts
    ) {

        return accounts.stream()
                .map(AccountMapper::toResponse)
                .toList();
    }
}
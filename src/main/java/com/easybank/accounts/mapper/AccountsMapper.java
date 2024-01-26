package com.easybank.accounts.mapper;

import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountDto(Accounts accounts, AccountsDto accountsDto){

        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());

        return accountsDto;
    }

    public static Accounts mapToAccounts(Accounts accounts, AccountsDto accountsDto){

        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}

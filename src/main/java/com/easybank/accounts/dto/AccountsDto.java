package com.easybank.accounts.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountsDto {

    private Long AccountNumber;

    private String accountType;

    private String branchAddress;


}

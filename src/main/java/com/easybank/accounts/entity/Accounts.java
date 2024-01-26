package com.easybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity {

    @NotNull
    private Long customerId;

    @Id
    private Long AccountNumber;

    @NotNull
    private String accountType;

    @NotNull
    private String branchAddress;
}

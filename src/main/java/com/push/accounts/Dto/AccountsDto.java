package com.push.accounts.Dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountsDto {
    @NotNull(message = "Account number cannot be null")
    @Digits(integer = 10, fraction = 0, message = "account number should be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "account type cannot be empty or null")
    private String accountType;
    @NotEmpty(message = "branch address cannot be empty or null")
    private String branchAddress;
}

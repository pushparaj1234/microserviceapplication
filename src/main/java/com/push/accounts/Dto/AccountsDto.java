package com.push.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {
    @NotNull(message = "Account number cannot be null")
    @Digits(integer = 10, fraction = 0, message = "account number should be 10 digits")
    @Schema(
            description = "Account Number of Bank account", example = "3454433243"
    )
    private Long accountNumber;
    @NotEmpty(message = "account type cannot be empty or null")
    @Schema(
            description = "Account type of Bank account", example = "Savings"
    )
    private String accountType;
    @NotEmpty(message = "branch address cannot be empty or null")
    @Schema(
            description = "Bank branch address", example = "123 NewYork"
    )
    private String branchAddress;
}

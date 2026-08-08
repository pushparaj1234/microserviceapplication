package com.push.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @NotEmpty(message = "Name cannot be empty")
    @Size(min=5, max=30, message="The length of the customer should be from 5 to 30")
    @Schema(
            description = "Name of the customer", example = "Pushparaj"
    )
    private String name;
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "Email address should be in valid format")
    @Schema(
            description = "Email address of the customer", example = "push@gmail.com"
    )
    private String email;
    @NotEmpty(message = "mobileNumber cannot be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "The mobile number must be 10 digits")
    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    private String mobileNumber;
    @Valid
    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;
}

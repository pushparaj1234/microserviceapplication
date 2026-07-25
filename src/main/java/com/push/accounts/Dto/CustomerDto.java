package com.push.accounts.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {
    @NotEmpty(message = "Name cannot be empty")
    @Size(min=5, max=30, message="The length of the customer should be from 5 to 30")
    private String name;
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "Email address should be in valid format")
    private String email;
    @NotEmpty(message = "mobileNumber cannot be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "The mobile number must be 10 digits")
    private String mobileNumber;
    @Valid
    private AccountsDto accountsDto;
}

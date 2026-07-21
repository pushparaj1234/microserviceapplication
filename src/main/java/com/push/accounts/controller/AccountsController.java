package com.push.accounts.controller;

import com.push.accounts.Dto.CustomerDto;
import com.push.accounts.Dto.ResponseDto;
import com.push.accounts.constants.AccountsConstant;
import com.push.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {
private IAccountsService accountsService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
            accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201,AccountsConstant.MESSAGE_201));
    }


}

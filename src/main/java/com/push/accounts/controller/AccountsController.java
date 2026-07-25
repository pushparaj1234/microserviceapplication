package com.push.accounts.controller;

import com.push.accounts.Dto.CustomerDto;
import com.push.accounts.Dto.ResponseDto;
import com.push.accounts.constants.AccountsConstant;
import com.push.accounts.repository.CustomerRepository;
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
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
         CustomerDto customer = accountsService.fetchAccount(mobileNumber);
         return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK).body(new ResponseDto(AccountsConstant.STATUS_200, AccountsConstant.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstant.STATUS_500, AccountsConstant.MESSAGE_500));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(AccountsConstant.STATUS_204,AccountsConstant.MESSAGE_204));
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstant.STATUS_500, AccountsConstant.MESSAGE_500));
        }
    }
}

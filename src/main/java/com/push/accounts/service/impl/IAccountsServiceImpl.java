package com.push.accounts.service.impl;

import com.push.accounts.Dto.CustomerDto;
import com.push.accounts.constants.AccountsConstant;
import com.push.accounts.entity.Accounts;
import com.push.accounts.entity.Customer;
import com.push.accounts.exception.DuplicateMobileNumberFoundException;
import com.push.accounts.mapper.CustomerMapper;
import com.push.accounts.repository.AccountsRepository;
import com.push.accounts.repository.CustomerRepository;
import com.push.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class IAccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        if(customerRepository.findBymobileNumber(customer.getMobileNumber()).isPresent()){
            throw new DuplicateMobileNumberFoundException("Customer with the following mobile number already exist" + customer.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer customerDetail = customerRepository.save(customer);
        Accounts account = createAccounts(customerDetail);
        accountsRepository.save(account);
    }
    private Accounts createAccounts(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(90000000);
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstant.SAVINGS);
        newAccount.setBranchAddress(AccountsConstant.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }
}

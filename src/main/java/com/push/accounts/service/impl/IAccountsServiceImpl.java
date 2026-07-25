package com.push.accounts.service.impl;

import com.push.accounts.Dto.AccountsDto;
import com.push.accounts.Dto.CustomerDto;
import com.push.accounts.constants.AccountsConstant;
import com.push.accounts.entity.Accounts;
import com.push.accounts.entity.Customer;
import com.push.accounts.exception.ResourceNotFoundException;
import com.push.accounts.exception.DuplicateMobileNumberFoundException;
import com.push.accounts.mapper.AccountsMapper;
import com.push.accounts.mapper.CustomerMapper;
import com.push.accounts.repository.AccountsRepository;
import com.push.accounts.repository.CustomerRepository;
import com.push.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
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

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findBymobileNumber(mobileNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "customerId",
                                customer.getCustomerId().toString()));

        CustomerDto customerDto =
                CustomerMapper.mapToCustomerDto(customer, new CustomerDto());

        customerDto.setAccountsDto(
                AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
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

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        Accounts accounts = accountsRepository.findByAccountNumber(accountsDto.getAccountNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "Accountnumber", accountsDto.getAccountNumber().toString())
        );
        if(accounts!=null) {
            Accounts UpdateAccounts = AccountsMapper.mapToAccounts(accountsDto,accounts);
            UpdateAccounts.setUpdatedAt(LocalDateTime.now());
            UpdateAccounts.setUpdatedBy("Anonymous");
            accountsRepository.save(UpdateAccounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer","CustomerId",customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customer.setUpdatedAt(LocalDateTime.now());
            customer.setUpdatedBy("Anonymous");
            customerRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted = false;
        Customer customer = customerRepository.findBymobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","MobileNumber",mobileNumber)
        );
        if(customer!=null){
            Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","MobileNumber",mobileNumber)
            );
            accountsRepository.delete(accounts);
            customerRepository.delete(customer);
            isDeleted = true;
        }
        return isDeleted;
    }


}

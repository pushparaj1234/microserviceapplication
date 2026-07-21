package com.push.accounts.service;

import com.push.accounts.Dto.CustomerDto;

public interface IAccountsService {
    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);
}

package com.royalreserve.accountservice.service;

import com.royalreserve.accountservice.dto.AccountRequest;
import com.royalreserve.accountservice.dto.AccountResponse;
import java.util.List;
import java.math.BigDecimal;

public interface AccountService {
    AccountResponse createAccount(AccountRequest request);
    AccountResponse getAccountById(String id);
    List<AccountResponse> getAllAccounts();
    void deleteAccount(String id);
    AccountResponse deposit(String id, BigDecimal amount);
    AccountResponse withdraw(String id, BigDecimal amount);
}

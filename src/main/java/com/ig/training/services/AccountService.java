package com.ig.training.services;

import com.ig.training.dto.AccountDto;
import com.ig.training.model.Account;
import org.springframework.data.domain.Page;

public interface AccountService {
    Account createAccount(Account req);
    Account updateAccount(Long id, AccountDto req);
    Account getAccountDetail(Long id);
    Page<Account> getAccountList(String query, int page, int size);
    void deleteAccount(Long id);
}

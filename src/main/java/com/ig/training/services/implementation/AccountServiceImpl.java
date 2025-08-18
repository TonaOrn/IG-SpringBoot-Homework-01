package com.ig.training.services.implementation;

import com.ig.training.dto.AccountDto;
import com.ig.training.exceptions.ApiErrorException;
import com.ig.training.model.Account;
import com.ig.training.repositories.AccountRepository;
import com.ig.training.services.AccountService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(Account req) {
        return accountRepository.save(req);
    }

    @Override
    public Account updateAccount(Long id, AccountDto req) {
        Account accountObj = getAccountDetail(id);
        Account updatedAccount = req.updateAccount(accountObj);
        return accountRepository.save(updatedAccount);
    }

    @Override
    public Account getAccountDetail(Long id) {
        return accountRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ApiErrorException(404, "Account not found"));
    }

    @Override
    public Page<Account> getAccountList(String query, int page, int size) {
        return accountRepository.findAll((root, cq, cb) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();

            if (query != null) {
                var searchAccountName = cb.like(cb.upper(root.get("accountHolderName")), "%" + query.toUpperCase() + "%");
                var searchAccountNum = cb.like(cb.upper(root.get("accountNumber")), "%" + query.toUpperCase() + "%");
                var searchAccountType = cb.like(cb.upper(root.get("accountType")), "%" + query.toUpperCase() + "%");
                predicates.add(cb.or(searchAccountName, searchAccountNum, searchAccountType));
            }
            Objects.requireNonNull(cq).orderBy(cb.desc(root.get("id")));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, PageRequest.of(page, size));
    }

    @Transactional
    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}

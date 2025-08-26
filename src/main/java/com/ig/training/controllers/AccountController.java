package com.ig.training.controllers;

import com.ig.training.aop.AuditFilter;
import com.ig.training.base.response.ObjectResponse;
import com.ig.training.base.response.PageResponse;
import com.ig.training.base.response.ResponseMessage;
import com.ig.training.dto.AccountDto;
import com.ig.training.model.Account;
import com.ig.training.services.AccountService;
import com.ig.training.utilities.constants.Constant;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/v1/api/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ObjectResponse<Account> getAccount(@Valid @RequestBody AccountDto accountDto) {
        return new ObjectResponse<>(accountService.createAccount(accountDto.toAccount()));
    }

    @PutMapping("/{id}")
    public ObjectResponse<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDto account) {
        return new ObjectResponse<>(accountService.updateAccount(id, account));
    }

    @GetMapping("/{id}")
    public ObjectResponse<AccountDto> getAccountById(@PathVariable Long id) {
        return new ObjectResponse<>(accountService.getAccountDetail(id).toAccountDto());
    }

    @AuditFilter()
    @GetMapping("/list")
    public PageResponse<AccountDto> getAccountListPage(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var listPage = accountService.getAccountList(query, page, size);
        var accountDtoList = listPage.getContent().stream().map(Account::toAccountDto).toList();
        return new PageResponse<>(accountDtoList, listPage.getTotalElements());
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseMessage();
    }
}

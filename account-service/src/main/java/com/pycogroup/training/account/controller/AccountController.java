package com.pycogroup.training.account.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pycogroup.training.account.entity.Account;
import com.pycogroup.training.account.mapper.AccountMapper;
import com.pycogroup.training.account.service.AccountService;

@RestController
@RequestMapping("accounts")
@Slf4j
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private AccountMapper accountMapper;

  @PostMapping
  public ResponseEntity<AccountResponse> add(final @RequestBody AccountRequest account) {
    return ResponseEntity.ok(
        accountMapper.toResponse(accountService.add(accountMapper.toAccount(account))));
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<AccountResponse>> bulkAdd(final @RequestBody List<AccountRequest> accounts) {
    final List<Account> savedAccounts = accountService.bulkAdd(
        accounts
            .stream()
            .map(account -> accountMapper.toAccount(account))
            .collect(Collectors.toList())
    );
    return ResponseEntity.ok(savedAccounts
        .stream()
        .map(account -> accountMapper.toResponse(account))
        .collect(Collectors.toList()));
  }

  @PutMapping
  public ResponseEntity<AccountResponse> update(final @RequestBody AccountRequest account) {
    return ResponseEntity.ok(
        accountMapper.toResponse(accountService.update(accountMapper.toAccount(account))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<AccountResponse> findById(final @PathVariable("id") String id) {
    return ResponseEntity.ok(accountMapper.toResponse(accountService.getById(id)));
  }

  @GetMapping
  public ResponseEntity<Page<AccountResponse>> findAll(final @PageableDefault Pageable pageable) {
    final Page<Account> accounts = accountService.findAll(pageable);
    log.info("Accounts found: {}", accounts);
    return ResponseEntity.ok(accounts.map(account -> accountMapper.toResponse(account)));
  }

  @PostMapping("/ids")
  public ResponseEntity<List<AccountResponse>> find(final @RequestBody List<String> ids) {
    final List<Account> accounts = accountService.find(ids);
    log.info("Accounts found: {}", accounts);
    return ResponseEntity.ok(accounts
        .stream()
        .map(account -> accountMapper.toResponse(account))
        .collect(Collectors.toList()));
  }

  @DeleteMapping("/{id}")
  public void delete(final @PathVariable("id") String id) {
    accountService.delete(id);
  }

}

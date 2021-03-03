package com.pycogroup.training.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pycogroup.training.account.mapper.AccountMapper;
import com.pycogroup.training.account.service.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private AccountMapper accountMapper;


  @GetMapping("/{id}")
  public ResponseEntity<AccountResponse> findById(final @PathVariable("id") String id) {
    return ResponseEntity.ok(accountMapper.toResponse(accountService.getById(id)));
  }

}

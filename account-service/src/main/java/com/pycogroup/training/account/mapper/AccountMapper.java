package com.pycogroup.training.account.mapper;

import org.mapstruct.Mapper;

import com.pycogroup.training.account.controller.AccountRequest;
import com.pycogroup.training.account.controller.AccountResponse;
import com.pycogroup.training.account.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  AccountResponse toResponse(final Account account);

  Account toAccount(final AccountRequest accountRequest);

}

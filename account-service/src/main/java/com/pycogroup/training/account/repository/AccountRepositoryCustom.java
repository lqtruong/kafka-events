package com.pycogroup.training.account.repository;

import java.util.List;

import com.pycogroup.training.account.entity.Account;

public interface AccountRepositoryCustom {

  List<Account> findAllByCustomerId(final String customerId);

}

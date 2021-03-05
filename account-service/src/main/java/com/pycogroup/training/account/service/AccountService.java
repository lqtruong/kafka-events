package com.pycogroup.training.account.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pycogroup.training.account.entity.Account;
import com.pycogroup.training.messaging.order.OrderMessage;

public interface AccountService {

  void process(final OrderMessage order);

  Account getById(final String id);

  List<Account> getAllByCustomerId(final String customerId);

  Account add(final Account account);

  Account update(final Account account);

  List<Account> find(final List<String> ids);

  void delete(final String id);

  List<Account> bulkAdd(final List<Account> accounts);

  Page<Account> findAll(final Pageable pageable);
}

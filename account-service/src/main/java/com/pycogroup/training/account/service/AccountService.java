package com.pycogroup.training.account.service;

import com.pycogroup.training.account.entity.Account;
import com.pycogroup.training.messaging.order.OrderMessage;

public interface AccountService {

  void process(final OrderMessage order);

  Account getById(final String id);
}

package com.pycogroup.training.account.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pycogroup.training.account.entity.Account;
import com.pycogroup.training.account.repository.AccountRepository;
import com.pycogroup.training.account.stream.source.OrderSource;
import com.pycogroup.training.messaging.order.OrderMessage;
import com.pycogroup.training.messaging.order.OrderStatus;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private OrderSource orderSource;

  @Override
  public void process(final OrderMessage order) {
    log.info("Order processed: {}", order);
    final String customerId = order.getCustomerId();
    final List<Account> accounts = accountRepository.findAllByCustomerId(customerId);
    if (CollectionUtils.isEmpty(accounts)) {
      log.warn("No account found for this customer {}", customerId);
      order.setStatus(OrderStatus.REJECTED);
    } else {
      final Account firstAccount = accounts.get(0);
      log.info("First Account found: {}", firstAccount);
      if (firstAccount.affordOk(order.getPrice())) {
        order.setStatus(OrderStatus.ACCEPTED);
        firstAccount.rebalance(order.getPrice());
      } else {
        order.setStatus(OrderStatus.REJECTED);
      }
      accountRepository.save(firstAccount);
    }
    orderSource.send(order);
    log.info("Order response sent: {}", order);
  }

  @Override
  public Account getById(final String id) {
    return accountRepository.findById(id).orElse(null);
  }

}

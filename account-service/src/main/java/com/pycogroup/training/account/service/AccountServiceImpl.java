package com.pycogroup.training.account.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pycogroup.training.account.entity.Account;
import com.pycogroup.training.account.repository.AccountRepository;
import com.pycogroup.training.account.stream.source.OrderSource;
import com.pycogroup.training.messaging.order.CheckedStatus;
import com.pycogroup.training.messaging.order.OrderMessage;

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
    final List<Account> accounts = getAllByCustomerId(customerId);
    if (CollectionUtils.isEmpty(accounts)) {
      log.warn("No account found for this customer {}", customerId);
      order.setBalanceCheck(
          CheckedStatus.from(CheckedStatus.Result.FAILED, "No account found for this customer"));
    } else {
      final Optional<Account> accountToCheck = accounts
          .stream()
          .filter(account -> StringUtils.equals(account.getId(), order.getAccountId()))
          .findFirst();
      if (!accountToCheck.isPresent()) {
        log.warn("No account matched {}", order.getAccountId());
        order.setBalanceCheck(CheckedStatus.from(CheckedStatus.Result.FAILED, "No account matched"));
      } else {
        final Account firstAccount = accountToCheck.get();
        log.info("First Account found: {}", firstAccount);
        if (firstAccount.affordOk(order.getPrice())) {
          order.setBalanceCheck(CheckedStatus.from(CheckedStatus.Result.SUCCESSFUL, "Enough credit"));
          firstAccount.rebalance(order.getPrice());
        } else {
          order.setBalanceCheck(CheckedStatus.from(CheckedStatus.Result.FAILED, "Not enough credit"));
        }
        accountRepository.save(firstAccount);
      }
    }
    orderSource.send(order);
    log.info("Order response sent: {}", order);
  }

  @Override
  public Account getById(final String id) {
    return accountRepository.findById(id).orElse(null);
  }

  @Override
  public List<Account> getAllByCustomerId(String customerId) {
    return accountRepository.findAllByCustomerId(customerId);
  }

  @Override
  @Transactional
  public Account add(final Account account) {
    return accountRepository.save(account);
  }

  @Override
  @Transactional
  public Account update(final Account account) {
    final Account foundAccount = getById(account.getId());
    if (Objects.isNull(foundAccount)) {
      log.info("Account {} not found. No update.", account.getId());
      return null;
    }
    foundAccount.setNumber(account.getNumber());
    foundAccount.setBalance(account.getBalance());
    foundAccount.setCustomerId(account.getCustomerId());
    return accountRepository.save(foundAccount);
  }

  @Override
  public List<Account> find(final List<String> ids) {
    return accountRepository.findByIdIn(ids);
  }

  @Override
  @Transactional
  public void delete(final String id) {
    accountRepository.deleteById(id);
  }

  @Override
  @Transactional
  public List<Account> bulkAdd(final List<Account> accounts) {
    return accountRepository.saveAll(accounts);
  }

  @Override
  public Page<Account> findAll(final Pageable pageable) {
    return accountRepository.findAll(pageable);
  }

}

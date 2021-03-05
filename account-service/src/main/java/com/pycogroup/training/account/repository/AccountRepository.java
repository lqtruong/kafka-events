package com.pycogroup.training.account.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pycogroup.training.account.entity.Account;

public interface AccountRepository extends MongoRepository<Account, String>, AccountRepositoryCustom {

  List<Account> findByIdIn(final List<String> ids);

}

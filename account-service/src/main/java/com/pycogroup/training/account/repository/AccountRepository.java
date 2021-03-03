package com.pycogroup.training.account.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pycogroup.training.account.entity.Account;

public interface AccountRepository extends MongoRepository<Account, String>, AccountRepositoryCustom {
}

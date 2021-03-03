package com.pycogroup.training.account.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.pycogroup.training.account.entity.Account;

@Repository
public class AccountRepositoryCustomImpl implements AccountRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public List<Account> findAllByCustomerId(final String customerId) {
    final Criteria criteria = Criteria.where("customerId").is(customerId);
    return mongoTemplate.find(Query.query(criteria), Account.class);
  }

}

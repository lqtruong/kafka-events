package com.pycogroup.training.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pycogroup.training.customer.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>, CustomerRepositoryCustom {

  List<Customer> findByIdIn(final List<String> ids);

  Optional<Customer> findByIdAndDeletedFalse(final String id);
}

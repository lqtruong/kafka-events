package com.pycogroup.training.customer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pycogroup.training.customer.entity.Customer;

public interface CustomerService {

  Customer getById(final String id);

  Customer add(final Customer customer);

  Customer update(final Customer customer);

  void delete(final String id);

  List<Customer> bulkAdd(final List<Customer> customers);

  Page<Customer> getAll(final Pageable pageable);
}

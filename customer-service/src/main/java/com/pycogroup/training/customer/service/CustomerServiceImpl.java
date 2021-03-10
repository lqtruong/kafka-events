package com.pycogroup.training.customer.service;

import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pycogroup.training.customer.entity.Customer;
import com.pycogroup.training.customer.repository.CustomerRepository;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Customer getById(final String id) {
    return customerRepository.findByIdAndDeletedFalse(id).orElse(null);
  }

  @Override
  @Transactional
  public Customer add(final Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  @Transactional
  public Customer update(final Customer customer) {
    final Customer foundCustomer = getById(customer.getId());
    if (Objects.isNull(foundCustomer)) {
      log.info("Customer {} not found. No update.", customer.getId());
      return null;
    }
    foundCustomer.setName(customer.getName());
    return customerRepository.save(foundCustomer);
  }

  @Override
  @Transactional
  public void delete(final String id) {
    customerRepository.deleteById(id);
  }

  @Override
  @Transactional
  public List<Customer> bulkAdd(final List<Customer> customers) {
    return customerRepository.saveAll(customers);
  }

  @Override
  public Page<Customer> getAll(final Pageable pageable) {
    return customerRepository.findAll(pageable);
  }

}

package com.pycogroup.training.customer.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pycogroup.training.customer.entity.Customer;
import com.pycogroup.training.customer.mapper.CustomerMapper;
import com.pycogroup.training.customer.service.CustomerService;

@RestController
@RequestMapping("customers")
@Slf4j
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private CustomerMapper customerMapper;

  @PostMapping
  public ResponseEntity<CustomerResponse> add(final @RequestBody CustomerRequest customer) {
    return ResponseEntity.ok(
        customerMapper.toResponse(customerService.add(customerMapper.toCustomer(customer))));
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<CustomerResponse>> bulkAdd(final @RequestBody List<CustomerRequest> customers) {
    final List<Customer> savedCustomers = customerService.bulkAdd(
        customers
            .stream()
            .map(customer -> customerMapper.toCustomer(customer))
            .collect(Collectors.toList())
    );
    return ResponseEntity.ok(savedCustomers
        .stream()
        .map(customer -> customerMapper.toResponse(customer))
        .collect(Collectors.toList()));
  }

  @PutMapping
  public ResponseEntity<CustomerResponse> update(final @RequestBody CustomerRequest customer) {
    return ResponseEntity.ok(
        customerMapper.toResponse(customerService.update(customerMapper.toCustomer(customer))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> getById(final @PathVariable("id") String id) {
    return ResponseEntity.ok(customerMapper.toResponse(customerService.getById(id)));
  }

  @GetMapping
  public ResponseEntity<Page<CustomerResponse>> getAll(final @PageableDefault Pageable pageable) {
    final Page<Customer> customers = customerService.getAll(pageable);
    log.info("Customers found: {}", customers);
    return ResponseEntity.ok(customers.map(customer -> customerMapper.toResponse(customer)));
  }

  @DeleteMapping("/{id}")
  public void delete(final @PathVariable("id") String id) {
    customerService.delete(id);
  }

  @GetMapping("/verify/{id}")
  public ResponseEntity<CustomerResponseDetail> verify(final @PathVariable("id") String id) {
    return ResponseEntity.ok(customerMapper.toResponseDetail(customerService.getById(id)));
  }

}

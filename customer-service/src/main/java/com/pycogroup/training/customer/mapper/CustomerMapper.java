package com.pycogroup.training.customer.mapper;

import org.mapstruct.Mapper;

import com.pycogroup.training.customer.controller.CustomerRequest;
import com.pycogroup.training.customer.controller.CustomerResponse;
import com.pycogroup.training.customer.controller.CustomerResponseDetail;
import com.pycogroup.training.customer.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  CustomerResponse toResponse(final Customer customer);

  Customer toCustomer(final CustomerRequest customerRequest);

  CustomerResponseDetail toResponseDetail(final Customer customer);
}

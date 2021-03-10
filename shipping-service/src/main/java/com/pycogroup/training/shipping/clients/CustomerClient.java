package com.pycogroup.training.shipping.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pycogroup.training.shipping.entity.CustomerDetail;

@FeignClient(value = "customerClient", url = "${rest.clients.customer}")
public interface CustomerClient {

  @GetMapping("/verify/{id}")
  CustomerDetail verifyCustomer(final @PathVariable("id") String customerId);

}

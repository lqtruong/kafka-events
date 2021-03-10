package com.pycogroup.training.shipping.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pycogroup.training.shipping.entity.CustomerDetail;
import com.pycogroup.training.shipping.entity.OrderDetail;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingResponseDetail {

  private String id;
  private List<OrderResponseDetail> orders;
  private CustomerResponseDetail customer;

}

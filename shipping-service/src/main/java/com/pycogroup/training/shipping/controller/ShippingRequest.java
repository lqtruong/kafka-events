package com.pycogroup.training.shipping.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShippingRequest {

  private String id;
  private String customerId;
  private List<String> orderIds;
  private List<ShippingMessage> messages;

}

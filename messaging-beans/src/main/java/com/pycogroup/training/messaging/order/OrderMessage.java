package com.pycogroup.training.messaging.order;

import java.util.List;

import lombok.Data;

@Data
public class OrderMessage {

  private String id;

  private String status;
  private double price;
  private String customerId;
  private String accountId;
  private List<String> productIds;

}
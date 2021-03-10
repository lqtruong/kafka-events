package com.pycogroup.training.shipping.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("shippings_details")
@Getter
@Setter
@ToString
public class ShippingDetail {

  private String id;
  private List<OrderDetail> orders;
  private CustomerDetail customer;

}

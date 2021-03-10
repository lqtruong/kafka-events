package com.pycogroup.training.shipping.entity;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetail {

  private String id;

  private double price;
  private List<ProductDetail> products;


}

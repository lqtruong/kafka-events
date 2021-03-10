package com.pycogroup.training.shipping.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShippingUpdateRequest {

  private String id;
  private String status;
  private String message;

}

package com.pycogroup.training.shipping.clients;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderVerificationRequest {

  private List<String> orderIds;

  public static OrderVerificationRequest of(final List<String> orderIds) {
    final OrderVerificationRequest orders = new OrderVerificationRequest();
    orders.setOrderIds(orderIds);
    return orders;
  }

}

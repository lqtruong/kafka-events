package com.pycogroup.training.order.service;

import com.pycogroup.training.order.entity.Order;
import com.pycogroup.training.order.entity.OrderVerification;

public interface OrderService {

  void process(final OrderVerification orderVerification);

  Order create(final Order order);

  Order getById(final String id);

  OrderVerification createOrderVerification(final OrderVerification orderVerification);
}

package com.pycogroup.training.order.service;

import com.pycogroup.training.order.entity.Order;

public interface OrderService {

  void process(final Order order);

  Order create(final Order order);

  Order getById(final String id);
}

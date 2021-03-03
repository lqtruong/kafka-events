package com.pycogroup.training.product.service;

import com.pycogroup.training.messaging.order.OrderMessage;
import com.pycogroup.training.product.entity.Product;

public interface ProductService {

  void process(final OrderMessage order);

  Product order(final Product product);

  Product getById(final String id);
}

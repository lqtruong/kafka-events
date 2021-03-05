package com.pycogroup.training.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pycogroup.training.messaging.order.OrderMessage;
import com.pycogroup.training.product.entity.Product;

public interface ProductService {

  void process(final OrderMessage order);

  Product order(final Product product);

  Product getById(final String id);

  Product add(final Product product);

  Product update(final Product product);

  List<Product> find(final List<String> ids);

  void delete(final String id);

  List<Product> bulkAdd(final List<Product> products);

  Page<Product> findAll(final Pageable pageable);
}

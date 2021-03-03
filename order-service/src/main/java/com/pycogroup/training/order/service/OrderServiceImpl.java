package com.pycogroup.training.order.service;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pycogroup.training.order.entity.Order;
import com.pycogroup.training.order.repository.OrderRepository;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository repository;

  @Override
  public void process(final Order order) {
    log.info("Order processing: {}", order);
    final Optional<Order> processingOrder = repository.findById(order.getId());
    if (!processingOrder.isPresent()) {
      log.info("Order {} not found", order.getId());
      return;
    }
    final Order foundOrder = processingOrder.get();
    if (!foundOrder.getStatus().isRejected()) {
      foundOrder.setStatus(order.getStatus());
      repository.save(foundOrder);
      log.info("Order status updated: {}", order);
    }
  }

  @Override
  public Order create(final Order order) {
    return repository.save(order);
  }

  @Override
  public Order getById(final String id) {
    return repository.findById(id).orElse(null);
  }

}

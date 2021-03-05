package com.pycogroup.training.order.service;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pycogroup.training.order.entity.Order;
import com.pycogroup.training.order.entity.OrderVerification;
import com.pycogroup.training.order.repository.OrderRepository;
import com.pycogroup.training.order.repository.OrderVerificationRepository;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private OrderVerificationRepository verificationRepository;

  @Override
  public void process(final OrderVerification orderVerification) {
    log.info("Order Verification: {}", orderVerification);
    final Optional<Order> processingOrder = repository.findById(orderVerification.getId());
    if (!processingOrder.isPresent()) {
      log.info("Order {} not found", orderVerification.getId());
      return;
    }
    final Order foundOrder = processingOrder.get();
    final Optional<OrderVerification> verification = verificationRepository.findById(foundOrder.getId());
    OrderVerification newVerification = null;
    if (!verification.isPresent()) {
      newVerification = OrderVerification.from(foundOrder);
    } else {
      newVerification = verification.get();
    }
    if (!orderVerification.getBalanceCheck().isNotVerified()) {
      newVerification.setBalanceCheck(orderVerification.getBalanceCheck());
    }
    if (!orderVerification.getProductCheck().isNotVerified()) {
      newVerification.setProductCheck(orderVerification.getProductCheck());
    }
    final OrderVerification savedOrderVerification = createOrderVerification(newVerification);
    log.info("New Order Verification status updated: {}", savedOrderVerification);
    foundOrder.setStatus(savedOrderVerification.getStatus());
    repository.save(foundOrder);
    log.info("Order status updated: {}", foundOrder);
  }

  @Override
  public Order create(final Order order) {
    return repository.save(order);
  }

  @Override
  public Order getById(final String id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public OrderVerification createOrderVerification(final OrderVerification orderVerification) {
    return verificationRepository.save(orderVerification);
  }

}

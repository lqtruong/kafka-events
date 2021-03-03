package com.pycogroup.training.order.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pycogroup.training.order.entity.Order;
import com.pycogroup.training.order.event.OrderEvent;
import com.pycogroup.training.order.mapper.OrderMapper;
import com.pycogroup.training.order.service.OrderService;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private OrderEvent orderEvent;

  @Autowired
  private OrderMapper orderMapper;

  @PostMapping
  public ResponseEntity<OrderResponse> process(final @RequestBody OrderRequest order) {
    final Order savedOrder = orderService.create(orderMapper.toOrder(order));
    log.info("Order saved: {}", savedOrder);
    final boolean isSent = orderEvent.send(orderMapper.toMessage(savedOrder));
    log.info("Order sent: {}", isSent);
    return new ResponseEntity(orderMapper.toResponse(savedOrder), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> findById(final @PathVariable("id") String id) {
    return ResponseEntity.ok(orderMapper.toResponse(orderService.getById(id)));
  }
}

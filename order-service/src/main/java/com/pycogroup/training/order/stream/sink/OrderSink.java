package com.pycogroup.training.order.stream.sink;

import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.pycogroup.training.messaging.order.OrderMessage;
import com.pycogroup.training.order.mapper.OrderMapper;
import com.pycogroup.training.order.service.OrderService;

@Service
@Slf4j
public class OrderSink {

  @Autowired
  private OrderService orderService;

  @Autowired
  private OrderMapper orderMapper;

  @Bean
  public Consumer<OrderMessage> receive() {
    return (order) -> orderService.process(orderMapper.fromMessage(order));
  }

}

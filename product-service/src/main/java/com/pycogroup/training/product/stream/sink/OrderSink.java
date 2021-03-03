package com.pycogroup.training.product.stream.sink;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

import com.pycogroup.training.messaging.order.OrderMessage;
import com.pycogroup.training.product.service.ProductService;

@Component
@Slf4j
public class OrderSink {

  @Autowired
  private ProductService productService;

  /*@Bean
  public Consumer<OrderMessage> receive() {
    return (order) -> productService.process(order);
  }*/

  @StreamListener(Processor.INPUT)
  public void receive(final OrderMessage order) {
    productService.process(order);
  }

}

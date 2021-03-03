package com.pycogroup.training.account.stream.sink;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

import com.pycogroup.training.account.service.AccountService;
import com.pycogroup.training.messaging.order.OrderMessage;

@Component
@Slf4j
public class OrderSink {

  @Autowired
  private AccountService accountService;

  /*@Bean
  public Consumer<OrderMessage> receive() {
    return (order) -> accountService.process(order);
  }*/

  @StreamListener(Processor.INPUT)
  public void receive(final OrderMessage order) {
    accountService.process(order);
  }

}

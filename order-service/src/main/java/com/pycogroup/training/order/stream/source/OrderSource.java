package com.pycogroup.training.order.stream.source;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pycogroup.training.order.mapper.OrderMapper;
import com.pycogroup.training.order.service.OrderService;

@Service
@Slf4j
public class OrderSource {

  @Autowired
  private OrderService orderService;

  @Autowired
  private OrderMapper orderMapper;


}

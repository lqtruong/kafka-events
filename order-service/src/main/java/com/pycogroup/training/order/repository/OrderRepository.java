package com.pycogroup.training.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pycogroup.training.order.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom {
}

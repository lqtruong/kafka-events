package com.pycogroup.training.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pycogroup.training.order.entity.OrderVerification;

public interface OrderVerificationRepository extends MongoRepository<OrderVerification, String>, OrderRepositoryCustom {
}

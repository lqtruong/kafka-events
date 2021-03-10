package com.pycogroup.training.shipping.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pycogroup.training.shipping.entity.Shipping;

public interface ShippingRepository extends MongoRepository<Shipping, String>, ShippingRepositoryCustom {

  Optional<Shipping> findByIdAndDeletedFalse(final String id);

}

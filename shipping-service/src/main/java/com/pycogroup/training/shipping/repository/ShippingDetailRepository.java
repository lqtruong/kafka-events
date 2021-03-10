package com.pycogroup.training.shipping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pycogroup.training.shipping.entity.ShippingDetail;

public interface ShippingDetailRepository extends MongoRepository<ShippingDetail, String>,
    ShippingDetailRepositoryCustom {

  @Query("{'customer.id': ?0}")
  Page<ShippingDetail> findByCustomerId(final String customerId, final Pageable pageable);

}

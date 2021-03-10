package com.pycogroup.training.shipping.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pycogroup.training.shipping.entity.Shipping;
import com.pycogroup.training.shipping.entity.ShippingDetail;

public interface ShippingService {

  ShippingDetail ship(final Shipping shipping);

  Shipping update(final String id, final String status, final String message);

  Shipping getById(final String id);

  void delete(final String id);

  Page<ShippingDetail> getByCustomerId(final String customerId, final Pageable pageable);
}

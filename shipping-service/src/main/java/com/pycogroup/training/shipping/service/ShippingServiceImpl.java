package com.pycogroup.training.shipping.service;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pycogroup.training.shipping.clients.CustomerClient;
import com.pycogroup.training.shipping.clients.OrderClient;
import com.pycogroup.training.shipping.clients.OrderVerificationRequest;
import com.pycogroup.training.shipping.entity.NoteDetail;
import com.pycogroup.training.shipping.entity.Shipping;
import com.pycogroup.training.shipping.entity.ShippingDetail;
import com.pycogroup.training.shipping.entity.ShippingStatus;
import com.pycogroup.training.shipping.repository.ShippingDetailRepository;
import com.pycogroup.training.shipping.repository.ShippingRepository;

@Service
@Slf4j
public class ShippingServiceImpl implements ShippingService {

  @Autowired
  private ShippingRepository shippingRepository;

  @Autowired
  private ShippingDetailRepository shippingDetailRepository;

  @Autowired
  private OrderClient orderClient;

  @Autowired
  private CustomerClient customerClient;

  @Override
  @Transactional
  public ShippingDetail ship(final Shipping shipping) {
    log.info("Create a shipping request {}", shipping);
    final Shipping savedShipping = shippingRepository.save(shipping);
    log.info("Shipping saved {}", savedShipping);
    final ShippingDetail shippingDetail = new ShippingDetail();
    // verify all orders accepted, having the same customer, returned order detail with products
    shippingDetail.setOrders(orderClient.verifyOrders(
        OrderVerificationRequest.of(savedShipping.getOrderIds())));

    // verify the customer and return customer detail
    shippingDetail.setCustomer(customerClient.verifyCustomer(savedShipping.getCustomerId()));
    // save shipping with processing status and shipping detail & return shipping detail
    return shippingDetailRepository.save(shippingDetail);
  }

  @Override
  @Transactional
  public Shipping update(final String id, final String status, final String message) {
    final Shipping shipping = getById(id);
    if (Objects.isNull(shipping)) {
      log.error("No shipping found {}", id);
    }
    shipping.setStatus(ShippingStatus.valueOf(StringUtils.upperCase(status)));
    shipping.getMessages().add(NoteDetail.of(message));
    return shippingRepository.save(shipping);
  }

  @Override
  public Shipping getById(final String id) {
    return shippingRepository.findByIdAndDeletedFalse(id).orElse(null);
  }

  @Override
  public void delete(final String id) {
    final Shipping shipping = getById(id);
    if (Objects.isNull(shipping)) {
      log.info("Shipping {} not found", id);
      return;
    }
    shipping.setDeleted(true);
    shippingRepository.save(shipping);

  }

  @Override
  public Page<ShippingDetail> getByCustomerId(final String customerId, final Pageable pageable) {
    return shippingDetailRepository.findByCustomerId(customerId, pageable);
  }

}

package com.pycogroup.training.shipping.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pycogroup.training.shipping.entity.ShippingDetail;
import com.pycogroup.training.shipping.mapper.ShippingMapper;
import com.pycogroup.training.shipping.service.ShippingService;

@RestController
@RequestMapping("shippings")
@Slf4j
public class ShippingController {

  @Autowired
  private ShippingService shippingService;

  @Autowired
  private ShippingMapper shippingMapper;


  @PostMapping
  public ResponseEntity<ShippingResponseDetail> ship(final @RequestBody ShippingRequest shipping) {
    return ResponseEntity.ok(
        shippingMapper.toResponseDetail(shippingService.ship(shippingMapper.toProduct(shipping))));
  }

  @PutMapping
  public ResponseEntity<ShippingResponse> update(final @RequestBody ShippingUpdateRequest updateRequest) {
    return ResponseEntity.ok(shippingMapper.toResponse(
        shippingService.update(updateRequest.getId(), updateRequest.getStatus(), updateRequest.getMessage())));
  }

  @GetMapping
  public ResponseEntity<Page<ShippingResponseDetail>> getAllShippings(
      final @RequestParam("customerId") String customerId, final @PageableDefault Pageable pageable) {
    final Page<ShippingDetail> shippingDetails = shippingService.getByCustomerId(customerId, pageable);
    log.info("Shipping Details found: {}", shippingDetails);
    return ResponseEntity.ok(shippingDetails.map(shippingDetail -> shippingMapper.toResponseDetail(shippingDetail)));
  }

  @DeleteMapping("/{id}")
  public void delete(final @PathVariable("id") String id) {
    shippingService.delete(id);
  }

}

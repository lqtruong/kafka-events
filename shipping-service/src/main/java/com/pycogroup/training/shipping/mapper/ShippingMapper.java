package com.pycogroup.training.shipping.mapper;

import org.mapstruct.Mapper;

import com.pycogroup.training.shipping.controller.ShippingRequest;
import com.pycogroup.training.shipping.controller.ShippingResponse;
import com.pycogroup.training.shipping.controller.ShippingResponseDetail;
import com.pycogroup.training.shipping.entity.Shipping;
import com.pycogroup.training.shipping.entity.ShippingDetail;

@Mapper(componentModel = "spring")
public interface ShippingMapper {

  Shipping toProduct(final ShippingRequest shippingRequest);

  ShippingResponseDetail toResponseDetail(final ShippingDetail shippingDetail);

  ShippingResponse toResponse(final Shipping shipping);

}

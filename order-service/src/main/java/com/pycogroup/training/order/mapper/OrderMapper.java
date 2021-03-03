package com.pycogroup.training.order.mapper;

import org.mapstruct.Mapper;

import com.pycogroup.training.messaging.order.OrderMessage;
import com.pycogroup.training.order.controller.OrderRequest;
import com.pycogroup.training.order.controller.OrderResponse;
import com.pycogroup.training.order.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderResponse toResponse(final Order order);

  OrderMessage toMessage(final Order order);

  Order toOrder(final OrderRequest orderRequest);

  Order fromMessage(final OrderMessage orderMessage);

}

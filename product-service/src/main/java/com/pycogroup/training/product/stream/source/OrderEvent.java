package com.pycogroup.training.product.stream.source;

import com.pycogroup.training.messaging.order.OrderMessage;

public interface OrderEvent {

  boolean send(final OrderMessage orderMessage);

}

package com.pycogroup.training.order.event;

import com.pycogroup.training.messaging.order.OrderMessage;

public interface OrderEvent {

  boolean send(final OrderMessage orderMessage);

}

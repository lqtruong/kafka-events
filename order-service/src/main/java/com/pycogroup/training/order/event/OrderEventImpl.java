package com.pycogroup.training.order.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.pycogroup.training.messaging.order.OrderMessage;

@Component
public class OrderEventImpl implements OrderEvent {

  @Autowired
  private Source source;

  @Override
  public boolean send(final OrderMessage orderMessage) {
    return this.source.output().send(MessageBuilder
        .withPayload(orderMessage)
        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
        .build());
  }

}

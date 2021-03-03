package com.pycogroup.training.messaging.order;

public enum OrderStatus {

  NEW, PROCESSING, ACCEPTED, DONE, REJECTED;

  public boolean isRejected() {
    return this == REJECTED;
  }

}

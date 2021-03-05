package com.pycogroup.training.order.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pycogroup.training.messaging.date.DatePatterns;

@Document("orders_verification")
@Getter
@Setter
@ToString
public class OrderVerification {

  @Id
  @Field(targetType = FieldType.OBJECT_ID)
  private String id;

  @CreatedDate
  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime created;

  @LastModifiedDate
  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime lastModified;

  private CheckedStatus balanceCheck = CheckedStatus.newInstance();
  private CheckedStatus productCheck = CheckedStatus.newInstance();

  public static OrderVerification from(final Order savedOrder) {
    final OrderVerification verification = new OrderVerification();
    verification.setId(savedOrder.getId());
    return verification;
  }

  public OrderStatus getStatus() {
    if (balanceCheck.isRejected()
        || productCheck.isRejected()) {
      return OrderStatus.REJECTED;
    }
    return OrderStatus.ACCEPTED;
  }

}

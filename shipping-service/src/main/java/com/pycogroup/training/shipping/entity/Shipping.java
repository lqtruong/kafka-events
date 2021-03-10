package com.pycogroup.training.shipping.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

@Document("shippings")
@Getter
@Setter
@ToString
public class Shipping {

  @Id
  @Field(targetType = FieldType.OBJECT_ID)
  private String id;
  private String customerId;
  private List<String> orderIds;
  private ShippingStatus status = ShippingStatus.PROCESSING;
  private List<NoteDetail> messages = new ArrayList<>();
  private boolean deleted = false;

  @CreatedDate
  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime created;

  @LastModifiedDate
  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime lastModified;

}

package com.pycogroup.training.product.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pycogroup.training.messaging.date.DatePatterns;

@Document("products")
@Getter
@Setter
@ToString
public class Product {

  @Id
  @Field(targetType = FieldType.OBJECT_ID)
  private String id;
  private String name;
  private double price;
  private long count;

  @CreatedDate
  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime created;

  @LastModifiedDate
  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime lastModified;

  public boolean stillInStock() {
    return this.count > 0;
  }

  public Product ordered() {
    this.setCount(this.getCount() - 1);
    return this;
  }

  public boolean validId() {
    return !StringUtils.isBlank(this.id);
  }

}

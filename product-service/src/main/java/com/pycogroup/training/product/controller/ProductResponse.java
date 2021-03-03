package com.pycogroup.training.product.controller;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pycogroup.training.messaging.date.DatePatterns;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

  private String id;
  private String name;
  private double price;
  private long count;

  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime created;

  @JsonFormat(pattern = DatePatterns.DATE_TIME_PATTERN)
  private LocalDateTime lastModified;

}

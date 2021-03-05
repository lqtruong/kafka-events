package com.pycogroup.training.product.controller;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.web.PageableDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSearch {

  private PageableDefault pageable;

}

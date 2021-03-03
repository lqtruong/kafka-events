package com.pycogroup.training.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pycogroup.training.product.mapper.ProductMapper;
import com.pycogroup.training.product.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductMapper productMapper;


  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> findById(final @PathVariable("id") String id) {
    return ResponseEntity.ok(productMapper.toResponse(productService.getById(id)));
  }

}

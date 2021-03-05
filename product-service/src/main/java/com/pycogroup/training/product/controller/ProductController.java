package com.pycogroup.training.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pycogroup.training.product.entity.Product;
import com.pycogroup.training.product.mapper.ProductMapper;
import com.pycogroup.training.product.service.ProductService;

@RestController
@RequestMapping("products")
@Slf4j
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductMapper productMapper;

  @PostMapping
  public ResponseEntity<ProductResponse> add(final @RequestBody ProductRequest product) {
    return ResponseEntity.ok(
        productMapper.toResponse(productService.add(productMapper.toProduct(product))));
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<ProductResponse>> bulkAdd(final @RequestBody List<ProductRequest> products) {
    final List<Product> savedProducts = productService.bulkAdd(
        products
            .stream()
            .map(product -> productMapper.toProduct(product))
            .collect(Collectors.toList())
    );
    return ResponseEntity.ok(savedProducts
        .stream()
        .map(product -> productMapper.toResponse(product))
        .collect(Collectors.toList()));
  }

  @PutMapping
  public ResponseEntity<ProductResponse> update(final @RequestBody ProductRequest product) {
    return ResponseEntity.ok(
        productMapper.toResponse(productService.update(productMapper.toProduct(product))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> findById(final @PathVariable("id") String id) {
    return ResponseEntity.ok(productMapper.toResponse(productService.getById(id)));
  }

  @GetMapping
  public ResponseEntity<Page<ProductResponse>> findAll(final @PageableDefault Pageable pageable) {
    final Page<Product> products = productService.findAll(pageable);
    log.info("Products found: {}", products);
    return ResponseEntity.ok(products.map(product -> productMapper.toResponse(product)));
  }

  @PostMapping("/ids")
  public ResponseEntity<List<ProductResponse>> find(final @RequestBody List<String> ids) {
    final List<Product> products = productService.find(ids);
    log.info("Products found: {}", products);
    return ResponseEntity.ok(products
        .stream()
        .map(product -> productMapper.toResponse(product))
        .collect(Collectors.toList()));
  }

  @DeleteMapping("/{id}")
  public void delete(final @PathVariable("id") String id) {
    productService.delete(id);
  }

}

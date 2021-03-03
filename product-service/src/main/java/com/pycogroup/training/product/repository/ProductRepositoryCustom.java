package com.pycogroup.training.product.repository;

import java.util.List;

import com.pycogroup.training.product.entity.Product;

public interface ProductRepositoryCustom {

  List<Product> findAllInIds(final List<String> productIds);

}

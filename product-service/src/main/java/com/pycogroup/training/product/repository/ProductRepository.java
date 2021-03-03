package com.pycogroup.training.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pycogroup.training.product.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String>, ProductRepositoryCustom {
}

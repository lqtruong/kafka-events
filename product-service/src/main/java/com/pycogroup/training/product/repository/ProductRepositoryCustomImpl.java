package com.pycogroup.training.product.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.pycogroup.training.product.entity.Product;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public List<Product> findAllInIds(final List<String> productIds) {
    final Criteria criteria = Criteria.where("id").in(productIds);
    return mongoTemplate.find(Query.query(criteria), Product.class);
  }

}

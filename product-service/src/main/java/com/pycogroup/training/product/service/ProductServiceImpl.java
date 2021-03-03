package com.pycogroup.training.product.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pycogroup.training.messaging.order.OrderMessage;
import com.pycogroup.training.messaging.order.OrderStatus;
import com.pycogroup.training.product.entity.Product;
import com.pycogroup.training.product.repository.ProductRepository;
import com.pycogroup.training.product.stream.source.OrderSource;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderSource orderSource;

  @Override
  @Transactional
  public void process(final OrderMessage order) {
    log.info("Order processed: {}", order);
    final List<Product> products = productRepository.findAllInIds(order.getProductIds());
    if (CollectionUtils.isEmpty(products)) {
      log.info("No products found with ids {}", order.getProductIds());
      order.setStatus(OrderStatus.REJECTED);
    } else {
      if (!products.stream().allMatch(p -> p.stillInStock())) {
        order.setStatus(OrderStatus.REJECTED);
      } else {
        products.stream().forEach(this::order);
      }
    }
    if (!order.getStatus().isRejected()) {
      order.setStatus(OrderStatus.ACCEPTED);
    }
    log.info("Order response sent: {}", order);
    orderSource.send(order);
  }

  @Override
  public Product order(final Product product) {
    if (!product.validId()) {
      throw new IllegalArgumentException("Product Id should not be blank or null");
    }
    product.ordered();
    log.info("Product updated: {}", product);
    return productRepository.save(product);
  }

  @Override
  public Product getById(final String id) {
    return productRepository.findById(id).orElse(null);
  }

}

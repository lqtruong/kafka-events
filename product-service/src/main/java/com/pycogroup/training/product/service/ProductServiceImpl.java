package com.pycogroup.training.product.service;

import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pycogroup.training.messaging.order.CheckedStatus;
import com.pycogroup.training.messaging.order.OrderMessage;
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
      order.setProductCheck(
          CheckedStatus.from(CheckedStatus.Result.FAILED, "No products found"));
    } else {
      if (!products.stream().allMatch(p -> p.stillInStock())) {
        order.setProductCheck(
            CheckedStatus.from(CheckedStatus.Result.FAILED, "Not all products in stock"));
      } else {
        products.stream().forEach(this::order);
      }
    }
    if (!order.getProductCheck().getStatus().isFailed()) {
      order.setProductCheck(
          CheckedStatus.from(CheckedStatus.Result.SUCCESSFUL, "All products are available in stock"));
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

  @Override
  public Product add(final Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(final Product product) {
    final Product foundProduct = getById(product.getId());
    if (Objects.isNull(foundProduct)) {
      log.info("Product {} not found. No update.", product.getId());
      return null;
    }
    foundProduct.setCount(product.getCount());
    foundProduct.setName(product.getName());
    foundProduct.setPrice(product.getPrice());
    return productRepository.save(foundProduct);
  }

  @Override
  public List<Product> find(final List<String> ids) {
    return productRepository.findAllInIds(ids);
  }

  @Override
  public void delete(final String id) {
    productRepository.deleteById(id);
  }

  @Override
  public List<Product> bulkAdd(final List<Product> products) {
    return productRepository.saveAll(products);
  }

  @Override
  public Page<Product> findAll(final Pageable pageable) {
    return productRepository.findAll(pageable);
  }

}

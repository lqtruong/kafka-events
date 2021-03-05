package com.pycogroup.training.product.mapper;

import org.mapstruct.Mapper;

import com.pycogroup.training.product.controller.ProductRequest;
import com.pycogroup.training.product.controller.ProductResponse;
import com.pycogroup.training.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  Product toProduct(final ProductRequest productRequest);

  ProductResponse toResponse(final Product product);

}

package com.pycogroup.training.shipping.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pycogroup.training.shipping.entity.OrderDetail;

@FeignClient(value = "orderClient", url = "${rest.clients.order}")
public interface OrderClient {


  @PostMapping("/verify")
  List<OrderDetail> verifyOrders(final @RequestBody OrderVerificationRequest orderVerificationRequest);

}

package com.pycogroup.training.customer.controller;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseDetail {

  private String id;
  private String firstName;
  private String lastName;
  private String fullName;
  private String telephone;
  private String secondTelephone;
  private String otherTelephone;
  private String email;
  private String gender;
  private String address;
  private String shippingAddress;
  private String shippingNotes;

}

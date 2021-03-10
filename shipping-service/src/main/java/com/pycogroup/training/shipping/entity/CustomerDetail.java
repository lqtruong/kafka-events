package com.pycogroup.training.shipping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDetail {

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

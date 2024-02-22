package com.tpjad.ejb.dtos;

import com.tpjad.ejb.entities.Product;

import java.io.Serializable;

public class ProductDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id = 1L;
  private String product = "";
  private String username = "";

  public ProductDTO(Product product) {
    this.id = product.getId();
    this.product = product.getProduct();
    this.username = product.getUser().getName();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}

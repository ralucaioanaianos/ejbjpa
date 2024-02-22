package com.tpjad.ejb.entities;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends Base implements Serializable {
  private String product = "";
  @ManyToOne
  private User user;

  public String getProduct() {
    return this.product;
  }

  public void setProduct(String newProduct) {
    if (newProduct == null || newProduct.isEmpty()) {
      return;
    }
    this.product = newProduct;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "{id: " + getId() + " , product: " + getProduct() + " , userId: " + getUser().getId() + "}";
  }
}

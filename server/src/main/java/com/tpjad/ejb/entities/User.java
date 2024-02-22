package com.tpjad.ejb.entities;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends Base implements Serializable {
  private String name = "";
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Product> products;

  public String getName() {
    return name;
  }

  public void setName(String userName) {
    this.name = userName;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}

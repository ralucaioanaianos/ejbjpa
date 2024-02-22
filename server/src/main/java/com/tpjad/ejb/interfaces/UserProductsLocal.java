package com.tpjad.ejb.interfaces;

import com.tpjad.ejb.entities.Product;
import com.tpjad.ejb.entities.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserProductsLocal {
  User addProductForUser(String product, String userName);

  List<User> getAllUsers();

  List<Product> getAllProducts();

  List<Product> getAllProductsForUser(User user);
}

package com.tpjad.ejb.beans;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import com.tpjad.ejb.dtos.ProductDTO;
import com.tpjad.ejb.dtos.UserDTO;
import com.tpjad.ejb.entities.Product;
import com.tpjad.ejb.entities.User;
import com.tpjad.ejb.interfaces.UserProductsLocal;
import com.tpjad.ejb.interfaces.UserProductsRemote;

@Stateless
public class UserProductsBean implements UserProductsLocal, UserProductsRemote {
  @PersistenceContext(unitName = "tpjad_user_products")
  private EntityManager manager;

  public User addProductForUser(String productText, String username) {
    if (productText == null || productText.isEmpty()) {
      return null;
    }

    Product product = new Product();
    product.setProduct(productText);

    User dbUser = getUserForUsername(username);
    product.setUser(dbUser);
    manager.persist(product);
    return dbUser;
  }

  public void addProductForUserR(String product, String userName) {
    this.addProductForUser(product, userName);
  }

  public List<User> getAllUsers() {
    TypedQuery<User> query = manager.createQuery("select u from User u ", User.class);
    return query.getResultList();
  }

  public List<Product> getAllProducts() {
    TypedQuery<Product> query = manager.createQuery("select n from Product n", Product.class);
    return query.getResultList();
  }

  public List<Product> getAllProductsForUser(User user) {
    TypedQuery<Product> query = manager.createQuery("select n from Product n where n.user = :user", Product.class);
    return query.setParameter("user", user).getResultList();
  }

  public List<UserDTO> getAllUsersR() {
    return this.getAllUsers().stream().map(this::userToDTO).collect(Collectors.toList());
  }

  public List<ProductDTO> getAllProductsR() {
    return this.getAllProducts().stream().map(this::productToDTO).collect(Collectors.toList());
  }

  public List<ProductDTO> getAllProductsForUserR(User user) {
    return this.getAllProductsForUser(user).stream().map(this::productToDTO).collect(Collectors.toList());
  }

  private UserDTO userToDTO(User user) {
    if (user == null) return null;
    return new UserDTO(user);
  }

  private ProductDTO productToDTO(Product product) {
    if (product == null) return null;
    return new ProductDTO(product);
  }

  private User getUserForUsername(String username) {
    TypedQuery<User> query = manager.createQuery("select u from User u where u.name = :name", User.class);
    List<User> resultList = query.setParameter("name", username).getResultList();
    if (resultList == null || resultList.isEmpty()) {
      User dbUser = new User();
      dbUser.setName(username);
      manager.persist(dbUser);
      return dbUser;
    } else {
      return resultList.get(0);
    }
  }
}

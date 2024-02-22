package com.tpjad.ejb.interfaces;

import com.tpjad.ejb.dtos.ProductDTO;
import com.tpjad.ejb.dtos.UserDTO;
import com.tpjad.ejb.entities.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserProductsRemote {
  public void addProductForUserR(String product, String userName);

  public List<UserDTO> getAllUsersR();

  public List<ProductDTO> getAllProductsR();

  public List<ProductDTO> getAllProductsForUserR(User user);
}

package com.tpjad.ejb.client;

import java.io.*;
import java.util.stream.Collectors;
import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.tpjad.ejb.entities.Product;
import com.tpjad.ejb.entities.User;
import com.tpjad.ejb.interfaces.UserProductsLocal;

@WebServlet(name = "ServletClient", urlPatterns = "/client")
public class ServletClient extends HttpServlet {

  @EJB
  private UserProductsLocal userProductsLocal;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String product = request.getParameter("product");
      String userName = request.getParameter("user");

      if (product == null || product.isEmpty()) {
        throw new Error("Product cannot be empty");
      }

      if (userName == null || userName.isEmpty()) {
        throw new Error("User cannot be empty");
      }

      User user = this.userProductsLocal.addProductForUser(product, userName);

      if (user == null) {
        throw new Error("User not found");
      }

      String products = this.userProductsLocal.getAllProductsForUser(user)
          .stream()
          .map(Product::toString)
          .collect(Collectors.joining("<br>"));

      request.setAttribute("products", products);
      RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
      dispatcher.forward(request, response);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
      request.setAttribute("error", e.toString());
      dispatcher.forward(request, response);
    }
  }

  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}

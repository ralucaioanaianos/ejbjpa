package com.tpjad.ejb.clients;

import com.tpjad.ejb.dtos.ProductDTO;
import com.tpjad.ejb.interfaces.UserProductsRemote;

import java.util.*;
import javax.naming.*;

public class Client {
    static final String JNDIFacadeR = "ejb:/server/UserProductsBean!com.tpjad.ejb.interfaces.UserProductsRemote";
    public static void main(String[] args) throws Exception {
        Context context = createInitialContext();
        UserProductsRemote proxy = (UserProductsRemote) context.lookup(JNDIFacadeR);
        System.out.println("Client started");
        proxy.addProductForUserR("Product2" + java.time.LocalDate.now(), "ion");
        System.out.println("Products:");
        for (ProductDTO productDTO : proxy.getAllProductsR()) {
            System.out.println("user: " + productDTO.getUsername() + ", product: " + productDTO.getProduct());
        }
    }

    private static Context createInitialContext() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.PROVIDER_URL,
                "http-remoting://localhost:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        return new InitialContext(jndiProperties);

    }
}

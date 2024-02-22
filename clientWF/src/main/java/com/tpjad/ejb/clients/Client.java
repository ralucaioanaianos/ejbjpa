package com.tpjad.ejb.clients;

import com.tpjad.ejb.dtos.NoteDTO;
import com.tpjad.ejb.interfaces.UserNotesRemote;

import java.util.*;
import javax.naming.*;

public class Client {
    static final String JNDIFacadeR = "ejb:/server/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesRemote";

    /*
          java:global/server/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesRemote
          java:app/server/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesRemote
          java:module/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesRemote
          java:jboss/exported/server/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesRemote
          ejb:/server/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesRemote
          java:global/server/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesLocal
          java:app/server/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesLocal
          java:module/UserNotesBean!com.tpjad.ejb.interfaces.UserNotesLocal
   */
    public static void main(String[] args) throws Exception {
        Context context = createInitialContext();
        UserNotesRemote proxy = (UserNotesRemote) context.lookup(JNDIFacadeR);

        System.out.println("Client started");

        // Add a new user book
        proxy.addNoteForUserR("Book2" + java.time.LocalDate.now(), "ion");

        // Print all the user books
        System.out.println("Books:");
        for (NoteDTO noteDTO : proxy.getAllNotesR()) {
            System.out.println("user: " + noteDTO.getUsername() + ", book: " + noteDTO.getNote());
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

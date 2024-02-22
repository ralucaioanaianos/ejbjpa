package com.tpjad.ejb.clients;

import com.tpjad.ejb.dtos.NoteDTO;
import com.tpjad.ejb.interfaces.UserNotesRemote;

import javax.naming.*;
import java.util.Properties;

public class Client {

  static final String JNDIName = "com.tpjad.ejb.interfaces.UserNotesRemote#com.tpjad.ejb.interfaces.UserNotesRemote";
  static final Properties JNDI = new Properties();
  static {
    JNDI.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
    JNDI.put("org.omg.CORBA.ORBInitialHost", "localhost");
    JNDI.put("org.omg.CORBA.ORBInitialPort", "3700");
  }
  public static void main(String[] args) throws Exception {
    Context context = null;
    try {

      UserNotesRemote proxy = (UserNotesRemote) (new InitialContext(JNDI)).lookup(JNDIName);
      System.out.println("Client started");

      proxy.addNoteForUserR("Book", "ion");

      System.out.println("Books: \n");
      for (NoteDTO noteDTO : proxy.getAllNotesR()) {
        System.out.println("user:" + noteDTO.getUsername() + ", book:" + noteDTO.getNote());
      }
    } finally {
      if (context != null) {
        try {
          context.close();
        } catch (NamingException e) {
          System.out.println("Failed to close context: " + e);
        }
      }
    }
  }
}

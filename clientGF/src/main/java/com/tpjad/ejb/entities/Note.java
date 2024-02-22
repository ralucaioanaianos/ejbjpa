package com.tpjad.ejb.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "notes")
public class Note extends Base implements Serializable {
  private String note = "";
  @ManyToOne
  private User user;

  public String getNote() {
    return this.note;
  }

  public void setNote(String newNote) {
    if (newNote == null || newNote.isEmpty()) {
      return;
    }
    this.note = newNote;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "{id: " + getId() + " , book: " + getNote() + " , userId: " + getUser().getId() + "}";
  }
}

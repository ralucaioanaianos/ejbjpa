package com.tpjad.ejb.interfaces;

import com.tpjad.ejb.dtos.NoteDTO;
import com.tpjad.ejb.dtos.UserDTO;
import com.tpjad.ejb.entities.User;

import java.util.List;

public interface UserNotesRemote {
  public void addNoteForUserR(String note, String userName);

  public List<UserDTO> getAllUsersR();

  public List<NoteDTO> getAllNotesR();

  public List<NoteDTO> getAllNotesForUserR(User user);
}
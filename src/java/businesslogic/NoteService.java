/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NoteDB;
import domainmodel.Note;
import java.util.List;

/**
 *
 * @author 742227
 */
public class NoteService {
     private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(int noteID) throws Exception {
        return noteDB.getNote(noteID);
    }

    public List<Note> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(int noteID, String contents) throws Exception {
        Note note = new Note(noteID, contents);
        return noteDB.update(note);
    }

    public int delete(int noteID) throws Exception {
        Note deletedUser = noteDB.getNote(noteID);
        return noteDB.delete(deletedUser);
    }

    public int insert(String contents) throws Exception {
        java.util.Date date = new java.util.Date();
        Note note = new Note(date, contents);
        return noteDB.insert(note);
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domainmodel.Note;
import domainmodel.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 742227
 */
public class NoteDB {

    public int insert(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String preparedQuery = "INSERT INTO Note (noteID,dateCreated,contents) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, note.getNoteID());
            ps.setDate(2, (Date) note.getDateCreated());
            ps.setString(3, note.getContents());
         
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            throw new NotesDBException("Error inserting user");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public int update(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String preparedSQL = "UPDATE Note SET "
                    + "dateCreate = ?, "
                    + "contents = ?, "
                    + "WHERE noteID = ?";

            PreparedStatement ps = connection.prepareStatement(preparedSQL);

            ps.setDate(1, (Date) note.getDateCreated());
            ps.setString(2, note.getContents());
            ps.setInt(3, note.getNoteID());

            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error updating note");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public List<Note> getAll() throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM notes;");
            rs = ps.executeQuery();
            List<Note> notes = new ArrayList<>();
            while (rs.next()) {
                notes.add(new Note(rs.getInt("noteID"), rs.getDate("dateCreated"), rs.getString("contents")));
            }
            pool.freeConnection(connection);
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting Notes");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }

    /**
     * Get a single user by their username.
     *
     * @param username The unique noteID.
     * @return A User object if found, null otherwise.
     * @throws NotesDBException
     */
    public Note getNote(int noteID) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String selectSQL = "SELECT * FROM User WHERE username = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(selectSQL);
            ps.setInt(1, noteID);
            rs = ps.executeQuery();

            Note note= null;
            while (rs.next()) {
                note = new Note(rs.getInt("noteID"), rs.getDate("dateCreated"), rs.getString("contents"));
            }
            pool.freeConnection(connection);
            return note;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException("Error getting Users");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }

    public int delete(Note note) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "DELETE FROM User WHERE username = ?";
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, note.getNoteID());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting User");
        } finally {
            pool.freeConnection(connection);
        }
    }
}



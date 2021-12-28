package edu.ucalgary.ensf409;

import java.sql.*;

/**
 * Do we need to have the ids and object as class variables? or should we have
 * them passed into the method deleteItem?
 */

public class DataBaseUpdate {
    public final String DBURL; // store the database url information
    public final String USERNAME; // store the user's account username
    public final String PASSWORD; // store the user's account password

    /**
     * Default constructor
     */
    public DataBaseUpdate() {
        this.DBURL = null;
        this.USERNAME = null;
        this.PASSWORD = null;
    }

    /**
     * Constructor initializes all the class variables
     * 
     * @param dburl
     * @param username
     * @param password
     */
    public DataBaseUpdate(String dburl, String username, String password) {

        this.DBURL = dburl;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    /**
     * 
     * @param object // the object i.e. chair, lamp, desk, etc
     * @param ids    // the ids of the items that are ordered and should be removed
     *               from inventory
     */
    public void deleteItem(String object, String[] ids) {
        try {
            // trying to establish a connection to the database
            Connection connectToDatabase = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
            for (int i = 0; i < ids.length; i++) {
                String tempItem = ids[i]; // sorting the id at index i in the array
                try {
                    String query = "DELETE FROM " + object + " WHERE ID = \"" + tempItem + "\""; // preparing the general SQL command
                    
					PreparedStatement myStmt = connectToDatabase.prepareStatement(query);
            
					myStmt.executeUpdate();
					myStmt.close();
                 
                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            connectToDatabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * @return the class variable DBURL to the caller
     */
    public String getDBURL() {
        return this.DBURL;
    }

    /**
     * 
     * @return the class variavle PASSWORD to the caller
     */
    public String getPASSWORD() {
        return this.PASSWORD;
    }

    /**
     * 
     * @return the class variavle USERNAME to the caller
     */
    public String getUSERNAME() {
        return USERNAME;
    }

}

package databaseControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
        private Connection con = null;
        
        public void connect(String url,String username,String password) {
                try {
                      // Load (and therefore register) the Oracle Driver
                      Class.forName("oracle.jdbc.driver.OracleDriver"); 
                     
                      // Get a Connection to the database
                        url = "jdbc:oracle:thin:@ondora01.hu.nl:8521:cursus01";
                      
                       username = "THO5_2012_2A_TEAM4";
                       password = "THO5_2012_2A_TEAM4";
                      
                      con = DriverManager.getConnection(url, username, password);
                } catch(ClassNotFoundException e) { 
                      System.out.println("Couldn't load database driver: " + e.getMessage());
                    }
                    catch(SQLException e) { 
                      System.out.println("SQLException caught: " + e.getMessage());
                    }
        }
        
        public void closeConnection() {
                try {
                        con.close();
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                }
        }
        
        public void executeQuery(String query) {
                try {
                        Statement statement = con.createStatement();
                        statement.executeQuery(query);
                } catch (java.sql.SQLException e) {
                        System.err.println(e);
                }
        }
        
        public ResultSet doQuery(String query) {
                ResultSet result = null;
                try {
                        Statement statement = con.createStatement();
                        result = statement.executeQuery(query);
                } catch (java.sql.SQLException e) {
                        System.err.println(e);
                }
                return result;
        }
        
        
        
}

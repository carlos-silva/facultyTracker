/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacultyTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class MysqlConnection {
    
        private static final MysqlConnection instance = new MysqlConnection();
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/faculty";
	public static final String USER = "root";
	public static final String PASSWORD = "root123";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	
	
private MysqlConnection() {
		try {
			 Class.forName(DRIVER_CLASS).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException ex) {
                    Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
	}
	
	public Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}	
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
}


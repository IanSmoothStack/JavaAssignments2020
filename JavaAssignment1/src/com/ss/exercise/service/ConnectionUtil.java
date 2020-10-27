/**
 * 
 */
package com.ss.exercise.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ian
 *
 */
public class ConnectionUtil {
	public static String driverName = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public static String userName = "root";
	public static String password = "Root223";
	public Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(Boolean.FALSE);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
}

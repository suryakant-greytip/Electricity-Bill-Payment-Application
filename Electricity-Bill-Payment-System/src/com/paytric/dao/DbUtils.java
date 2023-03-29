package com.paytric.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbUtils {

	public static Connection startConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResourceBundle rs=ResourceBundle.getBundle("dbdetails");
		
		return DriverManager.getConnection(rs.getString("url"), rs.getString("username"), rs.getString("password"));
	}
	
	public static void closeConnection(Connection con) throws SQLException {
		if(con!=null) {
			con.close();
		}
	}
	
	public static boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		if(!rs.isBeforeFirst() && rs.getRow()==0) {
			return true;
		}
		return false;
	}
	
}

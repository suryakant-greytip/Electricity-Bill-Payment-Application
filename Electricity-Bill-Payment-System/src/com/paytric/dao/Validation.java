package com.paytric.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.paytric.exceptions.SomethingWentWrongException;

public class Validation {

	
	public static boolean checkSameUsername(String userName) throws SomethingWentWrongException{
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="Select * FROM consumers where consumerUserName=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, userName);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				return false;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  ***Unable to fetch data please try again later***");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  ***Unable to fetch data please try again later***");
			}
		}
		return true;
	}
	
	public static boolean checkSameEmail(String email) throws SomethingWentWrongException{
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="Select * FROM consumers where consumerEmail=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				return false;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  ***Unable to fetch data please try again later***");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  ***Unable to fetch data please try again later***");
			}
		}
		return true;
	}
	
}

package com.paytric.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.paytric.dto.ConsumerDto;
import com.paytric.dto.ConsumerDtoImpl;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public class ConsumerDaoImpl implements ConsumerDao{

	@Override
	public ConsumerDto consumerLoginData(String uName, String pass)  throws SomethingWentWrongException, InvalidUsernameOrPasswordException {
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM consumers WHERE consumerUserName=? AND consumerPassword=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, uName);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new InvalidUsernameOrPasswordException("  ***Invalid Username or Password***");
			}
			else {
				rs.next();
				return new ConsumerDtoImpl(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
				
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to fecth data please try again later.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to fecth data please try again later.");
			}
		}
	} 
}

package com.paytric.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.paytric.dto.AdminDto;
import com.paytric.dto.AdminDtoImpl;
import com.paytric.dto.ConsumerDto;
import com.paytric.dto.ConsumerDtoImpl;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public class AdminDaoImpl implements AdminDao{

	@Override
	public AdminDto adminLoginData(String username,String password) throws InvalidUsernameOrPasswordException, SomethingWentWrongException {
		
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="SELECT adminUserName, adminPassword from administrator where adminUserName=? AND adminPassword=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new InvalidUsernameOrPasswordException("Invalid Username or Password! ");
			}
			else {
				rs.next();
				return new AdminDtoImpl(rs.getString(1), rs.getString(2), rs.getString(1));
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Unable to Login, something went wrong.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Unable to Login, something went wrong.");
			}
		}
	}

	@Override
	public List<ConsumerDto> viewAllConsumerData() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		List<ConsumerDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM Consumers";
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("Record not found in database.");
			}
			else {
				while(rs.next()) {
					list.add(new ConsumerDtoImpl(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Unable to fetch data, please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Unable to fetch data, please try again later");
			}
		}
		return list;
	}
	
	
}

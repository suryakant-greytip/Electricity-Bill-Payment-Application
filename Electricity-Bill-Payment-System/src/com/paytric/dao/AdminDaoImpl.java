package com.paytric.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.paytric.dto.AdminDto;
import com.paytric.dto.AdminDtoImpl;
import com.paytric.dto.BillDto;
import com.paytric.dto.BillDtoImpl;
import com.paytric.dto.ConsumerDto;
import com.paytric.dto.ConsumerDtoImpl;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public class AdminDaoImpl implements AdminDao{

	/**
	 * @author singh
	 * admin login functionality
	 * returns Object of AdminDto 
	 */
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
				throw new InvalidUsernameOrPasswordException("  Invalid Username or Password! ");
			}
			else {
				rs.next();
				return new AdminDtoImpl(rs.getString(1), rs.getString(2), rs.getString(1));
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to Login, something went wrong.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to Login, something went wrong.");
			}
		}
	}

	/**
	 * @author singh
	 *It is used to fetch consumer from database and returns list of consumerDto object.  
	 */
	@Override
	public List<ConsumerDto> viewAllConsumerData() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		List<ConsumerDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM Consumers WHERE is_active=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, 1);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("  Record not found in database.");
			}
			else {
				while(rs.next()) {
					list.add(new ConsumerDtoImpl(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
			}
		}
		return list;
	}

	
	
	@Override
	public List<BillDto> viewConsumerBillDataById(String conId)
			throws SomethingWentWrongException, RecordNotFoundException {
		
		Connection con=null;
		List<BillDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM bill WHERE consumerId=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, conId);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("  Record not found in database.");
			}
			else {
				while(rs.next()) {
					list.add(new BillDtoImpl(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8), rs.getDouble(9), rs.getDate(10).toLocalDate(), rs.getDate(11).toLocalDate(), rs.getDate(12).toLocalDate(),rs.getDate(13).toLocalDate(),rs.getInt(14)));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
			}
		}
		return list;
	}

	
	@Override
	public List<BillDto> viewAllBillsData() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		List<BillDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM bill order by Id";
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("  Record not found in database.");
			}
			else {
				while(rs.next()) {
					list.add(new BillDtoImpl(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8), rs.getDouble(9), rs.getDate(10).toLocalDate(), rs.getDate(11).toLocalDate(), rs.getDate(12).toLocalDate(),rs.getDate(13).toLocalDate(),rs.getInt(14)));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
			}
		}
		return list;
	}

	
	
	@Override
	public String getNameById(String ConsumerId) throws SomethingWentWrongException, RecordNotFoundException {
		
		Connection con=null;
		String result=null;
		try {
			con=DbUtils.startConnection();
			String query="select firstName, lastName from consumers where consumerId=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ConsumerId);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("  Record not found in database.");
			}
			else {
				rs.next();
				result=rs.getString(1)+" "+rs.getString(2);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
			}
		}
		return result;
	}

	
	
	@Override
	public List<BillDto> viewAllPaidBillsData() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		List<BillDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM bill WHERE is_paid=? order by Id";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, 1);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("  Record not found in database.");
			}
			else {
				while(rs.next()) {
					list.add(new BillDtoImpl(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8), rs.getDouble(9), rs.getDate(10).toLocalDate(), rs.getDate(11).toLocalDate(), rs.getDate(12).toLocalDate(),rs.getDate(13).toLocalDate(),rs.getInt(14)));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
			}
		}
		return list;
	}

	
	@Override
	public List<BillDto> viewAllPendingBillsData() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		List<BillDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM bill WHERE is_paid=? order by Id";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, 0);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("  Record not found in database.");
			}
			else {
				while(rs.next()) {
					list.add(new BillDtoImpl(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8), rs.getDouble(9), rs.getDate(10).toLocalDate(), rs.getDate(11).toLocalDate(), rs.getDate(12).toLocalDate(),rs.getDate(13).toLocalDate(),rs.getInt(14)));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to fetch data, please try again later");
			}
		}
		return list;
	}

	
	
	@Override
	public void deleteConsumerDataById(String consId) throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="UPDATE consumers set is_active=? WHERE consumerId=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, 0);
			ps.setString(2, consId);
            int rowAffected=ps.executeUpdate();
            if(rowAffected < 1) {
            	throw new RecordNotFoundException("  User with consumerId '"+consId+"' is not available in database.");
            }
            
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("  Unable to delete data please try again later.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("  Unable to delete data please try again later.");
			}
		}
		
	}
	
	
	
	
}

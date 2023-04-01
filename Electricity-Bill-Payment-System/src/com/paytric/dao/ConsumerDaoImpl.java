package com.paytric.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.paytric.dto.BillDto;
import com.paytric.dto.BillDtoImpl;
import com.paytric.dto.ConsumerDto;
import com.paytric.dto.ConsumerDtoImpl;
import com.paytric.exceptions.InvalidSecurityCredentails;
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
				throw new InvalidUsernameOrPasswordException("Invalid Username or Password");
			}
			else {
				rs.next();
				return new ConsumerDtoImpl(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
				
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Unable to fecth data please try again later.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Unable to fecth data please try again later.");
			}
		}
	}

	
	@Override
	public int getLastDataId() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		int res=0;
		try {
			con=DbUtils.startConnection();
			String query="SELECT Id from consumers order by Id desc limit 1";
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("Record not found in database");
			}
			rs.next();
			res=rs.getInt(1);
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Unable to fetch data please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Unable to fetch data please try again later");
			}
		}
		
		return res;
	}


	
	
	@Override
	public void registerConsumerData(ConsumerDto cDto) throws SomethingWentWrongException {
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="INSERT into consumers(consumerId,consumerUserName,consumerEmail,consumerPassword,securityQuestion,securityAnswer,firstName,lastName,address,MobileNo) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, cDto.getConsumerId());
			ps.setString(2, cDto.getConsumerUserName());
			ps.setString(3, cDto.getConsumerEmail());
			ps.setString(4, cDto.getConsumerPassword());
			ps.setString(5, cDto.getSecQuestion());
			ps.setString(6, cDto.getSecAnswer());
			ps.setString(7, cDto.getFirstName());
			ps.setString(8, cDto.getLastName());
			ps.setString(9, cDto.getAddress());
			ps.setString(10, cDto.getMobileNum());
			int result=ps.executeUpdate();
			
			if(result==0) {
				throw new SomethingWentWrongException("Unable to add data please try again later.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Unable to add data please try again later.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Unable to add data please try again later.");
			}
		}
		
	}


	
	
	@Override
	public List<BillDto> showAllPendingBillsData(String consId)
			throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		List<BillDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM bill WHERE is_paid=? AND consumerId=?  order by billId";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, 0);
			ps.setString(2, consId);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("Record not found in database.");
			}
			else {
				while(rs.next()) {
					list.add(new BillDtoImpl(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8), rs.getDouble(9), rs.getDate(10).toLocalDate(), rs.getDate(11).toLocalDate(), rs.getDate(12).toLocalDate(),rs.getDate(13).toLocalDate(),rs.getInt(14)));
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


	
	
	@Override
	public void payConsumerBill(String BillId) throws SomethingWentWrongException {
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="Update bill set is_paid=? where billId=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, 1);
			ps.setString(2, BillId);
			ps.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Payment Failed! please try again later.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Payment Failed! please try again later.");
			}
		}
	}


	
	
	
	
	@Override
	public boolean verifySecurityCredentials(String ques, String ans, String consId)
			throws SomethingWentWrongException, InvalidSecurityCredentails {
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="SELECT securityQuestion,securityAnswer from consumers Where securityQuestion=? AND securityAnswer=? AND consumerId=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ques);
			ps.setString(2, ans);
			ps.setString(3, consId);
			
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new InvalidSecurityCredentails("Wrong Security Credentials! please enter correct credentials");
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Unable to fetch data! please try again later");
		}
		return true;
	}


	
	
	
	
	@Override
	public void updatePassword(String pass, String consId) throws SomethingWentWrongException {
		
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="Update consumers set consumerPassword=? where consumerId=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, pass);
			ps.setString(2, consId);
			ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Payment Failed! please try again later.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Payment Failed! please try again later.");
			}
		}
	}


	
	
	
	
}

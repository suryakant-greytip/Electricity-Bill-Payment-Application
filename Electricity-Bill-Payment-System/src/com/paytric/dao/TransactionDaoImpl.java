package com.paytric.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.paytric.dto.BillDto;
import com.paytric.dto.BillDtoImpl;
import com.paytric.dto.TransactionDto;
import com.paytric.dto.TransactionDtoImpl;
import com.paytric.exceptions.RecordIsAlreadyUptoDate;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public class TransactionDaoImpl implements TransactionDao{

	@Override
	public int getLastTransactionId() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		int res=0;
		try {
			con=DbUtils.startConnection();
			String query="SELECT Id from bill_transaction_history order by Id desc limit 1";
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
	public BillDto getBillByBillId(String billId) throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		BillDto bill=null;
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM bill WHERE billId=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, billId);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("Record not found in database");
			}
			else {
				rs.next();
				bill=new BillDtoImpl(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7), rs.getDouble(8), rs.getDouble(9), rs.getDate(10).toLocalDate(), rs.getDate(11).toLocalDate(), rs.getDate(12).toLocalDate(),rs.getDate(13).toLocalDate(),rs.getInt(14));
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
		return bill;
	}


	
	
	@Override
	public void addTransactionData(TransactionDto trans) throws SomethingWentWrongException, RecordIsAlreadyUptoDate {
		
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="Insert into bill_transaction_history(consumerId,billId,amount_paid,payment_date,payment_method,transactionId) values(?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, trans.getConsumerId());
			ps.setString(2, trans.getBillId());
			ps.setDouble(3, trans.getAmountPaid());
			ps.setDate(4, Date.valueOf(trans.getPaymentDate()));
			ps.setString(5, trans.getPaymentMethod());
			ps.setString(6, trans.getTransactionId());
			
			int res=ps.executeUpdate();
			if(res==0) {
				throw new RecordIsAlreadyUptoDate("Transaction has been done already");
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
			throw new SomethingWentWrongException("Something went wrong please try again later"); 
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				System.out.println(e);
				throw new SomethingWentWrongException("Something went wrong please try again later"); 
			}
		}
		
	}


	
	
	
	@Override
	public List<TransactionDto> viewTransactionHistoryData(String consId)
			throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		List<TransactionDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM bill_transaction_history WHERE consumerId=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, consId);
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("Record not found in database");
			}
			else {
				while(rs.next()) {
					list.add(new TransactionDtoImpl(rs.getString(7), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5).toLocalDate(), rs.getString(6)));
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

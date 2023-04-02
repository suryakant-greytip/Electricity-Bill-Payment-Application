package com.paytric.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.paytric.dto.ComplaintDto;
import com.paytric.dto.ComplaintDtoImpl;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public class ComplaintDaoImpl implements ComplaintDao{

	
	@Override
	public int getLastComplaintId() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		int res=0;
		try {
			con=DbUtils.startConnection();
			String query="SELECT Id from complaints order by Id desc limit 1";
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
	public void fileComplaintData(ComplaintDto comp) throws SomethingWentWrongException {
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="Insert into complaints(complaintId, consumerId, date, time, complaintType, complaintDescription) values(?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, comp.getCompId());
			ps.setString(2, comp.getConsId());
			ps.setDate(3, Date.valueOf(comp.getCompDate()));
			ps.setTime(4, Time.valueOf(comp.getCompTime()));
			ps.setString(5, comp.getCompTypa());
			ps.setString(6, comp.getCompDesc());
			
			int res=ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Unable to file complaint! please try again later");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Unable to file complaint! please try again later");
			}
		}
		
	}



	@Override
	public List<ComplaintDto> viewAllComplaints() throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		List<ComplaintDto> list=new ArrayList<>();
		try {
			con=DbUtils.startConnection();
			String query="SELECT * FROM Complaints where status=0 order by id";
			PreparedStatement ps=con.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			if(DbUtils.isResultSetEmpty(rs)) {
				throw new RecordNotFoundException("Record Not found in Database.");
			}
			else {
				while(rs.next()) {
					list.add(new ComplaintDtoImpl(rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getTime(5).toLocalTime(), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), null, null));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
			throw new SomethingWentWrongException("Unable to fetch data something went wrong.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Unable to fetch data something went wrong.");
			}
		}
		return list;
	}



	
	
	
	@Override
	public void resolveComplaint(String compId) throws SomethingWentWrongException, RecordNotFoundException {
		Connection con=null;
		try {
			con=DbUtils.startConnection();
			String query="UPDATE Complaints set status=?,resolvedDate=?,resolvedTime=? where complaintId=? ";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, 1);
			ps.setDate(2, Date.valueOf(LocalDate.now()));
			ps.setTime(3, Time.valueOf(LocalTime.now()));
			ps.setString(4, compId);

			int res=ps.executeUpdate();
			if(res==0) {
				throw new RecordNotFoundException("Issue is Resolved Already");
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SomethingWentWrongException("Unable to resolve complaint something went wrong.");
		}
		finally {
			try {
				DbUtils.closeConnection(con);
			} catch (SQLException e) {
				throw new SomethingWentWrongException("Unable to resolve complaint something went wrong.");
			}
		}
	}

	
	
	
}

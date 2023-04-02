package com.paytric.dao;

import java.util.List;

import com.paytric.dto.ComplaintDto;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public interface ComplaintDao {

	public int getLastComplaintId() throws SomethingWentWrongException,RecordNotFoundException;
	
	public void fileComplaintData(ComplaintDto comp) throws SomethingWentWrongException;
	
	public List<ComplaintDto> viewAllComplaints() throws SomethingWentWrongException,RecordNotFoundException;
	
	public void resolveComplaint(String compId) throws SomethingWentWrongException, RecordNotFoundException;
	
	
}

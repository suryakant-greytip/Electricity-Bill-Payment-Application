package com.paytric.dao;

import com.paytric.dto.*;
import java.util.List;

import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public interface AdminDao {

	public AdminDto adminLoginData(String username,String password) throws InvalidUsernameOrPasswordException, SomethingWentWrongException;
 
	public List<ConsumerDto> viewAllConsumerData() throws SomethingWentWrongException, RecordNotFoundException; 
	
	public List<BillDto> viewConsumerBillDataById(String conId) throws SomethingWentWrongException,RecordNotFoundException;
	
	public List<BillDto> viewAllBillsData() throws SomethingWentWrongException,RecordNotFoundException;
	
	public String getNameById(String ConsumerId)  throws SomethingWentWrongException,RecordNotFoundException;
	
	public List<BillDto> viewAllPaidBillsData() throws SomethingWentWrongException,RecordNotFoundException;

	public List<BillDto> viewAllPendingBillsData()throws SomethingWentWrongException,RecordNotFoundException;

	public void deleteConsumerDataById(String consId)throws SomethingWentWrongException,RecordNotFoundException;
	
	
	
}

package com.paytric.dao;

import com.paytric.dto.ConsumerDto;
import com.paytric.exceptions.InvalidSecurityCredentails;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

import java.util.List;

import com.paytric.dto.BillDto;

public interface ConsumerDao {

	public ConsumerDto consumerLoginData(String uName, String pass) throws SomethingWentWrongException, InvalidUsernameOrPasswordException;
	
	public int getLastDataId() throws SomethingWentWrongException,RecordNotFoundException;
	
	public void registerConsumerData(ConsumerDto cDto)throws SomethingWentWrongException;
	
	public List<BillDto> showAllPendingBillsData(String consId)throws SomethingWentWrongException,RecordNotFoundException; 
	
	public void payConsumerBill(String BillId) throws SomethingWentWrongException;
	
	public boolean verifySecurityCredentials(String ques, String ans, String consId)throws SomethingWentWrongException, InvalidSecurityCredentails;
	
	public void updatePassword(String pass, String consId)throws SomethingWentWrongException;
}

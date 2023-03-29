package com.paytric.dao;

import java.util.List;

import com.paytric.dto.AdminDto;
import com.paytric.dto.ConsumerDto;
import com.paytric.exceptions.InvalidUsernameOrPasswordException;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public interface AdminDao {

	public AdminDto adminLoginData(String username,String password) throws InvalidUsernameOrPasswordException, SomethingWentWrongException;
 
	public List<ConsumerDto> viewAllConsumerData() throws SomethingWentWrongException, RecordNotFoundException; 
}

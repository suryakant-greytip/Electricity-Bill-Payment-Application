package com.paytric.dao;

import java.sql.Connection;
import java.util.List;

import com.paytric.dto.BillDto;
import com.paytric.dto.TransactionDto;
import com.paytric.exceptions.RecordIsAlreadyUptoDate;
import com.paytric.exceptions.RecordNotFoundException;
import com.paytric.exceptions.SomethingWentWrongException;

public interface TransactionDao {

	
	public int getLastTransactionId()throws SomethingWentWrongException,RecordNotFoundException;
	
	public BillDto getBillByBillId(String billId) throws SomethingWentWrongException,RecordNotFoundException;
	
	public void addTransactionData(TransactionDto trans)throws SomethingWentWrongException, RecordIsAlreadyUptoDate;
	
	public List<TransactionDto> viewTransactionHistoryData(String consId)throws SomethingWentWrongException,RecordNotFoundException;
	
	
	
}

package com.paytric.dto;

import java.time.LocalDate;

public interface TransactionDto {

	public String getTransactionId();

	public void setTransactionId(String transactionId);

	public String getConsumerId();

	public void setConsumerId(String consumerId);

	public String getBillId();

	public void setBillId(String billId);

	public double getAmountPaid();

	public void setAmountPaid(double amountPaid);

	public LocalDate getPaymentDate();

	public void setPaymentDate(LocalDate paymentDate);

	public String getPaymentMethod();

	public void setPaymentMethod(String paymentMethod);
	
	
}

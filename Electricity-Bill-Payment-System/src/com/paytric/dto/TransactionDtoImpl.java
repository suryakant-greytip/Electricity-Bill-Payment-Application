package com.paytric.dto;

import java.time.LocalDate;

public class TransactionDtoImpl implements TransactionDto{

	private String TransactionId;
	private String consumerId;
	private String billId;
	private double amountPaid;
	private LocalDate paymentDate;
	private String paymentMethod;
	
	public TransactionDtoImpl(String transactionId, String consumerId, String billId, double amountPaid,
			LocalDate paymentDate, String paymentMethod) {
		TransactionId = transactionId;
		this.consumerId = consumerId;
		this.billId = billId;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
	}

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}

package com.paytric.dto;

import java.time.LocalDate;

public class BillDtoImpl implements BillDto{

	private String consumerId;
	private String billId;
	private double prevReading;
	private double currReading;
	private double unitConsumed;
	private int unitRate;
	private double totalAmount;
	private double tax;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate billing_date;
	private LocalDate dueDate;
	private int isPaid;
	
	public BillDtoImpl(String consumerId, String billId, double prevReading, double currReading, double unitConsumed,
			int unitRate, double totalAmount, double tax, LocalDate startDate, LocalDate endDate,
			LocalDate billing_date, LocalDate dueDate, int isPaid) {
		
		this.consumerId = consumerId;
		this.billId = billId;
		this.prevReading = prevReading;
		this.currReading = currReading;
		this.unitConsumed = unitConsumed;
		this.unitRate = unitRate;
		this.totalAmount = totalAmount;
		this.tax = tax;
		this.startDate = startDate;
		this.endDate = endDate;
		this.billing_date = billing_date;
		this.dueDate = dueDate;
		this.isPaid = isPaid;
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

	public double getPrevReading() {
		return prevReading;
	}

	public void setPrevReading(double prevReading) {
		this.prevReading = prevReading;
	}

	public double getCurrReading() {
		return currReading;
	}

	public void setCurrReading(double currReading) {
		this.currReading = currReading;
	}

	public double getUnitConsumed() {
		return unitConsumed;
	}

	public void setUnitConsumed(double unitConsumed) {
		this.unitConsumed = unitConsumed;
	}

	public int getUnitRate() {
		return unitRate;
	}

	public void setUnitRate(int unitRate) {
		this.unitRate = unitRate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getBilling_date() {
		return billing_date;
	}

	public void setBilling_date(LocalDate billing_date) {
		this.billing_date = billing_date;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public int getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}

	
}

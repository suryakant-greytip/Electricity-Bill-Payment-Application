package com.paytric.dto;

import java.time.LocalDate;

public interface BillDto {

	
	public String getConsumerId();

	public void setConsumerId(String consumerId);

	public String getBillId();

	public void setBillId(String billId);

	public double getPrevReading();

	public void setPrevReading(double prevReading);

	public double getCurrReading();

	public void setCurrReading(double currReading);

	public double getUnitConsumed();

	public void setUnitConsumed(double unitConsumed);

	public int getUnitRate();

	public void setUnitRate(int unitRate) ;

	public double getTotalAmount();

	public void setTotalAmount(double totalAmount);

	public double getTax();

	public void setTax(double tax);

	public LocalDate getStartDate();

	public void setStartDate(LocalDate startDate);

	public LocalDate getEndDate();

	public void setEndDate(LocalDate endDate);

	public LocalDate getBilling_date();

	public void setBilling_date(LocalDate billing_date);

	public LocalDate getDueDate();

	public void setDueDate(LocalDate dueDate);

	public int getIsPaid();

	public void setIsPaid(int isPaid);
}

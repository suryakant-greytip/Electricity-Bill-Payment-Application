package com.paytric.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ComplaintDto {

	public String getCompId();


	public void setCompId(String compId);


	public String getConsId() ;


	public void setConsId(String consId);
	
	
	public LocalDate getCompDate();


	public void setCompDate(LocalDate compDate);


	public LocalTime getCompTime();


	public void setCompTime(LocalTime compTime);


	public String getCompTypa();


	public void setCompTypa(String compTypa);


	public String getCompDesc();


	public void setCompDesc(String compDesc) ;


	public String getAssignedTo();


	public void setAssignedTo(String assignedTo);


	public int getStatus();


	public void setStatus(int status);


	public LocalDate getResDate() ;


	public void setResDate(LocalDate resDate);


	public LocalTime getResTime() ;


	public void setResTime(LocalTime resTime);
	
	
}

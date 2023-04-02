package com.paytric.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ComplaintDtoImpl implements ComplaintDto{

	private String compId;
	private String consId;
	private LocalDate compDate;
	private LocalTime compTime;
	private String compTypa;
	private String compDesc;
	private String assignedTo;
	private int status;
	private LocalDate resDate;
	private LocalTime resTime;
	
	
	public ComplaintDtoImpl(String compId, String consId, LocalDate compDate, LocalTime compTime, String compTypa,
			String compDesc, String assignedTo, int status, LocalDate resDate, LocalTime resTime) {
		this.compId = compId;
		this.consId = consId;
		this.compDate = compDate;
		this.compTime = compTime;
		this.compTypa = compTypa;
		this.compDesc = compDesc;
		this.assignedTo = assignedTo;
		this.status = status;
		this.resDate = resDate;
		this.resTime = resTime;
	}


	public String getCompId() {
		return compId;
	}


	public void setCompId(String compId) {
		this.compId = compId;
	}


	public String getConsId() {
		return consId;
	}


	public void setConsId(String consId) {
		this.consId = consId;
	}


	public LocalDate getCompDate() {
		return compDate;
	}


	public void setCompDate(LocalDate compDate) {
		this.compDate = compDate;
	}


	public LocalTime getCompTime() {
		return compTime;
	}


	public void setCompTime(LocalTime compTime) {
		this.compTime = compTime;
	}


	public String getCompTypa() {
		return compTypa;
	}


	public void setCompTypa(String compTypa) {
		this.compTypa = compTypa;
	}


	public String getCompDesc() {
		return compDesc;
	}


	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}


	public String getAssignedTo() {
		return assignedTo;
	}


	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public LocalDate getResDate() {
		return resDate;
	}


	public void setResDate(LocalDate resDate) {
		this.resDate = resDate;
	}


	public LocalTime getResTime() {
		return resTime;
	}


	public void setResTime(LocalTime resTime) {
		this.resTime = resTime;
	}
	
	
}

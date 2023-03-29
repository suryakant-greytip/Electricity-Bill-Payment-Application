package com.paytric.dto;

public class AdminDtoImpl implements AdminDto{

	private String adminUserName;
	private String adminEmail;
	private String adminPassword;
	
	public AdminDtoImpl(String adminUserName, String adminEmail, String adminPassword) {
		this.adminUserName = adminUserName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}

	@Override
	public String getAdminUserName() {
		return adminUserName;
	}

	@Override
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	@Override
	public String getAdminEmail() {
		return adminEmail;
	}

	@Override
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	@Override
	public String getAdminPassword() {
		return adminPassword;
	}

	@Override
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}

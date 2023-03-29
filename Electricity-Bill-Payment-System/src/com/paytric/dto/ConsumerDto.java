package com.paytric.dto;

public interface ConsumerDto {

	public String getConsumerId();

	public void setConsumerId(String consumerId);

	public String getConsumerUserName();

	public void setConsumerUserName(String consumerUserName);

	public String getConsumerEmail();

	public void setConsumerEmail(String consumerEmail);

	public String getConsumerPassword();

	public void setConsumerPassword(String consumerPassword);

	public String getSecQuestion();

	public void setSecQuestion(String secQuestion);

	public String getSecAnswer();

	public void setSecAnswer(String secAnswer);

	public int getIsActive();

	public void setIsActive(int isActive);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getAddress();

	public void setAddress(String address);

	public String getMobileNum();

	public void setMobileNum(String mobileNum);
}

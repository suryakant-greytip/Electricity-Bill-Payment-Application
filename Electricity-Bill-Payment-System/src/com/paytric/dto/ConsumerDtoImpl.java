package com.paytric.dto;

public class ConsumerDtoImpl implements ConsumerDto{

	private String consumerId;
	private String consumerUserName;
	private String consumerEmail;
	private String consumerPassword;
	private String secQuestion;
	private String secAnswer;
	private int isActive;
	private String firstName;
	private String lastName;
	private String address;
	private String mobileNum;
	
	public ConsumerDtoImpl(String consumerId, String consumerUserName, String consumerEmail, String consumerPassword,
			String secQuestion, String secAnswer, int isActive, String firstName, String lastName, String address,
			String mobileNum) {
		this.consumerId = consumerId;
		this.consumerUserName = consumerUserName;
		this.consumerEmail = consumerEmail;
		this.consumerPassword = consumerPassword;
		this.secQuestion = secQuestion;
		this.secAnswer = secAnswer;
		this.isActive = isActive;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobileNum = mobileNum;
	}

	@Override
	public String getConsumerId() {
		return consumerId;
	}

	@Override
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	@Override
	public String getConsumerUserName() {
		return consumerUserName;
	}

	@Override
	public void setConsumerUserName(String consumerUserName) {
		this.consumerUserName = consumerUserName;
	}

	@Override
	public String getConsumerEmail() {
		return consumerEmail;
	}

	@Override
	public void setConsumerEmail(String consumerEmail) {
		this.consumerEmail = consumerEmail;
	}

	@Override
	public String getConsumerPassword() {
		return consumerPassword;
	}

	@Override
	public void setConsumerPassword(String consumerPassword) {
		this.consumerPassword = consumerPassword;
	}

	@Override
	public String getSecQuestion() {
		return secQuestion;
	}

	@Override
	public void setSecQuestion(String secQuestion) {
		this.secQuestion = secQuestion;
	}

	@Override
	public String getSecAnswer() {
		return secAnswer;
	}

	@Override
	public void setSecAnswer(String secAnswer) {
		this.secAnswer = secAnswer;
	}

	@Override
	public int getIsActive() {
		return isActive;
	}

	@Override
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getMobileNum() {
		return mobileNum;
	}

	@Override
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
}

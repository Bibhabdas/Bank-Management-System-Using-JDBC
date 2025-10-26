package org.bank.dto;

import java.sql.Date;

public class CustomerDetails {
	/* Customer_Id, Customer_Name, Customer_EmailId, Customer_Mobile_NUmber, 
	 * Customer_Aadhar_Number, Customer_PAN_Number, Customer_Gender, 
	 * Customer_Address, Customer_Status, Customer_Date_Of_Birth, Customer_Amount, 
	 * Customer_Account_Number, Customer_PIN, Customer_IFSC_Code */

	private int customerId;
	private String customerName;
	private String CustomerEmailId;
	private long customerMobileNumber;
	private long aadharNumber;
	private String panNumber;
	private long accountNumber;
	private int pinNumber;
	private String gender;
	private String address;
	private Date dateOfBirth;
	private String ifsccode;
	private double amount;
	private String status;
	
	public CustomerDetails() {
		
	}

	public CustomerDetails(int customerId, String customerName, String customerEmailId, long customerMobileNumber,
		long aadharNumber, String panNumber, long accountNumber, int pinNumber, String gender, String address,
			Date dateOfBirth, String ifsccode, double amount, String status) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.CustomerEmailId = customerEmailId;
		this.customerMobileNumber = customerMobileNumber;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.accountNumber = accountNumber;
		this.pinNumber = pinNumber;
		this.gender = gender;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.ifsccode = ifsccode;
		this.amount = amount;
		this.status = status;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmailId() {
		return CustomerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		CustomerEmailId = customerEmailId;
	}

	public long getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(long customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerDetails [customerId=" + customerId + ", customerName=" + customerName + ", CustomerEmailId="
				+ CustomerEmailId + ", customerMobileNumber=" + customerMobileNumber + ", aadharNumber=" + aadharNumber
				+ ", panNumber=" + panNumber + ", accountNumber=" + accountNumber + ", pinNumber=" + pinNumber
				+ ", gender=" + gender + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", ifsccode="
				+ ifsccode + ", amount=" + amount + ", status=" + status + "]";
	}
	
}

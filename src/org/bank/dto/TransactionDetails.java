package org.bank.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails {
	/*Transaction_ID, Transaction_Amount, Transction_Type, 
	 * Transaction_Date, Transaction_Time, Account_Number, 
	 * Balance_Amount, Reciver_Account_Number*/
	
	private int transactionId;
	private String transactionType;
	private double transactionAmount;
	private LocalDate transcationDate;
	private LocalTime transactionTime;
	private long accountNumber;
	private double balanceAmount;
	private long raccountNumber;
	
	public TransactionDetails() {}

	public TransactionDetails(int transactionId, String transactionType, double transactionAmount,
			LocalDate transcationDate, LocalTime transactionTime, int accountNumber, double balanceAmount,
			int raccountNumber) {
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transcationDate = transcationDate;
		this.transactionTime = transactionTime;
		this.accountNumber = accountNumber;
		this.balanceAmount = balanceAmount;
		this.raccountNumber = raccountNumber;
	}
	
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public LocalDate getTranscationDate() {
		return transcationDate;
	}

	public void setTranscationDate(LocalDate transcationDate) {
		this.transcationDate = transcationDate;
	}

	public LocalTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public long getRaccountNumber() {
		return raccountNumber;
	}

	public void setRaccountNumber(long raccountNumber) {
		this.raccountNumber = raccountNumber;
	}

	public int getTransactionId() {
		return transactionId;
	}
	
	public void transactionDetails() {
		System.out.println("Transaction Id: "+transactionId);
		System.out.println("Transaction Type: "+transactionType);
		System.out.println("Transaction Amount: "+transactionAmount);
		System.out.println("Transaction Date: "+transcationDate);
		System.out.println("Transaction Time: "+transactionTime);
		System.out.println("Transaction Account Number: "+accountNumber);
		System.out.println("Remain Balance: "+balanceAmount);
		System.out.println("Reciver Account Number: "+raccountNumber);
		System.out.println("***-----------***-------------***");
	}
	@Override
	public String toString() {
		return "TransactionDetails [transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + ", transcationDate=" + transcationDate
				+ ", transactionTime=" + transactionTime + ", accountNumber=" + accountNumber + ", balanceAmount="
				+ balanceAmount + ", raccountNumber=" + raccountNumber + "]";
	}
	
	
}

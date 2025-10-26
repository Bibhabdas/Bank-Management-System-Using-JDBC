package org.bank.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.bank.dao.CustomerDAO;
import org.bank.dao.TransactionDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.exception.CustomerInvalidDataException;

public class CustomerService {
	CustomerDAO customerDAO=new CustomerDAO();
	TransactionDAO transactionDAO=new TransactionDAO();
	Scanner sc=new Scanner(System.in);
	TransactionService transactionService=new TransactionService();
	public void customerRegistration() {
		List<CustomerDetails> allCustomerDetails=customerDAO.getAllCustomerDetails();
		CustomerDetails customerDetails=new CustomerDetails();
		//--------------validating Name------------------
		System.out.println("Enter the Customer Name");
		while(true) {
			try {
				String name=sc.next();
				for(int i=0;i<name.length();i++) {
					if(!(Character.isAlphabetic(name.charAt(i)))) {
						throw new CustomerInvalidDataException("Invalid Name");
					}
				}
				customerDetails.setCustomerName(name);
				break;
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionMSG());
				System.out.println("ReEnter Valid Name:");
			}
		}
		//------------Validating Email Id------------------------
		System.out.println("Enter the Customer Email ID: ");
		while(true) {
			try {
				String cemailId=sc.next();
				long cemailIdCount=allCustomerDetails.stream().filter((customer)->customer.getCustomerEmailId().equals(cemailId)).count();
				if(!(cemailId.endsWith("@gmail.com"))) {
					throw new CustomerInvalidDataException("Invalid Email Id");
				}
				if(cemailIdCount>0) {
					throw new CustomerInvalidDataException("Email Id already existed");
					
				}else {
					customerDetails.setCustomerEmailId(cemailId);
					break;
				}
			}catch (CustomerInvalidDataException e) {
				System.out.println(e.getExceptionMSG());
				System.out.println("ReEnter Valid EmailId:");
			}
		}
		//------------Validating Mobile Number------------------
		System.out.println("Enter the Customer Mobile Number");
		while(true) {
			try {
				long cmobilenumber=sc.nextLong();
				long cmobilenumbercount=allCustomerDetails.stream().filter((customer)->customer.getCustomerMobileNumber()==cmobilenumber).count();
				if(!(cmobilenumber>=6000000000l && cmobilenumber<=9999999999l)) {
					throw new CustomerInvalidDataException("Invalid Mobile Number");
				}
				if(cmobilenumbercount>0) {
					throw new CustomerInvalidDataException("Mobile Number already existed");
				}else {
					customerDetails.setCustomerMobileNumber(cmobilenumber);
					break;
				}
			}catch (CustomerInvalidDataException e) {
				System.out.println(e.getExceptionMSG());
				System.out.println("ReEnter Valid Mobile Number:");
			}
		}
		//-------------Validating Aadhar-----------
		System.out.println("Enter the Customer Aadhar Number");
		while(true) {
			try {
				long caadharnumber=sc.nextLong();
				long caadharnumberCount=allCustomerDetails.stream().filter((customer)->customer.getAadharNumber()==caadharnumber).count();
				if(!(caadharnumber>=100000000000l && caadharnumber<=999999999999l)) {
					throw new CustomerInvalidDataException("Invalid Aadhar Number");
				}
				if(caadharnumberCount>0) {
					throw new CustomerInvalidDataException("Aadhar Number ALready Existed");
				}else {
					customerDetails.setAadharNumber(caadharnumber);
					break;
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionMSG());
				System.out.println("ReEnter Valid Adhar Number:");
			}
		}
		//---------------Validating Pan-------------------
		System.out.println("Enter Customer PAN Number");
		while(true) {
			try {
				String cpannumber=sc.next();
				cpannumber=cpannumber.toUpperCase();
				String cPanNumber=cpannumber;
				long cpannumberCount=allCustomerDetails.stream().filter((customer)->customer.getPanNumber().equals(cPanNumber)).count();
				if(cpannumber.length()==10) {
					
				}else {
					throw new CustomerInvalidDataException("Invalid Pan Number");
				}
				for(int i=0;i<5;i++) {
					if(Character.isAlphabetic(cpannumber.charAt(i))) {
						
					}else {
						throw new CustomerInvalidDataException("Invalid PAN Number");
					}
				}
				for(int i=5;i<9;i++) {
					if(Character.isDigit(cpannumber.charAt(i))) {
						
					}else {
						throw new CustomerInvalidDataException("Invalid PAN Number");
					}
				}
				if(!(Character.isAlphabetic(cpannumber.charAt(9)))) {
					throw new CustomerInvalidDataException("Invalid PAN Number");
				}
				if(cpannumberCount>0) {
					throw new CustomerInvalidDataException("PAN Number ALready Existed");
				}else {
					customerDetails.setPanNumber(cpannumber);
					break;
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionMSG());
				System.out.println("ReEnter Valid Pan Number:");
			}
		}
		//-----------Validating Gender------------------
		System.out.println("Enter Customer Gender");
		while(true) {
			try {
				String cgender=sc.next();
				if(cgender.equalsIgnoreCase("Male")||cgender.equalsIgnoreCase("Female")|| cgender.equalsIgnoreCase("Others")) {
					customerDetails.setGender(cgender);
					break;
				}else {
					throw new CustomerInvalidDataException("Invalid Gender");
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionMSG());
				System.out.println("ReEnter Valid Gender:");
			}
		}
		//-----------validating DOB----------
		System.out.println("Enter customer Date of Birth");
		String cdob=sc.next();
		customerDetails.setDateOfBirth(Date.valueOf(cdob));
		//-----------Validating Address---------
		System.out.println("Enter customer Address");
		String caddress=sc.next();
		customerDetails.setAddress(caddress);
		
		//-----------Validating Amount---------------
		System.out.println("Enter the Amount");
		while(true) {
			try {
				double amount=sc.nextDouble();
				if(amount>0) {
					customerDetails.setAmount(amount);
					break;
				}else {
					throw new CustomerInvalidDataException("Invalid Amount");
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionMSG());
				System.out.println("ReEnter Valid Amount:");
			}
		}
		if(customerDAO.insertCustomerDetails(customerDetails)) {
			System.out.println("Registration Successfull...");
		}else {
			System.out.println("Server error 500");
		}
	}
	public List<CustomerDetails> getAllPendingDetails(){
		return customerDAO.selectCustomerDetailsByUsingStatus();
	}
	public void upadtePendingDetailsCustomer(CustomerDetails customerDetails) {
		if(customerDAO.updateNewCustomerDetails(customerDetails)) {
			System.out.println("Updated successfully...");
		}else {
			System.out.println("Error Occur during Updation...");
		}
	}
	
	//Login 
	public void customerLogin() {
		System.out.println("Enter your Email Id or Mobile Number");
		String emailIdOrMobno=sc.next();
		String status=customerDAO.selectStatusByUsingEmailIdOrMobileNumber(emailIdOrMobno);
		try {
			if(status.equalsIgnoreCase("Active")) {
				System.out.println("Enter your Pin Number");
				int pinNumber=sc.nextInt();
				if(customerDAO.selectCustomerDetailsByUsingEmailIdOrMobileNumberAndPinNumber(emailIdOrMobno, pinNumber)) {
					System.out.println("Login Successfull");
					customerOperations(pinNumber);
				}else {
					System.out.println("Invalid Email Id or Mobile Number or Pin Number");
				}
			}else {
				System.out.println("Still You Account is Pending");
			}
		}catch(NullPointerException e) {
			System.out.println("Invalid Credentials");
		}
	}
	public void customerOperations(int pin) {
		boolean start=true;
		while(start) {
		System.out.println("Enter \n 1.For Debit"
				+ "\n 2.For Credit "
				+ "\n 3.For Check Balance "
				+ "\n 4.For Check Statement "
				+ "\n 5.For Mobile Transcation "
				+ "\n 6.For Change Pin "
				+ "\n 7.For Closing Account "
				+ "\n 8.For Logout");
		
			switch (sc.nextInt()) {
			case 1:
				System.out.println("Debit");
				debitOperation(pin);
				break;
			case 2:
				System.out.println("Credit");
				creditOperation(pin);
				break;
			case 3:
				System.out.println("Check Balance");
				checkBalance(pin);
				break;
			case 4:
				System.out.println("Check Statement");
				checkStatement(pin);
				break;
			case 5:
				System.out.println("Make Mobile Transaction");
				mobileTransaction(pin);
				break;
			case 6:
				System.out.println("Change PIN Number");
				changePin(pin);
				break;
			case 7:
				System.out.println("Request For Closing Account");
				requestForClosingAccount(pin);
				break;
			case 8:
				System.out.println("Successfully Logout....");
				System.out.println("***-------------***--------------***");
				start=false;
				break;
			default:
				System.out.println("Please Enter a Valid Choice");
				break;
			}
			System.out.println();
		}
	}
	public void debitOperation(int pin) {
		System.out.println("Enter the Account Number");
		long accno=sc.nextLong();
		CustomerDetails customerDetails=customerDAO.selectCustomerDetailsByUsingAccountNumber(accno);
		if(customerDetails!=null && pin==customerDetails.getPinNumber()) {
			System.out.println("Enter amount to withdraw");
			double cAmount=sc.nextDouble();
			double dbAmount=customerDetails.getAmount();
			if(dbAmount>=cAmount) {
				dbAmount=dbAmount-cAmount;
				boolean update=customerDAO.updateBalanceAmountByUsingAccountNumber(dbAmount, accno);
				if(update) {
					System.out.println(cAmount+" Debited Successfully..");
					System.out.println("Your Bank Balance is: "+dbAmount);
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setTransactionType("DEBIT");
					transactionDetails.setTransactionAmount(cAmount);
					transactionDetails.setTranscationDate(LocalDate.now());
					transactionDetails.setTransactionTime(LocalTime.now());
					transactionDetails.setAccountNumber(accno);
					transactionDetails.setBalanceAmount(dbAmount);
					transactionDetails.setRaccountNumber(accno);
					transactionService.addTransactionDetails(transactionDetails);
				}else {
					System.out.println("Please Wait.... Your Transaction is Under Processing");
				}
			}else {
				System.out.println("Insufficent Bank Balance");
			}
		}else {
			System.out.println("Invalid Account Number");
		}
	}
	public void creditOperation(int pin) {
		System.out.println("Enter the Account Number");
		long accno = sc.nextLong();
		CustomerDetails customerDetails = customerDAO.selectCustomerDetailsByUsingAccountNumber(accno);
		if (customerDetails != null && pin == customerDetails.getPinNumber()) {
			System.out.println("Enter amount to Deposite");
			double cAmount = sc.nextDouble();
			double dbAmount = customerDetails.getAmount();
			dbAmount = dbAmount + cAmount;
			boolean update = customerDAO.updateBalanceAmountByUsingAccountNumber(dbAmount, accno);
			if (update) {
				System.out.println(cAmount + " Credited Successfully..");
				System.out.println("Your Bank Balance is: " + dbAmount);
				TransactionDetails transactionDetails=new TransactionDetails();
				transactionDetails.setTransactionType("CREDIT");
				transactionDetails.setTransactionAmount(cAmount);
				transactionDetails.setTranscationDate(LocalDate.now());
				transactionDetails.setTransactionTime(LocalTime.now());
				transactionDetails.setAccountNumber(accno);
				transactionDetails.setBalanceAmount(dbAmount);
				transactionDetails.setRaccountNumber(accno);
				transactionService.addTransactionDetails(transactionDetails);
			} else {
				System.out.println("Please Wait.... Your Transaction is Under Processing");
			}
		} else {
			System.out.println("Invalid Account Number");
		}
	}
	public void checkStatement(int pin) {
	    System.out.println("Enter the Account Number");
	    long accno = sc.nextLong();
	    CustomerDetails customerDetails = customerDAO.selectCustomerDetailsByUsingAccountNumber(accno);
	    if (customerDetails != null && pin == customerDetails.getPinNumber()) {
	        transactionService.checkStatementDetails(accno);
	    } else {
	        System.out.println("Invalid Account Number or PIN");
	    }
	}

	public void changePin(int pin) {
		System.out.println("Enter the Account Number");
		long accno = sc.nextLong();
		CustomerDetails customerDetails = customerDAO.selectCustomerDetailsByUsingAccountNumber(accno);
		if (customerDetails != null && pin == customerDetails.getPinNumber()) {
			while(true) {
				System.out.println("Enter Your Old PIN Number");
				int oldPin=sc.nextInt();
				if(oldPin==pin) {
					System.out.println("Enter the new PIN Number");
					int newPin=sc.nextInt();
					if(newPin==pin) {
						System.out.println("New PIN cannot be same as Previous Pin");
					}else {
						System.out.println("Enter Again to confirm Your PIN Number");
						int confirmPin=sc.nextInt();
						if(confirmPin==newPin) {
							if(customerDAO.updatePINnumberByUsingCurrentPin(accno,oldPin,newPin)) {
								customerDetails.setPinNumber(newPin);
								System.out.println("Your new PIN Number:"+newPin+" Updated Successfully");
								break;
							}else {
								System.out.println("Server Error\nYou new PIN Number is Not Updated...Please try again later...");
							}
						}else {
							System.out.println("Please enter the Same PIN number for New PIN and Confirm PIN");
						}
					}
				}else {
					System.out.println("Your Current PIN is Invalid");
				}
			}
		} else {
			System.out.println("Invalid Account Number or PIN");
		}
	}
	public void checkBalance(int pin) {
		System.out.println("Enter the Account Number");
		long accno = sc.nextLong();
		CustomerDetails customerDetails = customerDAO.checkBalanceByUsingPin(accno);
		if (customerDetails != null && pin == customerDetails.getPinNumber()) {
			System.out.println("Account Balance: "+customerDetails.getAmount());
		}else {
			System.out.println("Invalid Account");
		}
	}
	public void requestForClosingAccount(int pin) {
		System.out.println("Enter the Account Number");
		long accno = sc.nextLong();
		System.out.println("Did you withdrew all your amount?[Yes/No]");
		String response=sc.next();
		if(response.equalsIgnoreCase("Yes")) {
			if(customerDAO.updateStatusByUsingAccnoAndPin(accno, pin)) {
				System.out.println("Your closing Account Request has sent successfully.Thank You for Using Our Service");
			}else {
				System.out.println("Server Error");
			}
		}else {
			System.out.println("At first Collect all your amount from your account. After your account is removed you cannot get your money back.");
		}
	}
	
	public void displayColsedAccountUser() {
		List<CustomerDetails> cDetails = customerDAO.selectCustomerAccClosingRequest();
		for (CustomerDetails i : cDetails) {
			System.out.println("***----------------***---------------***");
			System.out.println(i);
		}
	}
	
	public boolean deleteCustomerDetails(long accno) {
		if(customerDAO.deleteCustomerDetailsByUsingStatus(accno)) {
			return true;
		}else {
			return false;
		}
	}
	public void mobileTransaction(int pin) {
		System.out.println("Enter your Mobile Number");
		long mobno=sc.nextLong();
		System.out.println("Enter Your Account Number");
		long cAccno=sc.nextLong();
		CustomerDetails customerDetails=customerDAO.selectCustomerByUsingMobileNumber(pin, mobno,cAccno);
		if(customerDetails!=null) {
			System.out.println("Enter Reciver Mobile Number");
			long rmobno=sc.nextLong();
			CustomerDetails reciverDetails=customerDAO.selectReciverByUsingMobileNumber(rmobno);
			System.out.println("Enter the Amount to transfer: ");
			double cAmount=sc.nextDouble();
			double dbAmount=customerDetails.getAmount();
			if(dbAmount>=cAmount) {
				dbAmount=dbAmount-cAmount;
				boolean update=customerDAO.updateBalanceAmountByUsingAccountNumber(dbAmount, cAccno);
				if(update) {
					System.out.println("Don't go back. Processing your Transaction...");
					System.out.println("Your Bank Balance is: "+dbAmount);
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setTransactionType("Mobile Transaction");
					transactionDetails.setTransactionAmount(cAmount);
					transactionDetails.setTranscationDate(LocalDate.now());
					transactionDetails.setTransactionTime(LocalTime.now());
					transactionDetails.setAccountNumber(cAccno);
					transactionDetails.setBalanceAmount(dbAmount);
					transactionDetails.setRaccountNumber(reciverDetails.getAccountNumber());
					transactionService.addTransactionDetails(transactionDetails);
				}else {
					System.out.println("Something Went wrong. Please try Again later...");
				}
			}else {
				System.out.println("Insufficent Bank Balance");
			}
			if(reciverDetails!=null) {
				double rdbAmount=cAmount+reciverDetails.getAmount();
				boolean rUpdate=customerDAO.updateBalanceAmountByUsingAccountNumber(rdbAmount, rmobno);
				if(rUpdate) {
					System.out.println(cAmount+"Money Send Successfully...");
				}
			}else {
				System.out.println(cAmount+"Money Send Successfully...");
			}
		}else {
			System.out.println("Invalid Mobile Number");
		}
	}
}

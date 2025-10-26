package org.bank.service;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.bank.dao.AdminDAO;
import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;

public class AdminService {
	Scanner sc = new Scanner(System.in);
	AdminDAO admindao = new AdminDAO();
	CustomerDAO customerdao = new CustomerDAO();
	CustomerService customerService = new CustomerService();

	public void adminLogin() {

		System.out.println("Enter Admin Email Id:");
		String aemail = sc.next();

		System.out.println("Enter Admin Password:");
		String epassword = sc.next();

		if (admindao.selectAdminDetailsByUsingEmailIdAndPassword(aemail, epassword)) {
			System.out.println("Login Successfull...\n");
			boolean start = true;
			while (start) {
				System.out.println("Enter \n1.To Get All Customer Details " + "\n2.To Accept Pending Details "
						+ "\n3.To Get All Account Requests\n4.Logout");
				switch (sc.nextInt()) {
				case 1:
					System.out.println("Get All Customer Details");
					getAllCustomerDetails();
					break;
				case 2:
					System.out.println("Accept Pending Details");
					accetptPendingDetails();
					break;
				case 3:
					System.out.println("Get All Account Requests");
					removeCustomer();
					break;
				case 4:
					System.out.println("Successfully Logout");
					start=false;
					break;
				default:
					System.out.println("Enter a Valid Choice...");
					break;
				}
			}

		} else {
			System.out.println("Invalid Email Id or Password");
		}

	}

	public void getAllCustomerDetails() {
		List<CustomerDetails> cDetails = customerdao.getAllCustomerDetails();
		for (CustomerDetails i : cDetails) {
			System.out.println("***----------------***---------------***");
			System.out.println(i);
		}
	}

	public List<CustomerDetails> allPendingDetails() {
		List<CustomerDetails> pendingCustomers = customerService.getAllPendingDetails();
		if (pendingCustomers != null) {
			System.out.println("Pending Customer Details:");
			for (CustomerDetails customer : pendingCustomers) {
				String mobileNumber = String.valueOf(customer.getCustomerMobileNumber());

				String maskedMobileNumber = mobileNumber.substring(0, 3) + "***" + mobileNumber.substring(7);

				// Display customer details with the masked mobile number
				System.out.println("***----------***----------------***");
				System.out.println("Customer Id: " + customer.getCustomerId());
				System.out.println("Customer Name: " + customer.getCustomerName());
				System.out.println("Customer Email: " + customer.getCustomerEmailId());
				System.out.println("Mobile Number: " + maskedMobileNumber);
				System.out.println("Gender: " + customer.getGender());
				System.out.println("Address: " + customer.getAddress());
				System.out.println("Amount: " + customer.getAmount());
			}
			System.out.println("***---------***-------------***");
		} else {
			System.out.println("No pending customers found.");
		}

		System.out.println("Enter Email Id of the Customer");
		String cemailId = sc.next();
		return pendingCustomers.stream().filter((customer) -> customer.getCustomerEmailId().equals(cemailId))
				.collect(Collectors.toList());
	}

	public void accetptPendingDetails() {
		CustomerDetails customerDetails = allPendingDetails().get(0);
		System.out.println(customerDetails);
		Random random = new Random();
		int accountNumber = random.nextInt(10000000);
		if (accountNumber < 1000000) {
			accountNumber += 1000000;
		}
		int pin = random.nextInt(10000);
		if (pin < 1000) {
			pin += 1000;
		}
		customerDetails.setAccountNumber(accountNumber);
		customerDetails.setPinNumber(pin);
		customerDetails.setIfsccode("M16BANK007");
		customerDetails.setStatus("ACTIVE");
		System.out.println(customerDetails);

		customerService.upadtePendingDetailsCustomer(customerDetails);
	}
	
	public void removeCustomer() {
		customerService.displayColsedAccountUser();
		System.out.println("-----------------------------------");
		System.out.println("Enter the Account Number");
		long accno=sc.nextLong();
		if(customerService.deleteCustomerDetails(accno)) {
			System.out.println("Customer with Account Number: "+accno+" is removed");
		}else {
			System.out.println("Server Error");
		}
	}
}

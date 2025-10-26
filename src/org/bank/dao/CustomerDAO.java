package org.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.CustomerDetails;
import org.bank.service.AdminService;
import org.bank.util.JdbcConnections;

public class CustomerDAO {
	private static final String insert_customer_details = "insert into customer_details(Customer_Name, Customer_EmailId, Customer_Mobile_NUmber, "
			+ "Customer_Aadhar_Number, Customer_PAN_Number, Customer_Gender, Customer_Address, Customer_Status, Customer_Date_Of_Birth, "
			+ "Customer_Amount) values(?,?,?,?,?,?,?,?,?,?)";
	private static final String select_all_customer="select * from customer_details";
	private static final String select_by_using_status="select * from customer_details where Customer_Status=?";
	private static final String update_by_using_emailid="update customer_details set Customer_Account_Number=?, Customer_PIN=?, Customer_IFSC_Code=?, Customer_Status=? where Customer_EmailId=?";
	private static final String select_by_using_emailIdOrMobnoAndPin="select * from customer_details where (Customer_EmailId=? or Customer_Mobile_NUmber=?) and Customer_PIN=?";
	private static final String select_status_by_using_EmailId_Or_MobileNumber="select Customer_Status from customer_details where Customer_EmailId=? or Customer_Mobile_NUmber=?";
	private static final String select_customerDetails_by_using_accno="select * from customer_details where Customer_Account_Number=?";
	private static final String update_balance_amount_by_using_Accno="update customer_details set Customer_Amount=? where Customer_Account_Number=?";
	private static final String update_pin_number_by_using_current_pin="update customer_details set Customer_PIN=? where Customer_Account_Number=? and Customer_PIN=?";
	private static final String select_balance_by_using_accno="select * from customer_details where Customer_Account_Number=?";
	private static final String update_status_by_using_accno_and_pin="update customer_details set Customer_Status=? where Customer_Account_Number=? and Customer_PIN=?";
	private static final String delete_customer_by_using_status_and_email="delete from customer_details where Customer_Status=? and Customer_Account_Number=?";
	private static final String select_customer_by_using_status="select * from customer_details where Customer_Status=?";
	private static final String select_customer_by_using_mobno="select * from customer_details where Customer_Mobile_NUmber=? and Customer_PIN=? and Customer_Account_Number=?";
	private static final String select_recever_by_using_mobno="select * from customer_details where Customer_Mobile_NUmber=?";
	
	public boolean insertCustomerDetails(CustomerDetails customerDetails) {
		try {
			Connection connection=JdbcConnections.forMySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insert_customer_details);
			preparedStatement.setString(1, customerDetails.getCustomerName());
			preparedStatement.setString(2, customerDetails.getCustomerEmailId());
			preparedStatement.setLong(3, customerDetails.getCustomerMobileNumber());
			preparedStatement.setLong(4, customerDetails.getAadharNumber());
			preparedStatement.setString(5, customerDetails.getPanNumber());
			preparedStatement.setString(6, customerDetails.getGender());
			preparedStatement.setString(7, customerDetails.getAddress());
			preparedStatement.setString(8, "Pending");
			preparedStatement.setDate(9,customerDetails.getDateOfBirth());
			preparedStatement.setDouble(10, customerDetails.getAmount());
			int result=preparedStatement.executeUpdate();
			if(result!=0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CustomerDetails> getAllCustomerDetails(){
		try {
			Connection connection=JdbcConnections.forMySqlConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(select_all_customer);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<CustomerDetails> listOfCustomers=new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setCustomerId(resultSet.getInt("Customer_Id"));
					customerDetails.setCustomerName(resultSet.getString("Customer_Name"));
					customerDetails.setStatus(resultSet.getString("Customer_Status"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setDateOfBirth(resultSet.getDate("Customer_Date_Of_Birth"));
					customerDetails.setAddress(resultSet.getString("Customer_Address"));
					customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
					customerDetails.setAadharNumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setCustomerEmailId(resultSet.getString("Customer_EmailId"));
					customerDetails.setCustomerMobileNumber(resultSet.getLong("Customer_Mobile_NUmber"));
					customerDetails.setPanNumber(resultSet.getString("Customer_PAN_Number"));
					customerDetails.setAccountNumber(resultSet.getLong("Customer_Account_Number"));
					customerDetails.setPinNumber(resultSet.getInt("Customer_PIN"));
					customerDetails.setIfsccode(resultSet.getString("Customer_IFSC_Code"));
					
					listOfCustomers.add(customerDetails);
				}
				return listOfCustomers;
			}else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		public List<CustomerDetails> selectCustomerDetailsByUsingStatus() {
			/* select * from customer_details where Customer_Status=?*/
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(select_by_using_status);
				preparedStatement.setString(1, "Pending");
				ResultSet resultSet=preparedStatement.executeQuery();
				List<CustomerDetails> listOfCustomerDetails=new ArrayList<CustomerDetails>();
				if(resultSet.isBeforeFirst()) {
					while(resultSet.next()) {
						CustomerDetails customerDetails=new CustomerDetails();
						customerDetails.setCustomerName(resultSet.getString("Customer_Name"));
						customerDetails.setStatus(resultSet.getString("Customer_Status"));
						customerDetails.setGender(resultSet.getString("Customer_Gender"));
						customerDetails.setDateOfBirth(resultSet.getDate("Customer_Date_Of_Birth"));
						customerDetails.setAddress(resultSet.getString("Customer_Address"));
						customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
						customerDetails.setAadharNumber(resultSet.getLong("Customer_Aadhar_Number"));
						customerDetails.setCustomerEmailId(resultSet.getString("Customer_EmailId"));
						customerDetails.setCustomerMobileNumber(resultSet.getLong("Customer_Mobile_NUmber"));
						customerDetails.setPanNumber(resultSet.getString("Customer_PAN_Number"));
						
						listOfCustomerDetails.add(customerDetails);
					}
					return listOfCustomerDetails;
				}else {
					return null;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		public boolean updateNewCustomerDetails(CustomerDetails customerDetails) {
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(update_by_using_emailid);
				preparedStatement.setLong(1, customerDetails.getAccountNumber());
				preparedStatement.setInt(2, customerDetails.getPinNumber());
				preparedStatement.setString(3, customerDetails.getIfsccode());
				preparedStatement.setString(4, customerDetails.getStatus());
				preparedStatement.setString(5, customerDetails.getCustomerEmailId());
				int result=preparedStatement.executeUpdate();
				if(result>0) {
					return true;
				}else {
					return false;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		public boolean selectCustomerDetailsByUsingEmailIdOrMobileNumberAndPinNumber(String emailIdOrmobileNumber, int pinNumber) {
			
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(select_by_using_emailIdOrMobnoAndPin);
				preparedStatement.setString(1, emailIdOrmobileNumber);
				preparedStatement.setString(2, emailIdOrmobileNumber);
				preparedStatement.setInt(3, pinNumber);
				
				ResultSet resultSet=preparedStatement.executeQuery();
				if(resultSet.next()) {
					return true;
				}else {
					return false;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		public String selectStatusByUsingEmailIdOrMobileNumber(String emailIdOrmobileNumber) {
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(select_status_by_using_EmailId_Or_MobileNumber);
				preparedStatement.setString(1, emailIdOrmobileNumber);
				preparedStatement.setString(2, emailIdOrmobileNumber);
				
				ResultSet resultSet=preparedStatement.executeQuery();
				if(resultSet.next()) {
					return resultSet.getString("Customer_Status");
				}else {
					return null;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		public CustomerDetails selectCustomerDetailsByUsingAccountNumber(long accountNumber) {
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(select_customerDetails_by_using_accno);
				preparedStatement.setLong(1, accountNumber);
				ResultSet resultSet=preparedStatement.executeQuery();
				if(resultSet.next()) {
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
					customerDetails.setPinNumber(resultSet.getInt("Customer_PIN"));
					return customerDetails;
				}else {
					return null;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		public boolean updateBalanceAmountByUsingAccountNumber(double balanceAmount,long accountNumber) {
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(update_balance_amount_by_using_Accno);
				preparedStatement.setDouble(1, balanceAmount);
				preparedStatement.setLong(2, accountNumber);
				int result=preparedStatement.executeUpdate();
				if(result!=0) {
					return true;
				}else {
					return false;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		public boolean updatePINnumberByUsingCurrentPin(long accno,int oldpin,int newpin) {
			try {
				Connection connection = JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(update_pin_number_by_using_current_pin);
				
				preparedStatement.setInt(1, newpin);
				preparedStatement.setLong(2, accno);
				preparedStatement.setInt(3, oldpin);
				int result=preparedStatement.executeUpdate();
				if(result!=0) {
					return true;
				}else {
					return false;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		public CustomerDetails checkBalanceByUsingPin(long accno) {
			try {
				Connection connection = JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(select_balance_by_using_accno);
				
				preparedStatement.setLong(1, accno);
				ResultSet resultSet=preparedStatement.executeQuery();
				CustomerDetails customerDetails=new CustomerDetails();
				if(resultSet.next()) {
					customerDetails.setPinNumber(resultSet.getInt("Customer_PIN"));
					customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
					return customerDetails;
				}else {
					return null;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		public boolean updateStatusByUsingAccnoAndPin(long accno,int pin) {
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(update_status_by_using_accno_and_pin);
				preparedStatement.setString(1, "CLOSED");
				preparedStatement.setLong(2, accno);
				preparedStatement.setInt(3, pin);
				
				int result=preparedStatement.executeUpdate();
				if(result!=0) {
					return true;
				}else {
					return false;
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		public List<CustomerDetails> selectCustomerAccClosingRequest() {
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(select_customer_by_using_status);
				preparedStatement.setString(1, "CLOSED");
				ResultSet resultSet=preparedStatement.executeQuery();
				List<CustomerDetails> listOfCustomers=new ArrayList<CustomerDetails>();
				if(resultSet.isBeforeFirst()) {
					while(resultSet.next()) {
						CustomerDetails customerDetails=new CustomerDetails();
						customerDetails.setCustomerId(resultSet.getInt("Customer_Id"));
						customerDetails.setCustomerName(resultSet.getString("Customer_Name"));
						customerDetails.setStatus(resultSet.getString("Customer_Status"));
						customerDetails.setGender(resultSet.getString("Customer_Gender"));
						customerDetails.setDateOfBirth(resultSet.getDate("Customer_Date_Of_Birth"));
						customerDetails.setAddress(resultSet.getString("Customer_Address"));
						customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
						customerDetails.setAadharNumber(resultSet.getLong("Customer_Aadhar_Number"));
						customerDetails.setCustomerEmailId(resultSet.getString("Customer_EmailId"));
						customerDetails.setCustomerMobileNumber(resultSet.getLong("Customer_Mobile_NUmber"));
						customerDetails.setPanNumber(resultSet.getString("Customer_PAN_Number"));
						customerDetails.setAccountNumber(resultSet.getLong("Customer_Account_Number"));
						customerDetails.setPinNumber(resultSet.getInt("Customer_PIN"));
						customerDetails.setIfsccode(resultSet.getString("Customer_IFSC_Code"));
						
						listOfCustomers.add(customerDetails);
					}
					return listOfCustomers;
				}else {
					return null;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		public boolean deleteCustomerDetailsByUsingStatus(long accno) {
			try {
				Connection connection=JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(delete_customer_by_using_status_and_email);
				preparedStatement.setString(1, "CLOSED");
				preparedStatement.setLong(2, accno);
				int result=preparedStatement.executeUpdate();
				if(result>0) {
					return true;
				}else {
					return false;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		
		public CustomerDetails selectCustomerByUsingMobileNumber(int pin,long mobile,long accon) {
			try {
				Connection connection = JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(select_customer_by_using_mobno);
				
				preparedStatement.setLong(1, mobile);
				preparedStatement.setInt(2, pin);
				preparedStatement.setLong(3, accon);
				ResultSet resultSet=preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setCustomerId(resultSet.getInt("Customer_Id"));
					customerDetails.setCustomerName(resultSet.getString("Customer_Name"));
					customerDetails.setStatus(resultSet.getString("Customer_Status"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setDateOfBirth(resultSet.getDate("Customer_Date_Of_Birth"));
					customerDetails.setAddress(resultSet.getString("Customer_Address"));
					customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
					customerDetails.setAadharNumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setCustomerEmailId(resultSet.getString("Customer_EmailId"));
					customerDetails.setCustomerMobileNumber(resultSet.getLong("Customer_Mobile_NUmber"));
					customerDetails.setPanNumber(resultSet.getString("Customer_PAN_Number"));
					customerDetails.setAccountNumber(resultSet.getLong("Customer_Account_Number"));
					customerDetails.setPinNumber(resultSet.getInt("Customer_PIN"));
					customerDetails.setIfsccode(resultSet.getString("Customer_IFSC_Code"));
					
					return customerDetails;
				}else {
					return null;
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		public CustomerDetails selectReciverByUsingMobileNumber(long mobile) {
			try {
				Connection connection = JdbcConnections.forMySqlConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(select_recever_by_using_mobno);
				
				preparedStatement.setLong(1, mobile);
				ResultSet resultSet=preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setCustomerId(resultSet.getInt("Customer_Id"));
					customerDetails.setCustomerName(resultSet.getString("Customer_Name"));
					customerDetails.setStatus(resultSet.getString("Customer_Status"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setDateOfBirth(resultSet.getDate("Customer_Date_Of_Birth"));
					customerDetails.setAddress(resultSet.getString("Customer_Address"));
					customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
					customerDetails.setAadharNumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setCustomerEmailId(resultSet.getString("Customer_EmailId"));
					customerDetails.setCustomerMobileNumber(resultSet.getLong("Customer_Mobile_NUmber"));
					customerDetails.setPanNumber(resultSet.getString("Customer_PAN_Number"));
					customerDetails.setAccountNumber(resultSet.getLong("Customer_Account_Number"));
					customerDetails.setPinNumber(resultSet.getInt("Customer_PIN"));
					customerDetails.setIfsccode(resultSet.getString("Customer_IFSC_Code"));
					
					return customerDetails;
				}else {
					return null;
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
}


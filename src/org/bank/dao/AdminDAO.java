package org.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bank.dto.CustomerDetails;
import org.bank.util.JdbcConnections;

public class AdminDAO {
	
	private static final String admin_login="select * from admin_details where Admin_Email_ID=? and Admin_Password=?";
	
	private List<CustomerDetails> listOfCustomer;
	public boolean selectAdminDetailsByUsingEmailIdAndPassword(String emailId,String password) {
		
		try {
			Connection connection=JdbcConnections.forMySqlConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(admin_login);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);
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
	
}

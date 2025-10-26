package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.TransactionDetails;
import org.bank.util.JdbcConnections;

public class TransactionDAO {
	private static final String insert_details = "insert into transaction_details (Transaction_Amount, Transction_Type, "
			+ "Transaction_Date, Transaction_Time, Account_Number, Balance_Amount,"
			+ " Reciver_Account_Number) values(?,?,?,?,?,?,?)";
	private static final String select_transaction_details_by_using_Accno = "select * from transaction_details where Account_Number=?";

	public boolean insertTransactionDetails(TransactionDetails transactionDetails) {
		try {
			Connection connection = JdbcConnections.forMySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insert_details);
			preparedStatement.setDouble(1, transactionDetails.getTransactionAmount());
			preparedStatement.setString(2, transactionDetails.getTransactionType());
			preparedStatement.setDate(3, Date.valueOf(transactionDetails.getTranscationDate()));
			preparedStatement.setTime(4, Time.valueOf(transactionDetails.getTransactionTime()));
			preparedStatement.setLong(5, transactionDetails.getAccountNumber());
			preparedStatement.setDouble(6, transactionDetails.getBalanceAmount());
			preparedStatement.setLong(7, transactionDetails.getRaccountNumber());

			int result = preparedStatement.executeUpdate();
			if (result != 0) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<TransactionDetails> selectTransactionDetails(long accno) {
		try {
			Connection connection = JdbcConnections.forMySqlConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(select_transaction_details_by_using_Accno);
			preparedStatement.setLong(1, accno);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<TransactionDetails> listOfTransactionDetails = new ArrayList<TransactionDetails>();
			if (resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setTransactionId(resultSet.getInt("Transaction_ID"));
					transactionDetails.setTransactionAmount(resultSet.getDouble("Transaction_Amount"));
					transactionDetails.setTransactionType(resultSet.getString("Transction_Type"));
					transactionDetails.setTranscationDate(resultSet.getDate("Transaction_Date").toLocalDate());
					transactionDetails.setTransactionTime(resultSet.getTime("Transaction_Time").toLocalTime());
					transactionDetails.setAccountNumber(resultSet.getLong("Account_Number"));
					transactionDetails.setBalanceAmount(resultSet.getDouble("Balance_Amount"));
					transactionDetails.setRaccountNumber(resultSet.getLong("Reciver_Account_Number"));
					listOfTransactionDetails.add(transactionDetails);
				}
				return listOfTransactionDetails;
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}

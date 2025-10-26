package org.bank.service;

import java.util.List;

import org.bank.dao.TransactionDAO;
import org.bank.dto.TransactionDetails;

public class TransactionService {
	TransactionDAO transactionDAO=new TransactionDAO();
	public void addTransactionDetails(TransactionDetails transactionDetails) {
		if(transactionDAO.insertTransactionDetails(transactionDetails)) {
			System.out.println("Your Data is Updated");
		}else {
			System.out.println("Server Error 500");
		}
	}
	public void checkStatementDetails(long accno) {
		List<TransactionDetails> allTransactionDetails=transactionDAO.selectTransactionDetails(accno);
		try {
			allTransactionDetails.stream()
			.forEach((t)->{
				System.out.println("Transaction Id: "+t.getTransactionId());
				System.out.println("Transaction Type: "+t.getTransactionType());
				System.out.println("Transaction Amount: "+t.getTransactionAmount());
				System.out.println("Transaction Date: "+t.getTranscationDate());
				System.out.println("Transaction Time: "+t.getTransactionTime());
				System.out.println("Transaction Account Number: "+t.getAccountNumber());
				System.out.println("Remain Balance: "+t.getBalanceAmount());
				System.out.println("Reciver Account Number: "+t.getRaccountNumber());
				System.out.println("***-----------***-------------***");
			});
		}catch (Exception e) {
			System.out.println("No statement found");
		}
	}
}

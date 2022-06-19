package com.mylogistic.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mylogistic.factory.Transaction;
import com.mylogistic.transport.Truck;

public class Repository {

	private static Repository instance;

	private Repository() {

	}

	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
		}
		return instance;
	}

	public Vector<Transaction> getTransactions(Vector<Transaction> transactions) {
		//Vector<Transaction> transactions = new Vector<>();
		transactions.clear();

		try {
			String query = "SELECT shipping_id, package_id, transport_id, created_date, eta, arrival_date, `status`\n"
					+ "FROM transaction t\n" + "LEFT JOIN transport tp ON tp.id = t.transport_id";
			ResultSet res = DBConnection.getConnection().execute(query);
			String packageId, createdDate, eta, arrivalDate, status;
			Integer shippingId, transportId;
			while (res.next()) {
				shippingId = res.getInt("shipping_id");
				packageId = res.getString("package_id");
				transportId = res.getInt("transport_id");
				createdDate = res.getString("created_date");
				eta = res.getString("eta");
				arrivalDate = res.getString("arrival_date");
				status = res.getString("status");
				transactions.add(
						new Transaction(shippingId, packageId, transportId, createdDate, eta, arrivalDate, status));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

	public void addTransaction(String packageId, int transportId, String createdDate, String eta, String arrivalDate) {
		DBConnection con = DBConnection.getConnection();
		String query = String.format("INSERT INTO transaction VALUES(DEFAULT, '%s', '%d', '%s', '%s', '%s')", packageId,
				transportId, createdDate, eta, arrivalDate);
		con.executeUpdate(query);
	}
	
	public void updateTransaction(String eta, String packageId) {
		DBConnection con = DBConnection.getConnection();
		String query = String.format("UPDATE transaction\r\n" + 
				"SET eta = '%s'\r\n" + 
				"WHERE package_id = '%s'", eta, packageId);
		con.executeUpdate(query);
	}

}

package com.mylogistic.factory;

import com.mylogistic.transport.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mylogistic.database.DBConnection;
import com.mylogistic.deliveryItem.Package;

public abstract class Logistics {

	public void planDelivery(Package p) {
		if (getTransportAvailability()) {
			// TODO insert the transaction..
			// TODO update the transport object status to Shipping
			Transport t = createTransport();
			System.out.println("Plan Delivery " + p + " via " + t + " Logistic.");
			t.deliver();

		} else {
			System.out.println("Sorry, we're currently unavailable to transport your package.");
			// TODO
			System.out.println("The closest available date for this " + getType() + " is at ???");
		}
	}

	abstract Transport createTransport();

	public abstract String getType();

	private boolean getTransportAvailability() {
		DBConnection db = DBConnection.getConnection();

		ResultSet rs = db.execute("SELECT count(id) as availableTransport " + "FROM transport WHERE type = '"
				+ getType() + "' " + "AND status = 'Available' ");
		// Shipping or Maintenance means it's not available
		try {
			rs.next();
			return rs.getInt("availableTransport") > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}

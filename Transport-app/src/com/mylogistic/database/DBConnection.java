package com.mylogistic.database;

import java.sql.*;

public class DBConnection {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "logistic";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
//	 database schema
//	 create table transport (
//		id int not null, 
//		type varchar(12) not null, 
//		status varchar(10) not null, #Available / Shipping / Maintenance  
//		primary key (id));
//	 create table transaction (
//     shipping_id int not null,
//     package_id varchar(10) not null,
//     transport_id int not null,
//     created_date date,
//     eta date,
//     arrival_date date,
//     primary key (shipping_id));
	
	private Connection con;
	private Statement st;
	
	private static DBConnection connection;
	
	private DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			st = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized DBConnection getConnection() {
		return connection = (connection == null) ? new DBConnection() : connection;
    }
	
	public ResultSet execute(String query) {
		ResultSet rs = null;
		
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void executeUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

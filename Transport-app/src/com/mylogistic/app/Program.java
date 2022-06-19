package com.mylogistic.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.mylogistic.database.DBConnection;
import com.mylogistic.database.Repository;
import com.mylogistic.deliveryItem.Package;
import com.mylogistic.factory.Logistics;
import com.mylogistic.factory.RoadLogistics;
import com.mylogistic.factory.SeaLogistics;
import com.mylogistic.factory.Transaction;

/* Group 4 - LJ01
 * 
 * 2440078696 - Ahmad Saugi
 * 2440107065 - Lorencia
 * 2440107720 - Randy Wirjadiredja
 * 2440040204 - Ritchie Tunggara
 * 
 * */

public class Program {

	private Scanner in = new Scanner(System.in);
	Repository repo = Repository.getInstance();
	Vector<Transaction> transactions = new Vector<>();

	private static final Map<Integer, Logistics> LOGISTIC_FACTORIES = new HashMap<>();

	static {
		LOGISTIC_FACTORIES.put(1, new RoadLogistics());
		LOGISTIC_FACTORIES.put(2, new SeaLogistics());
	};

	static String getShippingOptions() {
		ArrayList<String> optionString = new ArrayList<>();

		Iterator<Map.Entry<Integer, Logistics>> it = LOGISTIC_FACTORIES.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Logistics> e = it.next();
			optionString.add(e.getKey() + ". " + e.getValue().getType());
		}

		return Arrays.toString(optionString.toArray());
	}

	public Program() {
		// Restructure the code so that the program can handle several operations such
		// as
		// 1) create new shipping transaction
		// 2) completes a shipping transaction

		int inp = -1;

		do {
			menu();

			try {
				inp = in.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Input must be number!");
			}
			in.nextLine();

			switch (inp) {
			case 1:
				cls();
				createNew();
				cls();
				break;
			case 2:
				cls();
				viewStatus();
				cls();
				break;
			case 3:
				cls();
				updateStatus();
				cls();
				break;

			default:
				break;
			}
		} while (inp != 4);

	}

	private void updateStatus() {
		// TODO Auto-generated method stub
		String packageId, eta;

		if (repo.getTransactions(transactions).isEmpty()) {
			System.out.println("There is no transaction in here.");
			pressEnter();
		} else {
			for (Transaction t : repo.getTransactions(transactions)) {
				System.out.println(t.toString());
			}

			System.out.printf("Input package ID : ");
			packageId = in.nextLine();

			for (Transaction t : repo.getTransactions(transactions)) {
				if(t.getPackageId().contains(packageId)) {
					System.out.println("Input new eta : ");
					eta = in.nextLine();
					
					repo.updateTransaction(eta, packageId);
					System.out.println();
					System.out.println("Your transaction has been recorded.");
					pressEnter();
					break;
				}
				else if(!t.getPackageId().contains(packageId)){
					System.out.println("Vehicle ID " + packageId + " is invalid!");
					pressEnter();
					break;
				}
			}
			
//			for (int i=0; i<repo.getTransactions(transactions).size(); i++) {
//				repo.getTransactions(transactions).get(i);
//				if (!transactions && i == repo.getTransactions(transactions).size() - 1) {
//					System.out.println("Vehicle ID " + packageId + " is invalid!");
//					pressEnter();
//				} else if(repo.getTransactions(transactions).equals(packageId)){
//					System.out.println("Input new eta : ");
//					eta = in.nextLine();
//					
//					repo.updateTransaction(eta, packageId);
//					System.out.println();
//					System.out.println("Your transaction has been recorded.");
//					pressEnter();
//					break;
//				}
//				
//			}
		}
	}

	private void viewStatus() {
		// TODO Auto-generated method stub
		if (repo.getTransactions(transactions).isEmpty()) {
			System.out.println("There is no transaction in here.");
			pressEnter();
		} else {
			for (Transaction t : repo.getTransactions(transactions)) {
				System.out.println(t.toString());
			}
			pressEnter();
		}

	}

	private void createNew() {
		// TODO Auto-generated method stub
		String packageId = null, createDate = null, eta = null, arrivalDate = null, status;
		Integer transportId;

		do {
			System.out.printf("Choose transport ID : ");
			try {
				transportId = in.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				transportId = -1;
				System.out.println("Please input number!");
			}
			in.nextLine();
		} while (transportId < 0);

		System.out.printf("Input date : ");
		createDate = in.nextLine();

		System.out.printf("Input eta : ");
		eta = in.nextLine();

		System.out.printf("Input arrival date : ");
		arrivalDate = in.nextLine();

		packageId = generatePackageID(packageId);
		status = "Shipping";

		//System.out.println(packageId + transportId + createDate + eta + arrivalDate);
		repo.addTransaction(packageId, transportId, createDate, eta, arrivalDate);
		System.out.println();
		System.out.println("Your transaction has been recorded.");
		pressEnter();
	}

	private void menu() {
		// TODO Auto-generated method stub
		System.out.println("+-----------------+");
		System.out.println("| FOUR - LOGISTIC |");
		System.out.println("+-----------------+");
		System.out.println("");
		System.out.println("1. Create new shipping");
		System.out.println("2. View delivery status");
		System.out.println("3. Update delivery status");
		System.out.println("4. Exit");
		System.out.println("");
		System.out.printf("Choose >> ");
	}

	private void cls() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	private void pressEnter() {
		System.out.println();
		System.out.printf("Press enter to continue ...");
		in.nextLine();
	}

	private String generatePackageID(String packageId) {
		double x1, x2, x3;
		x1 = Math.round(Math.random() * 9) + 0;
		x2 = Math.round(Math.random() * 9) + 0;
		x3 = Math.round(Math.random() * 9) + 0;

		return packageId = "PKG" + (int) x1 + (int) x2 + (int) x3;
	}

	public static void main(String[] args) {
		new Program();
	}

}

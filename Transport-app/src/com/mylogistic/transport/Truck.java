package com.mylogistic.transport;

public class Truck implements Transport {

	@Override
	public void deliver() {
		System.out.println("Start delivery via Truck");
	}

	public String toString() {
		// TODO
		return "Truck ID ?? from database";
	}

}

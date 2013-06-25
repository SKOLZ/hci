package com.example.alerts;

import com.example.clickntravel.MainActivity;
import com.example.utils.FlightStatus;

public class ArrivalGateAlert extends Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getArrivalGate().equals(newStatus.getArrivalGate());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification(MainActivity.newArrivalGate + " "
				+ newStatus.getArrivalGate());
	}

	public String getName() {
		return "ArrivalGate";
	}

}
package com.example.alerts;

import com.example.clickntravel.MainActivity;
import com.example.utils.FlightStatus;

public class DepartureGateAlert extends Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getDepartureGate().equals(newStatus.getDepartureGate());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification(MainActivity.NEW_DEPARTURE_GATE + " " + newStatus.getDepartureGate());
	}

	public String getName() {
		return "DepartureGate";
	}
}
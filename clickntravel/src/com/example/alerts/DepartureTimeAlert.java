package com.example.alerts;

import com.example.clickntravel.MainActivity;
import com.example.utils.FlightStatus;

public class DepartureTimeAlert extends Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getDepartureTime().equals(newStatus.getDepartureTime());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification(MainActivity.newDepartureHour + " " + newStatus.getDepartureTime());
	}

	public String getName() {
		return "DepartureTime";
	}
}
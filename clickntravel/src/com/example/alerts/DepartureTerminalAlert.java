package com.example.alerts;

import com.example.clickntravel.MainActivity;
import com.example.utils.FlightStatus;

public class DepartureTerminalAlert extends Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getDepartureTerminal().equals(newStatus.getDepartureTerminal());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification(MainActivity.NEW_DEPARTURE_TERMINAL + " " + newStatus.getDepartureTerminal());
	}

	public String getName() {
		return "DepartureTerminal";
	}
}
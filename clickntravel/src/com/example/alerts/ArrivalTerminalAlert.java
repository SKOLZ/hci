package com.example.alerts;

import com.example.clickntravel.MainActivity;
import com.example.utils.FlightStatus;

public class ArrivalTerminalAlert extends Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getArrivalTerminal().equals(newStatus.getArrivalTerminal());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification(MainActivity.newArrivalTerminal + " " + newStatus.getArrivalTerminal());
	}

	public String getName() {
		return "ArrivalTerminal";
	}

}

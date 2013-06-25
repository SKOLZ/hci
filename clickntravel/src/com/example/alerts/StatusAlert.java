package com.example.alerts;

import com.example.clickntravel.MainActivity;
import com.example.utils.FlightStatus;

public class StatusAlert extends Alert {

	public boolean changedStatus(FlightStatus oldStatus,
			FlightStatus newStatus) {
		return !oldStatus.getStatus().equals(newStatus.getStatus());
	}
	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification(MainActivity.NEW_STATE + " " + newStatus.getStatus());
	}

	public String getName() {
		return "Status";
	}

}
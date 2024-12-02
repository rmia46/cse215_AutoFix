package autofix.core.services;

import java.io.Serializable;

public class Service implements Serializable {
	private static final long serialVersionUID = 10002L;
	private String title;
	private String userDescription;
	private String serviceDescription;
	private Vehicle vehicle;
	private String status;
	private String callerUserName;
	private double cost;
	private double discountFactor = 0.0;
	
	public Service(String title, String userDescription, Vehicle vehicle, String callerUserName) {
		this.title = title;
		this.userDescription = userDescription;
		this.vehicle = vehicle;
		this.callerUserName = callerUserName;
		this.serviceDescription = "N/A";
		this.status = "Pending";
		this.cost = 0.0;
		this.discountFactor = 0.0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getStatus() {
		return status;
	}

	public String getCallerUserName() {
		return callerUserName;
	}

	public void setCallerUserName(String callerUserName) {
		this.callerUserName = callerUserName;
	}

	public double getDiscountFactor() {
		return discountFactor;
	}

	public void setDiscountFactor(double discountFactor) {
		this.discountFactor = discountFactor;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost, double discountFactor) {
		this.discountFactor = discountFactor;
		this.cost = cost * (100 - discountFactor) * 0.01;
	}
	
	@Override 
	public String toString() {
		return "Title: " + title + "Status: " + status;
	}
	
	public String printDetails() {
		return "Title: " + title + "\nDescription: " + userDescription + "\nCar Details: " + vehicle.toString() + "\nStatus: " + status + "\nMessage: " + serviceDescription + 
				"\nTotal Cost: " + cost + " with " + discountFactor + "% discount.";

	}
	
}

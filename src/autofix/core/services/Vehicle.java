package autofix.core.services;

import java.io.Serializable;

import autofix.ObjectIdentifier;

public class Vehicle implements Serializable, ObjectIdentifier {

	private static final long serialVersionUID = 1003L;
	private String brand;
	private String model;
	private int year;	
	private String licensePlate;
	private String potentialIssue;
	private static int vehicleCounter = 0;
	private int vehicleId;
	
	public Vehicle(String brand, String model, int year, String licensePlate, String potentialIssue) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
		this.potentialIssue = potentialIssue;
		this.vehicleId = generateId();
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getPotentialIssue() {
		return potentialIssue;
	}

	public void setPotentialIssue(String potentialIssue) {
		this.potentialIssue = potentialIssue;
	}
	@Override
	public int generateId() {
		vehicleCounter++;
		return 9000000 + vehicleCounter;
	}
	@Override
	public String toString() {
		return "Brand: " + brand + " Model: " + model + " Year: " + " License Number: " + licensePlate;
	}
}

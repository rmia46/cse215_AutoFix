package autofix.gui.application;

import java.io.IOException; 
import autofix.AppConstants;
import autofix.core.services.Service;
import autofix.core.services.ServiceManager;
import autofix.core.services.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddServiceController implements AppConstants {
	private String title, description, brand, model, license, issues;
	private static String callerUserName;
	private int year;
	private Service service;
	private Vehicle vehicle;
	
	public static String getCallerUserName() {
		return callerUserName; 
	}
	public static void setCallerUserName(String loginUserName) {
		callerUserName = loginUserName;
	}
	
	@FXML
	TextField serviceTitle, serviceDescription, carBrand, carModel, carYear, carLicense, potentialIssue;
	
	@FXML
	Button serviceAddButton;
	
	public void initialize() {
		serviceAddButton.setOnAction(e -> {
			boolean serviceAdded = addService();
			if(serviceAdded) {
				showSuccessAlert();
			}
		});
	}
	
	private boolean addService() {
		title = serviceTitle.getText();
		description = serviceDescription.getText();
		brand = carBrand.getText();
		model = carModel.getText();
		license = carLicense.getText();
		issues = potentialIssue.getText();
		try {
			year = Integer.parseInt(carYear.getText());
		} catch(NumberFormatException err) {
			showErrorAlert("Invalid Year!");
			return false;
		}
		vehicle = new Vehicle(brand, model, year, license, issues);
		service = new Service(title, description, vehicle, callerUserName);
		
		try {
			ServiceManager.addService(service);
			System.out.println("Service Added");
			return true;
		} catch (IOException e) {
			System.out.println("Unable to add service");
		}
		showErrorAlert("Service add failed!");
		return false;
	}
	private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setContentText("Service request added! Check detials below-\n" + service.toString());
        alert.showAndWait();
    }
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message + "\nFailed to add service request! Try agian.");
        alert.showAndWait();
    }
}

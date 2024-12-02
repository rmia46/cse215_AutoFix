package autofix.gui.login;
import autofix.core.exceptions.Validations;
import autofix.core.exceptions.Validations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import autofix.AppConstants;
import autofix.core.people.Customer;
import autofix.core.people.Employee;
import autofix.core.people.People;
import autofix.core.people.PeopleManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class SignupController implements AppConstants {
	List<People> peopleList = new ArrayList<>();
	People employee, customer;
	private String name, phone, address, password, confirmPassword;
	private int nid;
    @FXML
    private Button signupButton;
    
    @FXML
	private TextField signupName, signupAddress, signupPhoneNumber, signupNID, identifier;
    
    @FXML
	private PasswordField signupPassword, signupConfirmPassword; 

    public void initialize() throws ClassNotFoundException, IOException {
        signupButton.setOnAction(e -> {
            boolean signupStatus = false;
			try {
				signupStatus = signup();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (signupStatus) {
			    showSuccessAlert();
			}
        });
    }

    private boolean signup() throws ClassNotFoundException, IOException {
    	name = signupName.getText();
		address = signupAddress.getText(); 
		phone = signupPhoneNumber.getText();
		password = signupPassword.getText();
		confirmPassword = signupConfirmPassword.getText();
		try {
			Validations.validateName(name);
		} catch(InvalidNameException err) {
			showErrorAlert(err.getMessage());
			System.out.println(err);
			return false;
		}
		try {
			Validations.validateNID(signupNID.getText());
		} catch(InvalidNIDException err) {
			showErrorAlert(err.getMessage());
			System.out.println(err);
			return false;
		}
		try {
			Validations.validatePhoneNumber(phone);
		} catch(InvalidPhoneNumberException err) {
			showErrorAlert(err.getMessage());
			System.out.println(err);
			return false;
		}
		try {
			Validations.validatePassword(password, confirmPassword);
		} catch(PasswordMismatchException err) {
			showErrorAlert(err.getMessage());
			System.out.println(err);
			return false;
		}
		if(identifier.getText().equals("EMP")) {
			employee = new Employee(name, address, phone, nid, password);
			PeopleManager.addPeople(employee);
			showDetails(employee);
			return true;
		}
		else if(identifier.getText().equals("C")) {
			customer = new Customer(name, address, phone, nid, password);
			PeopleManager.addPeople(customer);
			showDetails(customer);
			return true;
		}
		else { 
			System.out.println("Wrong Identifier");
			return false;
		}
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Signup successful!\n");
        alert.showAndWait();
    }
    private void showDetails(People person) {
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setContentText(person.toString());
    	alert.showAndWait();
    }

    private void showErrorAlert(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Signup failed.\n" + err);
        alert.showAndWait();
    }
}
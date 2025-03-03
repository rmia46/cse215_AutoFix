package autofix.gui.login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import autofix.AppConstants;
import autofix.core.people.People;
import autofix.core.people.PeopleManager;
import autofix.core.services.ServiceManager;
import autofix.gui.application.AddServiceController;
import autofix.gui.application.AppSceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

public class LoginController implements AppConstants {

	List<People> peopleList = new ArrayList<>();
	private String loginUserName, loginPassword;
	
    @FXML
    private Button loginButton;
    
    @FXML
	private TextField loginUserNameField;
    
    @FXML
	private PasswordField loginPasswordField;

    public void initialize() throws ClassNotFoundException, IOException {
        loginButton.setOnAction(e -> {
            boolean loginStatus = false;
			try {
				loginStatus = login(); 
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (loginStatus) {
			    showSuccessAlert();
			    AddServiceController.setCallerUserName(loginUserName);
			    AppSceneSwitcher obj = new AppSceneSwitcher();
			    obj.initalLoader(loginButton);
			} 
			else {
			    showErrorAlert();
			}
        });
    }

    private boolean login() throws ClassNotFoundException, IOException {
    	String filename = CUSTOMER_FILE;
    	loginUserName = loginUserNameField.getText();
		loginPassword = loginPasswordField.getText();
		
		try {
			if(!ServiceManager.isCustomer(loginUserName)) {
				filename = EMPLOYEE_FILE;
			}
			peopleList = PeopleManager.listPeople(filename);
			for(People person : peopleList) {
				if(person.getUsername().equals(loginUserName) && person.getPassword().equals(loginPassword)) {
					System.out.println("User found");
					return true;
				}
			}
			System.out.println("NO User FOUND");
		}  catch(FileNotFoundException err) {
			System.out.println(err);
		}	
		return false;
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Login successful!");
        alert.getDialogPane().setId("alert");
        alert.showAndWait();
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Username or password mismatch. Login failed.");
        alert.getDialogPane().setId("alert");
        alert.showAndWait();
    }
}
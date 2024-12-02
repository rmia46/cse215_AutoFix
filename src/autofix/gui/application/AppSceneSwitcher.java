package autofix.gui.application;
import javafx.scene.control.ButtonBar;
import java.io.IOException;

import autofix.core.services.ServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppSceneSwitcher {
	private String callerUserName = AddServiceController.getCallerUserName();
	
	@FXML
	private BorderPane mainBorderPane;
	
	@FXML
	private Label newService, serviceHistory, serviceRequests, loggedInUser;
	
	
	public void initalLoader(Button button) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/autofix/gui/application/MainLayout.fxml"));
	        Scene scene = new Scene(loader.load());
	        Stage stage = (Stage) button.getScene().getWindow(); 
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException err) {
	        err.printStackTrace();
	    }
	}
	
	public void initialize() {
		loggedInUser.setText(callerUserName);
		if(ServiceManager.isCustomer(callerUserName)) {
			serviceRequests.setText("Notifications");
			serviceRequests.setOnMouseClicked(e -> loadScene("Notifications.fxml"));
		}
		else {
			serviceRequests.setOnMouseClicked(e -> loadScene("Requests.fxml"));
		}
        newService.setOnMouseClicked(e -> loadScene("AddService.fxml"));
        serviceHistory.setOnMouseClicked(e -> loadScene("History.fxml"));
        loggedInUser.setOnMouseClicked(e -> logOut());
	}
	
	public void loadScene(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        try {
    		mainBorderPane.setCenter(loader.load());
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        
    }
	public void logOut() {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    ButtonType logoutButton = new ButtonType("Logout", ButtonBar.ButtonData.YES);
	    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.NO);

	    alert.getButtonTypes().setAll(logoutButton, cancelButton);
	    alert.setTitle("Logout");
	    alert.setHeaderText("Are you sure you want to logout?");
	    alert.setContentText("Any unsaved changes will be lost.");
	    alert.showAndWait().ifPresent(response -> {
	        if (response == logoutButton) {
	            try {
	    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/autofix/gui/login/Window.fxml"));
	    	        Scene scene = new Scene(loader.load());
	    	        Stage stage = (Stage) mainBorderPane.getScene().getWindow();
	    	        stage.setScene(scene);
	    	        stage.show();
	    	    } catch (IOException err) {
	    	        err.printStackTrace();
	    	    }
	        }
	    });
	}
}

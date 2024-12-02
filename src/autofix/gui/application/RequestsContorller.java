package autofix.gui.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import autofix.AppConstants;
import autofix.core.services.NotificationManager;
import autofix.core.services.Service;
import autofix.core.services.ServiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RequestsContorller implements AppConstants {
	private String callerUserName = AddServiceController.getCallerUserName();
	private String customerUserName;
	private List<Service> serviceList = new ArrayList<>();
	private ObservableList<Service> servicesObList; 

	
	@FXML
	private ListView<Service> serviceListView; 

	public void initialize() {
		servicesObList = FXCollections.observableArrayList();
		refreshList();

		serviceListView.setItems(servicesObList);
		
		serviceListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Check for double-click
            	
                Service selectedService = serviceListView.getSelectionModel().getSelectedItem();
                if (selectedService != null) {
                    showDetailsDialog(selectedService);
                }
            }
        });
	}
	private void refreshList() {
		try {
			serviceList = ServiceManager.getHistory(callerUserName);
			servicesObList.setAll(serviceList);
			System.out.println("List updated");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void showDetailsDialog(Service service) {
		
		customerUserName = service.getCallerUserName();
		
		Dialog<ButtonType> dialog = new Dialog<>();
	    dialog.setTitle("Service Details");
	    dialog.setHeaderText(service.getTitle());

	    TextArea textArea = new TextArea(service.printDetails());
	    textArea.setEditable(false);
	    textArea.setWrapText(true); 
	    textArea.setPrefSize(450, 300); 

	    TextField costField = new TextField();
	    TextField discountField = new TextField();
	    
	    costField.setPromptText("Enter total cost");
	    discountField.setPromptText("Enter discount amount in percentage");	    
	    
	    ButtonType acceptButton = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
	    ButtonType declineButton = new ButtonType("Decline", ButtonBar.ButtonData.OTHER);
	    ButtonType deleteButton = new ButtonType("Delete this record", ButtonBar.ButtonData.OTHER);
	    ButtonType markAsServicedButton = new ButtonType("Mark as Serviced", ButtonBar.ButtonData.OK_DONE);
	    ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
	    
	    if(service.getStatus().equals("Declined") || service.getStatus().equals("Serviced")) {
	    	dialog.getDialogPane().getButtonTypes().addAll(closeButton, deleteButton);
	    	dialog.getDialogPane().setContent(textArea);
	    }
	    else if(service.getStatus().equals("Being serviced")) {
	    	dialog.getDialogPane().getButtonTypes().addAll(markAsServicedButton, declineButton, closeButton, deleteButton);
	        VBox dialogContent = new VBox();
	        dialogContent.getChildren().addAll(textArea, costField, discountField);
	        dialog.getDialogPane().setContent(dialogContent);
	    }
	    else {
	    	dialog.getDialogPane().getButtonTypes().addAll(acceptButton, declineButton, closeButton, deleteButton);
	    	dialog.getDialogPane().setContent(textArea);
	    }
	    
	    // Handle button actions
	    dialog.setResultConverter(button -> {
	    if (button == acceptButton) {
	    	System.out.println("Accepted: " + service.getTitle());      
	        try {
	        	ServiceManager.setServiceStatus(service, "Being serviced", "Your car is being serviced", customerUserName);
	        	NotificationManager.addNotification(customerUserName, "Request accepted. Your car is being serviced");
	        	refreshList();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	        return button;     
	    } 
	    else if (button == declineButton) {
	        try {
	        	ServiceManager.setServiceStatus(service, "Declined", "We could not accept your request", customerUserName);
	        	NotificationManager.addNotification(customerUserName, "Request declined. Please contact our agent");
	        	refreshList();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	        return button;
	    }
	    else if(button == markAsServicedButton) {
	    	double cost, discountFactor;
	    	try {
	    		cost = Double.parseDouble(costField.getText());
	    		discountFactor = Double.parseDouble(discountField.getText());
	        	ServiceManager.setServiceStatus(service, "Serviced", "Your vehicle is serviced", customerUserName, cost, discountFactor);
	        	NotificationManager.addNotification(customerUserName, "Your vehicle is serviced. Check your history.");
	        	refreshList();
			} catch (NumberFormatException | ClassNotFoundException e) {
				showErrorAlert("Empty cost fields!");
			}
	        return button;
	    }
	    
	    else if(button == deleteButton) {
	    	try {
	        	ServiceManager.deleteServiceRecord(service);
	        	refreshList();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
	        return button;
	    }
	    return null;
	    
	    
	    });

	    dialog.showAndWait();
	}
	private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


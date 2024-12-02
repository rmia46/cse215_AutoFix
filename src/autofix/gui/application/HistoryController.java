package autofix.gui.application;

import javafx.scene.control.ButtonBar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import autofix.AppConstants;
import autofix.core.services.Service;
import autofix.core.services.ServiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class HistoryController implements AppConstants {
	private String callerUserName = AddServiceController.getCallerUserName();
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
		 
		Dialog<ButtonType> dialog = new Dialog<>();
	    dialog.setTitle("Service Details");
	    dialog.setHeaderText(service.getTitle());

	    TextArea textArea = new TextArea(service.printDetails());
	    textArea.setEditable(false);
	    textArea.setWrapText(true); 
	    textArea.setPrefSize(450, 300); 
	    
	    ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
	    
	    dialog.getDialogPane().getButtonTypes().addAll(closeButton);
    	dialog.getDialogPane().setContent(textArea);

	    
	    dialog.showAndWait();
	}
}

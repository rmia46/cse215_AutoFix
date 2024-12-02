package autofix.gui.application;

import java.util.ArrayList;
import java.util.List;

import autofix.AppConstants;
import autofix.core.services.NotificationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class NotificationController implements AppConstants {
	private String callerUserName = AddServiceController.getCallerUserName();
	
	private List<String> notificationList = new ArrayList<>();
	private ObservableList<String> notificationObList; 
	
	@FXML
	private ListView<String> notificationListView; 
	
	
	public void initialize() {
		notificationObList = FXCollections.observableArrayList();
		refreshList();
		notificationListView.setItems(notificationObList);
		
		notificationListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {     	
                String selectedNotification = notificationListView.getSelectionModel().getSelectedItem();
                if (selectedNotification != null) {
                    showNotificationDialog(selectedNotification);
                }
            }
        });
	}
	private void refreshList() {
		notificationList = NotificationManager.getNotifications(callerUserName);
		notificationObList.setAll(notificationList);
		System.out.println("Notification updated");
	}
	
	private void showNotificationDialog(String notification) {
		 
		Dialog<ButtonType> dialog = new Dialog<>();
	    dialog.setTitle("Details");
	    dialog.setHeaderText("Notification");

	    TextArea textArea = new TextArea(notification + "\ngenerated by System.");
	    textArea.setEditable(false);
	    textArea.setWrapText(true); 
	    textArea.setPrefSize(450, 300); 
	    
	    ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
	    ButtonType deleteButton = new ButtonType("Delete this record", ButtonBar.ButtonData.OTHER);
	    
	    dialog.getDialogPane().getButtonTypes().addAll(closeButton, deleteButton);
    	dialog.getDialogPane().setContent(textArea);

    	// Handle button actions
	    dialog.setResultConverter(button -> {

	    if(button == deleteButton) {
	    	try {
	        	NotificationManager.deleteNotification(callerUserName, notification);
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
}
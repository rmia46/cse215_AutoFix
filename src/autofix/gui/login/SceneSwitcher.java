package autofix.gui.login;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class SceneSwitcher {
	@FXML
	private Button loginLoaderButton, signupLoaderButton;
	
	@FXML
	private BorderPane mainBorderPane;
	
	public void initialize() {
		loginLoaderButton.setOnAction(e -> loadScene("Login.fxml"));
		signupLoaderButton.setOnAction(e -> loadScene("Signup.fxml"));
	}
	public void loadScene(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        try {
    		mainBorderPane.setCenter(loader.load());
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        
    }
}

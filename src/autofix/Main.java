package autofix;
	
import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import autofix.gui.application.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("gui/login/Window.fxml"));
			Scene scene = new Scene(root,700, 500);
			scene.getStylesheets().add(getClass().getResource("gui/stylesheets/application.css").toExternalForm());
			primaryStage.setMaxWidth(700);
	        primaryStage.setMaxHeight(550);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

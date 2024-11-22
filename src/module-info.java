module autofix.core {
	requires javafx.controls;
	
	opens autofix.core.people to javafx.graphics, javafx.fxml;
}

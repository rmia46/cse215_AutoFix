module AutoFix {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	
	opens autofix to javafx.graphics, javafx.fxml;
	opens autofix.gui.login to javafx.graphics, javafx.fxml;
	opens autofix.gui.application to javafx.graphics, javafx.fxml;
	exports autofix.gui.login;
	exports autofix.gui.application;
}

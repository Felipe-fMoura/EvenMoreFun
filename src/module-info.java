module evenMoreFun {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires jasypt;
	requires javafx.graphics;
    
    exports application;
    exports view;
    
    opens view to javafx.fxml;  // Permite que o FXMLLoader acesse os campos privados via reflection
}
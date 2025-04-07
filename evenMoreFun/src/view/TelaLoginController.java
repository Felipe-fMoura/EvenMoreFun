package view;
import service.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaLoginController {
	
	UsuarioService u = new UsuarioService();
	
	@FXML
	private TextField txtUsuarioLogin;
	
	@FXML
	private TextField txtSenhaLogin;
	
	@FXML
	private Button btnLogar;
	
	
	@FXML
	
	private void onBtnLogar() {
		
		String email = txtUsuarioLogin.getText();
		String senha = txtSenhaLogin.getText();
		
		
		if (u.fazerLogin(email, senha)) {
			System.out.println("Senha correta");
		} else {
			System.out.println("Senha incorreta");
		}
		
		
	}
	

}

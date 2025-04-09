package view;
import view.util.*;
import service.UsuarioService;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import service.UsuarioService;

public class TelaCadastroController {

	 private UsuarioService usuarioService = UsuarioService.getInstance();

	
	 
  @FXML
  private TextField txtNome;
  
  @FXML
  private TextField txtSobrenome;
  
  @FXML
  private TextField txtEmail;
	
  @FXML
  private TextField txtSenha;
  
  @FXML
  private TextField txtVerificarSenha;
  
  @FXML
  private DatePicker txtDataNascimento;
  
  @FXML
  private Button btnConfirma;
  
  @FXML
  private Button btListaUsuarios;
  
  
  @FXML
  private void onBtCadastrarUsuario(ActionEvent event) {
	  
	  		String nome = txtNome.getText();
	  		String sobrenome = txtSobrenome.getText();
			String email = txtEmail.getText();
			String senha = txtSenha.getText();	
			LocalDate dataNasc= txtDataNascimento.getValue();
			System.out.println(""+nome+" "+sobrenome+"\n"+email);
			
			Usuario novo = new Usuario(nome, sobrenome, email, senha, dataNasc);
			boolean emailValido = usuarioService.validarEmail(email);
			Alertas a = new Alertas();
			
			
			
			if(emailValido) {
				boolean sucesso = usuarioService.cadastrar(novo);

			if (sucesso){
				a.mostrarAlerta("Sucesso", "Usuário cadastrado com sucesso, Bem vindo!!");
				System.out.println("Usuario cadastrado, SENHA É " +senha);
				
				try {
					//Exibindo a outra tela
	                Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/TelaLogin.fxml"));
	                Scene loginScene = new Scene(loginRoot);
	                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	                stage.setScene(loginScene);
	                stage.show();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
				
				
			} else {
				System.out.println("Erro Email ja cadastrado"); //tirar
				a.mostrarAlerta("Erro de Cadastro", "Email já cadastrado. Tente novamente");
			}
			} else {
				
				
				a.mostrarAlerta("Erro de Cadastro", "Email inválido. Tente novamente");
				
			}
				
				
  }

  //tirar
  @FXML
  public void onBtListaUsuarios() {
	  
	  for (Usuario u : usuarioService.getUsuarios()) {
          System.out.println("Nome: " + u.getNome() + " | E-mail: " + u.getEmail()+ " | Senha: " + u.getSenha() + " | Data de nascimento: " + u.getDataNascimento());
	  }
  }
  
}

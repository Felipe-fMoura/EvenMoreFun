package view;
import view.util.*;
import service.UsuarioService;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Usuario;
import service.UsuarioService;

public class TelaCadastroController {

	 private UsuarioService usuarioService = new UsuarioService();
	 BasicPasswordEncryptor PasswordEncryptor = new BasicPasswordEncryptor();
	
	 
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
  private void onBtCadastrarUsuario() {
	  
	  		String nome = txtNome.getText();
	  		String sobrenome = txtSobrenome.getText();
			String email = txtEmail.getText();
			String senha= PasswordEncryptor.encryptPassword(txtSenha.getText());		
			LocalDate dataNasc= txtDataNascimento.getValue();
			System.out.println(""+nome+" "+sobrenome+"\n"+email);
			
			Usuario novo = new Usuario(nome, sobrenome, email, senha, dataNasc);
			boolean emailValido = usuarioService.validarEmail(email);
			Alertas a = new Alertas();
			
			
			
			if(emailValido) {
				boolean sucesso = usuarioService.cadastrar(novo);

			if (sucesso){
				a.mostrarAlerta("Sucesso", "Usuário cadastrado com sucesso, Bem vindo!!");
				System.out.println("Usuario cadastrado");
				
				
			} else {
				System.out.println("Erro Email ja cadastrado");
				a.mostrarAlerta("Erro de Cadastro", "Email já cadastrado. Tente novamente");
			}
			} else {
				
				
				a.mostrarAlerta("Erro de Cadastro", "Email inválido. Tente novamente");
				
			}
				
				
  }

  @FXML
  public void onBtListaUsuarios() {
	  
	  for (Usuario u : usuarioService.getUsuarios()) {
          System.out.println("Nome: " + u.getNome() + " | E-mail: " + u.getEmail()+ " | Senha: " + u.getSenha() + " | Data de nascimento: " + u.getDataNascimento());
	  }
  }
  
}

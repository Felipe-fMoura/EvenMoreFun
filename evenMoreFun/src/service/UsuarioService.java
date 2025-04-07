package service;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioService {

	
	private List<Usuario> listaUsuarios = new ArrayList<>();
	
	public boolean cadastrar(Usuario usuario) {
		for (Usuario u : listaUsuarios) {
			if (u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
				return false;
			}
		}
		listaUsuarios.add(usuario);
		return true;
	}
	
	public List<Usuario> getUsuarios(){
		return listaUsuarios;
	}
	
	public void listarUsuarios() {
	    if (listaUsuarios.isEmpty()) {
	        System.out.println("Nenhum usuário cadastrado.");
	        return;
	    }
	    
	    System.out.println("Lista de usuários cadastrados:");
	    for (Usuario u : listaUsuarios) {
	        System.out.println("Nome: " + u.getNome() + " | Email: " + u.getEmail());
	    }
	}

	public boolean validarEmail(String email) {
	    return email.contains("@") && email.endsWith(".com");//adicionar mais 
	}
	
	public boolean fazerLogin(String email, String senha) {
		for (Usuario u : listaUsuarios) {
			if (u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
 
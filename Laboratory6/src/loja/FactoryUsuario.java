package loja;

import excecoes.StringInvalidaException;
import usuario.Noob;
import usuario.Usuario;

public class FactoryUsuario {
	
	public Usuario criaUsuario(String nome, String login, String tipo) throws StringInvalidaException{
		Usuario noob = new Noob(nome, login);
		return noob;
	}

}

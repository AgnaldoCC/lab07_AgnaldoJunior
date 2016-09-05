// 115211093 - Agnaldo Souto Xavier Junior: Lab 7 - Turma 1

package loja;

import excecoes.StringInvalidaException;
import usuario.Usuario;

/**
 * 
 * @author Agnaldo Souto Xavier Junior
 *
 */

public class FactoryUsuario {
	
	public Usuario criaUsuario(String nome, String login, String tipo) throws StringInvalidaException{
		Usuario usr = new Usuario(nome, login);
		return usr;
	}

}

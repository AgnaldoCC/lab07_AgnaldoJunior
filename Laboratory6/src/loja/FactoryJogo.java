package loja;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import jogo.Jogabilidade;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.Rpg;

public class FactoryJogo {

	public Jogo criaJogo(String nome, double preco, String tipo, Set<Jogabilidade> jogabilidades)
			throws StringInvalidaException, PrecoInvalidoException {
		if (tipo.equalsIgnoreCase("Rpg")) {
			return new Rpg(nome, preco, jogabilidades);
		}

		else if (tipo.equalsIgnoreCase("Luta")) {
			return new Luta(nome, preco, jogabilidades);
		}

		else if (tipo.equalsIgnoreCase("Plataforma")) {
			return new Plataforma(nome, preco, jogabilidades);
		}

		return null;

	}

}

// 115211093 - Agnaldo Souto Xavier Junior: Lab 7 - Turma 1

package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;
import usuario.Usuario;

/**
 * 
 * @author Agnaldo Souto Xavier Junior
 *
 */

public class LojaController {
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private List<Usuario> meusUsuarios;
	private FactoryUsuario factoryUsuario;
	private FactoryJogo factoryJogo;
	private HashMap<String, Jogabilidade> mapJogabildades;

	/**
	 * Método responsável por criar o objeto LojaController, iniciando seus
	 * atributos.
	 */

	public LojaController() {
		this.meusUsuarios = new ArrayList<Usuario>();
		factoryUsuario = new FactoryUsuario();
		factoryJogo = new FactoryJogo();
		this.initializeMap();
	}

	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);
	}

	private Set<Jogabilidade> createJogabilidades(String names1) {
		Set<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();

		String[] listofNames = names1.split(" ");

		for (int i = 0; i < listofNames.length; i++) {
			String element = listofNames[i].toUpperCase();
			if (element != null) {
				Jogabilidade tipojogabilidade = mapJogabildades.get(element);
				jogabilidades.add(tipojogabilidade);
			}
		}

		return jogabilidades;

	}

	/**
	 * Método responsável por criar um usuário na loja.
	 * 
	 * @param nome
	 *            Nome do usuário a ser criado.
	 * @param login
	 *            Login do usuário a ser criado.
	 * @param tipo
	 *            Tipo do usuário a ser criado(Noob ou Veterano).
	 */

	public void criaUsuario(String nome, String login, String tipo) throws StringInvalidaException {
		meusUsuarios.add(factoryUsuario.criaUsuario(nome, login, tipo));
	}

	/**
	 * Método responsável por criar um jogo.
	 * 
	 * @param jogoNome
	 *            Nome do jogo a ser criado.
	 * @param preco
	 *            Preco do jogo a ser criado
	 * @param tipo
	 *            Tipo do jogo a ser criado(RPG, Luta ou Plataforma).
	 * @param jogabilidades
	 *            Conjunto com as jogabilidades do respectivo jogo.
	 * @return Retorna o jogo criado com os atributos dados.
	 */

	private Jogo criaJogo(String jogoNome, double preco, String tipo, Set<Jogabilidade> jogabilidades)
			throws StringInvalidaException, PrecoInvalidoException {
		return factoryJogo.criaJogo(jogoNome, preco, tipo, jogabilidades);
	}

	/**
	 * Método responsável por vender um jogo para um determinado usuário.
	 * 
	 * @param jogoNome
	 *            Nome do jogo a ser vendido.
	 * @param preco
	 *            Preco do jogo a ser vendido.
	 * @param estiloJogabilidade
	 *            Jogabilidade(s) que o jogo possui.
	 * @param estiloJogo
	 *            Estilo do jogo a ser criado(RPG, Luta ou Plataforma).
	 * @param loginUser
	 *            Login do usuário ao qual o jogo será vendido.
	 */

	public void vendeJogo(String jogoNome, double preco, String estiloJogabilidade, String estiloJogo, String loginUser)
			throws ValorInvalidoException, StringInvalidaException, PrecoInvalidoException {
		Usuario buscado = this.buscaUsuario(loginUser);
		Set<Jogabilidade> jogabilidades = createJogabilidades(estiloJogabilidade);
		Jogo jogoVendido = this.criaJogo(jogoNome, preco, estiloJogo, jogabilidades);
		buscado.compraJogo(jogoVendido);
	}

	/**
	 * Método responsável por punir um usuário.
	 * 
	 * @param login
	 *            Login do usuário que será punido.
	 * @param nomeJogo
	 *            Nome do jogo que foi jogado pelo usuário.
	 * @param score
	 *            Score obtido na jogada.
	 * @param venceu
	 *            Boolean indicando se o usuário zerou ou não o jogo.
	 */

	public void punir(String login, String nomeJogo, int score, boolean venceu)
			throws ValorInvalidoException, StringInvalidaException {
		Usuario usr = buscaUsuario(login);
		usr.punir(nomeJogo, score, venceu);
	}

	/**
	 * Método responsável por recompensar um usuário.
	 * 
	 * @param login
	 *            Login do usuário que será recompensar.
	 * @param nomeJogo
	 *            Nome do jogo que foi jogado pelo usuário.
	 * @param score
	 *            Score obtido na jogada.
	 * @param venceu
	 *            Boolean indicando se o usuário zerou ou não o jogo.
	 */

	public void recompensar(String login, String nomeJogo, int score, boolean venceu)
			throws ValorInvalidoException, StringInvalidaException {
		Usuario usr = buscaUsuario(login);
		usr.recompensar(nomeJogo, score, venceu);
	}

	/**
	 * Método responsável por adicionar dinheiro a um usuário.
	 * 
	 * @param login
	 *            Login do usuário que será creditado o dinheiro.
	 * @param credito
	 *            Quantidade de dinheiro que será adicionada ao usuário.
	 */

	public void adicionaCredito(String login, double credito) throws ValorInvalidoException, StringInvalidaException {
		if (credito < 0) {
			throw new ValorInvalidoException("Credito nao pode ser negativo");
		}
		Usuario user = this.buscaUsuario(login);
		user.setCredito(user.getCredito() + credito);

	}

	/**
	 * Método responsável por buscar um usuário na loja pelo seu login.
	 * 
	 * @param login
	 *            Login do usuário a ser buscado.
	 * @return Retorna o usuário se ele existir, caso contrário retorna Null.
	 * @throws StringInvalidaException
	 *             Lança exception caso o login seja vazio ou nulo.
	 */

	public Usuario buscaUsuario(String login) throws StringInvalidaException {
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio");
		}
		for (Usuario user : meusUsuarios) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;

	}

	/**
	 * Método responsável por tornar um usuário noob em veterano.
	 * 
	 * @param login
	 *            Login do usuário a ser upado.
	 */

	public void upgrade(String login) throws StringInvalidaException, UpgradeInvalidoException {
		Usuario usr = this.buscaUsuario(login);
		usr.upgrade();

	}

	/**
	 * Método responsável por tornar um usuário veterano em noob.
	 * 
	 * @param login
	 *            Login do usuário que será rebaixado.
	 */

	public void downgrade(String login) throws StringInvalidaException, UpgradeInvalidoException {
		Usuario usr = this.buscaUsuario(login);
		usr.downgrade();

	}

	/**
	 * Método responsável por mostrar o crédito de um determinado usuário
	 * cadastrado na loja.
	 * 
	 * @param login
	 *            Login do usuário a ser consultado.
	 * @return Retorna o crédito do usuário pesquisado.
	 */

	public double confereCredito(String login) throws StringInvalidaException {
		Usuario procurado = this.buscaUsuario(login);
		return procurado.getCredito();

	}

	public String informacaoUsuarios() {
		String myString = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for (int i = 0; i < meusUsuarios.size(); i++) {
			myString += meusUsuarios.get(i).toString() + FIM_DE_LINHA;
		}
		return myString;
	}

	/**
	 * Método responsável por mostrar a quantidade de X2P de um determinado
	 * usuário.
	 * 
	 * @param login
	 *            Login do usuário a ser pesquisado.
	 * @return Retorna o X2P do usuário pesquisado.
	 */

	public int getX2p(String login) throws StringInvalidaException {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getX2p();
	}

}

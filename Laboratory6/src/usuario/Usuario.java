// 115211093 - Agnaldo Souto Xavier Junior: Lab 7 - Turma 1

package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

/**
 * 
 * @author Agnaldo Souto Xavier Junior
 *
 */

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	protected Jogabilidade jogabilidades;

	private String login;
	private String nome;
	private Set<Jogo> meusJogos;
	private double credito;
	private int xp2;
	private TiposUsuarios status;

	/**
	 * Método responsável pela criação do objeto Usuario, com nome e login.
	 * 
	 * @param nome
	 *            Nome do usuario.
	 * @param login
	 *            Login do usuario
	 * @throws StringInvalidaException
	 *             Lança exception para caso o nome ou o login sejam nulos ou
	 *             vazios.
	 */

	public Usuario(String nome, String login) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		this.xp2 = 0;
		status = new Noob();
		meusJogos = new HashSet<Jogo>();
		this.credito = 0;
	}

	/**
	 * 
	 * @param jogo
	 *            Jogo recebido para ser comprado se possível.
	 * @throws ValorInvalidoException
	 *             Lança exception para caso o dinheiro não seja suficiente para
	 *             comprar o jogo.
	 */

	public void compraJogo(Jogo jogo) throws ValorInvalidoException {
		if (this.credito < jogo.getPreco()) {
			throw new ValorInvalidoException("Dinheiro insuficiente.");
		}
		xp2 += status.getX2p(jogo);
		this.credito -= status.compraJogo(jogo);
		meusJogos.add(jogo);
	}

	public void setX2p(int novoValor) {
		this.xp2 = novoValor;
	}

	public int getX2p() {
		return this.xp2;
	}

	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setCredito(double novoValor) {
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}

	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou)
			throws ValorInvalidoException, StringInvalidaException {
		Jogo jogo = buscaJogo(nomeJogo);
		xp2 += status.recompensar(jogo);
		xp2 += jogo.registraJogada(scoreObtido, zerou);
	}

	public void punir(String nomeJogo, int scoreObtido, boolean zerou)
			throws ValorInvalidoException, StringInvalidaException {
		Jogo jogo = buscaJogo(nomeJogo);
		xp2 += jogo.registraJogada(scoreObtido, zerou);
		xp2 -= status.punir(jogo);
	}

	/**
	 * 
	 * @param nomeJogo
	 *            Nome do jogo
	 * @return Retorna, se tiver, o jogo da lista com o nome passado como
	 *         parâmetro.
	 */

	public Jogo buscaJogo(String nomeJogo) {
		Jogo buscado = null;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			if (achado.getNome().equals(nomeJogo)) {
				buscado = achado;
			}
		}
		return buscado;
	}

	/**
	 * Método responsável por tornar um jogador noob em veterano.
	 * 
	 * @throws UpgradeInvalidoException
	 *             Lança exception para os seguintes casos: X2P menor do que
	 *             1000 ou se o usuário ja for veterano.
	 */

	public void upgrade() throws UpgradeInvalidoException {
		if (xp2 < 1000) {
			throw new UpgradeInvalidoException("Quantidade de x2p insuficiente.");
		}
		if (status instanceof Veterano) {
			throw new UpgradeInvalidoException("Usuario ja e veterano.");
		}
		this.status = new Veterano();
	}

	/**
	 * Método responsável por tornar um jogador veterano em noob.
	 * 
	 * @throws UpgradeInvalidoException
	 *             Lança exception para os seguintes caso o usuário ja for noob.
	 */

	public void downgrade() throws UpgradeInvalidoException {
		if (status instanceof Noob) {
			throw new UpgradeInvalidoException("O usuario ja e Noob.");
		}
		this.status = new Noob();
	}

	public TiposUsuarios getStatusDoUsuario() {
		return status;
	}

	public void setStatusDoUsuario(TiposUsuarios statusDoUsuario) {
		this.status = statusDoUsuario;
	}

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

	/**
	 * Calcula o preco total dos jogos adquiridos.
	 * 
	 * @return Retorna o preco total.
	 */

	public double calculaPrecoTotal() {
		double total = 0;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			total += achado.getPreco();
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());
		} else {
			return false;
		}
	}
}

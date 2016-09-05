// 115211093 - Agnaldo Souto Xavier Junior: Lab 7 - Turma 1 

package jogo;

import java.util.HashSet;
import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * 
 * @author Agnaldo Souto Xavier Junior
 *
 */

public abstract class Jogo {
	
	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private double preco;
	private int vezesJogadas;
	private int vezesConcluidas;
	private int maiorScore;
	Set<Jogabilidade> jogabilidades;
	
	/**
	 * Método responsável pela criação do jogo, com nome e preco.
	 * @param nome
	 * Nome do jogo.
	 * @param preco
	 * Preco do jogo.
	 * @throws StringInvalidaException
	 * Lança exception para caso o nome do jogo seja nulo ou vazio.
	 * @throws PrecoInvalidoException
	 * Lança exception para caso o preço do jogo ser menor do que zero.
	 */

	public Jogo(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		jogabilidades = new HashSet<Jogabilidade>();
	}
	
	/**
	 * Método responsável pea criação do jogo, com nome, preco, e um conjunto de jogabilidade(s).
	 * @param nome
	 * Nome do jogo.
	 * @param preco
	 * Preco do jogo.
	 * @param jogabilidades
	 * Conjunto com as jogabilidades do jogo.
	 * @throws StringInvalidaException
	 * Lança exception para caso o nome do jogo seja nulo ou vazio.
	 * @throws PrecoInvalidoException
	 * Lança exception para caso o preço do jogo ser menor do que zero.
	 */

	public Jogo(String nome, double preco, Set<Jogabilidade> jogabilidades)
			throws StringInvalidaException, PrecoInvalidoException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		this.jogabilidades = jogabilidades;
	}

	public abstract int registraJogada(int score, boolean venceu) throws ValorInvalidoException;

	public double getPreco() {
		return this.preco;
	}

	public String getNome() {
		return this.nome;
	}

	public int getMaiorScore() {
		return this.maiorScore;
	}

	public void setMaiorScore(int novoScore) {
		this.maiorScore = novoScore;
	}

	public int getvezesConcluidas() {
		return this.vezesConcluidas;
	}

	public void setVezesConcluidas(int novaQuantidade) {
		this.vezesConcluidas = novaQuantidade;
	}

	public int getVezesJogadas() {
		return this.vezesJogadas;
	}

	public void setVezesJogadas(int novaQuantidade) {
		this.vezesJogadas = novaQuantidade;
	}
	
	public void adicionaJogabilidade(Jogabilidade jogabilidade){
		jogabilidades.add(jogabilidade);
	}
	
	public Set<Jogabilidade> getJogabilidades(){
		return this.jogabilidades;
	}

	@Override
	public String toString() {
		String resultado = "==> Jogou " + getVezesJogadas() + " vez(es)" + FIM_DE_LINHA;
		resultado += "==> Zerou " + getvezesConcluidas() + " vez(es)" + FIM_DE_LINHA;
		resultado += "==> Maior Score: " + getMaiorScore() + FIM_DE_LINHA;
		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Jogo) {
			Jogo temp = (Jogo) obj;

			return this.getNome().equals(temp.getNome()) && this.getPreco() == temp.getPreco();

		} else {
			return false;
		}

	}
}

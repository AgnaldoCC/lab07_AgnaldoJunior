// 115211093 - Agnaldo Souto Xavier Junior: Lab 7 - Turma 1

package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * 
 * @author Agnaldo Souto Xavier Junior
 *
 */

public class Plataforma extends Jogo {
	public final static int MAXIMUM_SCORE = 100000;
	public final static int TAXA_XP2 = 20;
	
	/**
	 * Método responsável pela criação do jogo Plataforma, com nome e preco,além de ser subclasse de Jogo.
	 * @param nome
	 * Nome do jogo.
	 * @param preco
	 * Preco do jogo
	 * @throws StringInvalidaException
	 * Lança exception para caso o nome do jogo seja nulo ou vazio.
	 * @throws PrecoInvalidoException
	 * Lança exception para caso o preço do jogo ser menor do que zero.
	 */
	
	public Plataforma(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco);
	}
	
	/**
	 * Método responsável pela criação do jogo Plataforma, com nome, preco e um conjunto com jogabilidades, além de ser subclasse de Jogo.
	 * @param nome
	 * Nome do jogo.
	 * @param preco
	 * Preco do jogo
	 * @param jogabilidades
	 * Conjunto com jogabilidades.
	 * @throws StringInvalidaException
	 * Lança exception para caso o nome do jogo seja nulo ou vazio.
	 * @throws PrecoInvalidoException
	 * Lança exception para caso o preço do jogo ser menor do que zero.
	 */

	public Plataforma(String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco, jogabilidades);
	}

	/**
	 *Método responsável por registrar uma jogada.
	 *@param score
	 *Score adquirido na jogada.
	 *@param venceu
	 *Boolean indicando se o jogo foi zerado ou não.
	 * @throws ValorInvalidoException
	 * Lança exception para caso o score seja menor do que 0.
	 */
	
	public int registraJogada(int score, boolean venceu) throws ValorInvalidoException{
		if (score < 0){
			throw new ValorInvalidoException("Score não pode ser menor do que zero.");
		}
		setVezesJogadas(getVezesJogadas() + 1);
		if (score > this.getMaiorScore()) {
			setMaiorScore(score);
		} else if (score > MAXIMUM_SCORE) {
			setMaiorScore(MAXIMUM_SCORE);
		}
		if (venceu) {
			setVezesConcluidas(getvezesConcluidas() + 1);
			return TAXA_XP2;
		}
		return 0;
	}

	public String toString() {
		String resultado = getNome() + " - Plataforma:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}

}

// 115211093 - Agnaldo Souto Xavier Junior: Lab 7 - Turma 1

package usuario;

import jogo.Jogabilidade;
import jogo.Jogo;

/**
 * 
 * @author Agnaldo Souto Xavier Junior
 *
 */

public class Veterano implements TiposUsuarios {
	public static final double DESCONTO_VETERANO = 0.8;

	/**
	 * Método que compra o jogo
	 * 
	 * @param Jogo
	 *            Jogo a ser comprado
	 * @return Retorna o preço do jogo com desconto
	 * 
	 */

	public double compraJogo(Jogo jogo) {
		double desconto = jogo.getPreco() - (jogo.getPreco() * DESCONTO_VETERANO);
		return desconto;
	}

	/**
	 * Método que retorna o bônus de X2p para o usuário noob.
	 * 
	 * @param jogo
	 *            Jogo cujo preço será multiplicado por 10 para dar o bônus do
	 *            jogador noob.
	 */

	public int getX2p(Jogo jogo) {
		return (int) (jogo.getPreco() * 15);
	}

	/**
	 * Método que recompensa o usuário dependendo dos estilos de jogabilidade
	 * dos jogos que ele pertence.
	 * 
	 * @param jogo
	 *            Jogo cujos estilos serão verificados para recompensar o
	 *            jogador.
	 * @return Retorna a recompensa adquirida pelo jogador.
	 */

	public int recompensar(Jogo jogo) {
		int recompensa = 0;
		if (jogo.getJogabilidades().contains(Jogabilidade.ONLINE)) {
			recompensa += 10;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)) {
			recompensa += 20;
		}
		return recompensa;
	}

	/**
	 * Método que puni o usuário pelo pelos estilos dos jogos que ele possui.
	 * 
	 * @param jogo
	 *            Jogo cujos estilos serão verificados para punir o jogador.
	 * @return Retorna a punição do jogador.
	 */

	public int punir(Jogo jogo) {
		int recompensa = 0;
		if (jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)) {
			recompensa += 20;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)) {
			recompensa += 20;
		}
		return recompensa;

	}

	public String toString() {
		String myString = "Jogador Veterano: ";
		return myString;
	}

}

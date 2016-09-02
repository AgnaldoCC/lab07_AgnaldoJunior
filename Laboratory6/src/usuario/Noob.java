package usuario;

import java.util.Iterator;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Noob extends Usuario {
	public static final double DESCONTO_NOOB = 0.9;

	public Noob(String nome, String login) throws StringInvalidaException {
		super(nome, login);
		setX2p(0);
	}

	@Override
	public void compraJogo(Jogo jogo) throws Exception {
		double custo = jogo.getPreco() * DESCONTO_NOOB;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira = (int) (jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp = parteInteira * 10;
			setX2p(getX2p() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}

	}

	@Override
	public String toString() {
		String myString = "Jogador Noob: " + this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - " + getX2p() + " X2P" + FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;
		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de preco dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		return myString;
	}

	@Override
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = buscaJogo(nomeJogo);
		for (Jogabilidade j : jogo.getJogabilidades()) {
			if (j == Jogabilidade.OFFLINE) {
				setX2p(getX2p() + 30);
			}
			if (j == Jogabilidade.MULTIPLAYER) {
				setX2p(getX2p() + 10);
			}
		}
		setX2p(getX2p() + jogo.registraJogada(scoreObtido, zerou));
	}

	@Override
	public void punir(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = buscaJogo(nomeJogo);
		for (Jogabilidade j : jogo.getJogabilidades()) {
			if (j == Jogabilidade.ONLINE) {
				setX2p(getX2p() - 10);
			}
			if (j == Jogabilidade.COMPETITIVO) {
				setX2p(getX2p() - 20);
			}
			if (j == Jogabilidade.COOPERATIVO) {
				setX2p(getX2p() - 50);
			}
		}
		setX2p(getX2p() + jogo.registraJogada(scoreObtido, zerou));
	}
}
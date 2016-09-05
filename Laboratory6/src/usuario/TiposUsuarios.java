// 115211093 - Agnaldo Souto Xavier Junior: Lab 7 - Turma 1

package usuario;

import excecoes.ValorInvalidoException;
import jogo.Jogo;

/**
 * 
 * @author Agnaldo Souto Xavier Junior
 *
 */

public interface TiposUsuarios {
	
	double compraJogo(Jogo jogo)throws ValorInvalidoException;
	
	int punir(Jogo nomeJogo);
	
	int recompensar(Jogo nomeJogo);
	
	int getX2p(Jogo jogo);

}

// 115211093 - Agnaldo Souto Xavier Junior: Lab 7 - Turma 1

package loja;

import easyaccept.EasyAccept;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;

/**
 * 
 * @author Agnaldo Souto Xavier Junior
 *
 */

public class LojaFacade {

	private LojaController loja;
	
	/**
	 * Método responsável por criar o objeto LojaFacade e iniciar seus atributos.
	 */
	
	public LojaFacade() {
		this.loja = new LojaController();
	}
	
	/**
	 * Método com a mesma função do criaUsuario de LojaController e responsável pela captura dos exceptions.
	 * @param nome
	 * @param login
	 * @param tipo
	 * @throws StringInvalidaException
	 */
	
	public void criaUsuario(String nome, String login, String tipo) throws StringInvalidaException {
		try{
			loja.criaUsuario(nome, login, tipo);
		}catch(StringInvalidaException ex){		
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Método com a mesma função do confereCredito de LojaController e responsável pela captura dos exceptions.
	 * @param login
	 * @return
	 * @throws StringInvalidaException
	 */
	
	public double confereCredito(String login) throws StringInvalidaException{
		return loja.confereCredito(login);
	}
	
	/**
	 * Método com a mesma função do vendeJogo de LojaController e responsável pela captura dos exceptions.
	 * @param jogoNome
	 * @param preco
	 * @param jogabilidades
	 * @param estiloJogo
	 * @param loginUser
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) throws StringInvalidaException, PrecoInvalidoException{
		try{
			loja.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
		}catch(StringInvalidaException ex){
			System.out.println(ex.getMessage());
		}catch(PrecoInvalidoException ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	/**
	 * Método com a mesma função do adicionaCrédito de LojaController e responsável pela captura dos exceptions.
	 * @param login
	 * @param credito
	 * @throws StringInvalidaException
	 * @throws ValorInvalidoException
	 */
	
	public void adicionaCredito(String login, double credito) throws StringInvalidaException, ValorInvalidoException{
		try{
			loja.adicionaCredito(login, credito);
		}catch(ValorInvalidoException ex){
			System.out.println(ex.getMessage());
		}catch(StringInvalidaException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Método com a mesma função do upgrade de LojaController e responsável pela captura dos exceptions.
	 * @param login
	 * @throws StringInvalidaException
	 * @throws UpgradeInvalidoException
	 */
	
	public void upgrade(String login) throws StringInvalidaException, UpgradeInvalidoException  {
		try{
			loja.upgrade(login);
		}catch(StringInvalidaException ex){
			System.out.println(ex.getMessage());
		}catch(UpgradeInvalidoException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Método com a mesma função do downgrade de LojaController e responsável pela captura dos exceptions.
	 * @param login
	 * @throws StringInvalidaException
	 * @throws UpgradeInvalidoException
	 */
	
	public void downgrade(String login) throws StringInvalidaException, UpgradeInvalidoException{
		try{
			loja.downgrade(login);
		}catch(StringInvalidaException ex){
			System.out.println(ex.getMessage());
		}catch(UpgradeInvalidoException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Método com a mesma função do getX2p de LojaController e responsável pela captura dos exceptions.
	 * @param login
	 * @return
	 * @throws StringInvalidaException
	 */
	
	public int getX2p(String login) throws StringInvalidaException{
		try{
			return loja.getX2p(login);
		}catch(StringInvalidaException ex){
			System.out.println(ex.getMessage());
		}
		throw new StringInvalidaException("Nome invalido");
	}
	
	/**
	 * Método com a mesma função do punir de LojaController e responsável pela captura dos exceptions.
	 * @param login
	 * @param nome
	 * @param score
	 * @param zerou
	 * @throws ValorInvalidoException
	 * @throws StringInvalidaException
	 */
	
	public void punir(String login, String nome, int score, boolean zerou) throws ValorInvalidoException, StringInvalidaException{
		try{
			loja.punir(login, nome, score, zerou);
		}catch(ValorInvalidoException ex){
			System.out.println(ex.getMessage());
		}catch(StringInvalidaException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Método com a mesma função do recompensar de LojaController e responsável pela captura dos exceptions.
	 * @param login
	 * @param nome
	 * @param score
	 * @param zerou
	 * @throws ValorInvalidoException
	 * @throws StringInvalidaException
	 */
	
	public void recompensar(String login, String nome, int score, boolean zerou) throws ValorInvalidoException, StringInvalidaException{
		try{
			loja.recompensar(login, nome, score, zerou);
		}catch(StringInvalidaException ex){
			System.out.println(ex.getMessage());
		}catch(ValorInvalidoException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public String informacaoUsuarios(){
		return loja.informacaoUsuarios();
	}
	
	public static void main(String[] args) {
		args = new String[] { "loja.LojaFacade", "acceptance_test/us1.txt", "acceptance_test/us2.txt",
				"acceptance_test/us3.txt" };
		System.out.println();
		EasyAccept.main(args);
	}

}

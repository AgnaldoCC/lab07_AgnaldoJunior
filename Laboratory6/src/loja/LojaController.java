package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.Rpg;
import usuario.Usuario;
import usuario.Veterano;

public class LojaController {
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private List<Usuario> meusUsuarios;
	private FactoryUsuario factoryUsuario;
	private FactoryJogo factoryJogo;
	private HashMap<String, Jogabilidade> mapJogabildades;

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

	public void criaUsuario(String nome, String login, String tipo) throws StringInvalidaException {
		meusUsuarios.add(factoryUsuario.criaUsuario(nome, login, tipo)); 
	}

	private Jogo criaJogo(String jogoNome, double preco, String tipo, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		return factoryJogo.criaJogo(jogoNome, preco, tipo , jogabilidades);
	}

	public void vendeJogo(String jogoNome, double preco, String estiloJogabilidade, String estiloJogo, String loginUser) {

		try {
			Usuario buscado = this.buscaUsuario(loginUser);
			Set<Jogabilidade> jogabilidades = createJogabilidades(estiloJogabilidade);
			Jogo jogoVendido = this.criaJogo(jogoNome, preco, estiloJogo, jogabilidades);
			buscado.compraJogo(jogoVendido);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void punir(String login, String nomeJogo, int score, boolean venceu){
		Usuario usr = buscaUsuario(login);
		usr.punir(nomeJogo, score, venceu);
	}
	
	public void recompensar(String login, String nomeJogo, int score, boolean venceu){
		Usuario usr = buscaUsuario(login);
		usr.recompensar(nomeJogo, score, venceu);
	}

	public void adicionaCredito(String login, double credito) {
		try {
			if (credito < 0) {
				throw new ValorInvalidoException("Credito nao pode ser negativo");
			}
			Usuario user = this.buscaUsuario(login);
			user.setCredito(user.getCredito() + credito);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Usuario buscaUsuario(String login) {
		Usuario buscado = null;

		try {
			for (int i = 0; i < meusUsuarios.size(); i++) {
				if (meusUsuarios.get(i).getLogin().equals(login)) {
					buscado = meusUsuarios.get(i);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return buscado;
	}

	public void upgrade(String login) throws Exception {
		Usuario antigo = this.buscaUsuario(login);
		if (antigo instanceof Veterano) {
			throw new UpgradeInvalidoException("Upgrade impossivel de ser realizado, usuario ja eh veterano");
		} else if (antigo.getX2p() < 1000) {
			throw new UpgradeInvalidoException("Impossivel realizar upgrade, quantidade de x2p insuficiente!");
		}
		Usuario novo = new Veterano(antigo.getNome(), antigo.getLogin());
		novo.setCredito(antigo.getCredito());
		novo.setX2p(antigo.getX2p());
		novo.setMeusJogos(antigo.getMeusJogos());
		int index = meusUsuarios.indexOf(antigo);
		meusUsuarios.set(index, novo);

	}

	public double confereCredito(String login) {
		try {
			Usuario procurado = this.buscaUsuario(login);
			return procurado.getCredito();
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	public String informacaoUsuarios() {
		String myString = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for (int i = 0; i < meusUsuarios.size(); i++) {
			myString += meusUsuarios.get(i).toString() + FIM_DE_LINHA;
		}
		return myString;
	}

	public int getX2p(String login) {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getX2p();
	}

	public static void main(String[] args) {
		args = new String[] { "loja.LojaController", "acceptance_test/us1.txt", "acceptance_test/us2.txt",
				"acceptance_test/us3.txt" };
		EasyAccept.main(args);

	}

}

package servidor;


/**
 * @author Murakami
 * @version 1.0
 * @created 19-mai-2016 21:09:29
 */

public abstract class Jogador {

    private StatusJogador _meustatus;
	private boolean ativo;
	private String nome;

	public Jogador(String username) {
		nome = username;
		ativo = false;
		_meustatus = StatusJogador.JOGANDO;
	}

	public void setMeuStatus (StatusJogador st){
		_meustatus = st;
	}
	
	public void setAtivo(boolean b){
		this.ativo = b;
	}
	
	
	

public void definirAtivo(){
this.ativo = true;
	}

	public void finalize() throws Throwable {

	}
}//end Jogador
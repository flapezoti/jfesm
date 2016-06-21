package cliente.TelaMediadores;



/**
 * implementação da classe Atualizador Tela
 * Objetivo principal é comunicar com a tela e a tualizar os
 * valores de acordo com a lógica do modelo.

* OBS.: Deve armazenar as imagens da Forca que serão atualizadas de acordo 
* com as mudanças na tela
* 

 * @author Murakami
 * @version 1.0
 * @created 19-mai-2016 21:09:29

**/

import java.util.ArrayList;
import javax.swing.ImageIcon;

import cliente.TelaMediadores.Tela.Tela;



public class AtualizadorTela{

	private ImageIcon fotoForca;
	private String Palavra1;
	private String Palavra2;
	private String JogadorAtivo;
	
      
       
        private ArrayList<ImageIcon> ImagensDaForca;
	
    public Tela _tela;
    private String MensagemErroInput;
    private String MsgJogador;
    private String TextoStatus;
    private String listaLetrasErradas;
    private String listaPalavrasErradas;
 
        
        
//contrutor
	public AtualizadorTela(Tela _tela){
            
            this._tela = _tela;
            
            InicilizarMensagens();
            listaLetrasErradas = "";
            listaPalavrasErradas = "";
            ImagensDaForca = new ArrayList<ImageIcon>();
            inicializarArrayListImagens();
            this.fotoForca = ImagensDaForca.get(0); 
            _tela.atualizarImagemForca(this.fotoForca);
            
             
	}
        
        
        //Mensagem de início de jogo
        private void InicilizarMensagens() {
        
        this.MensagemErroInput = "Faltam alguma coisa para iniciar o Jogo";
        
        
        
        }

        
//comandos Set
        
        
   //atualiza o campo da palavra 1
    public void setPalavra1(String Palavra1) {
        
        this.Palavra1 = Palavra1;
    }
//atualiza o campo da palavra 2
    public void setPalavra2(String Palavra2) {
        this.Palavra2 = Palavra2;
    }
    
//atualiza o texto para o jogador
    public void MensagemJogadorAtivo(String nomeJogador) {
        this.MsgJogador = "É a vez de " + nomeJogador;
        this.TextoStatus = "Escolha sua Jogada:";
        this.JogadorAtivo=nomeJogador;
        _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
        _tela.mostrarJogadorAtivo(nomeJogador);
        
    }

    public void setTextoStatus(String TextoStatus) {
        this.TextoStatus = TextoStatus;
    }

        //fim dos comandos Set
    
    
/*
    Início dos métodos de envio de mensagens para a tela.
    
    */
    
    
    
    
	/**
	 * 
	 * @param partesForca
         * @finalizado
	 */
	public void atualizarImagemForca(int partesForca){
            
            switch(partesForca){
                case 1:
                    this.fotoForca = ImagensDaForca.get(1);
                    
                    break;
                case 2:
                    this.fotoForca = ImagensDaForca.get(2);
                    break;
                case 3:
                    this.fotoForca = ImagensDaForca.get(3);
                    break;
                case 4:
                    this.fotoForca = ImagensDaForca.get(4);
                    break;
                case 5:
                    this.fotoForca = ImagensDaForca.get(5);
                    break;
            }
            _tela.atualizarImagemForca(this.fotoForca);
	}
        
        /*
        Mensagens Para a tela
        */

	public void atualizarJogadorAtivo(){
           // this.MsgJogador
            _tela.mostrarJogadorAtivo(this.JogadorAtivo);

	}

	public void atualizarListaPalpites(String palavrasUsadas){
			listaPalavrasErradas = listaPalavrasErradas + " " + palavrasUsadas;
            String Palpites= "PALPITES ERRADOS:" + listaPalavrasErradas ;
            
            _tela.atualizarPalpites(Palpites);
	}
    public void atualizarLetrasErradas(String listaDeLetrasUsadas) {
        String letrasErradas = "LETRAS ERRADAS: " + listaLetrasErradas + " " + listaDeLetrasUsadas;
        listaLetrasErradas = listaLetrasErradas + " " + listaDeLetrasUsadas;
        _tela.atualizarListaLetrasUsadas(letrasErradas);
    
    }
	public void atualizarPalavrasNaTela(String VisualPalavra1, String VisualPalavra2){
            
            _tela.atualizarCampoPalavras(VisualPalavra1, VisualPalavra2);

	}

	public void atualizarTextoTela(){
		_tela.atualizarTextoStatus(TextoStatus, MsgJogador);
	}

  public   void mensagemErrodeInicio() {
                _tela.atualizarTextoStatus(MensagemErroInput, " ");
    }

    
   

    /*
    Início das mensagens de texto para o painel.
    */
  


  public   void mensagemAcertouPalavras(String JogadorAtivo) {
        mensagemGanhador();
        this.MsgJogador = JogadorAtivo + " venceu o Jogo!";
        _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
    }

 public   void mensagemAcertouLetra() {
       
    this.TextoStatus = "Você acertou uma letra!";    
    
    _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
    }

   


  public   void mensagemErrouLetra() {
        this.TextoStatus = "Letra errada!";
        _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
    }
 public     void mensagemErrouPalavras() {
        this.TextoStatus = "Você errou o palpite! ";  
        _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
    }

    
public void mensagemGanhador() {
      this.TextoStatus = "Você ganhou o Jogo!!";
      _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
              }
 
 public    void mensagemPerdedor(String nomeJogador) {
        this.TextoStatus = "Você perdeu! :(";
        _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
        _tela.mostrarVermelho(nomeJogador);
      
    }
  public   void FimDoJogo() {
         _tela.desabilitaBotao();
    this.TextoStatus = "Fim do jogo! :)";    
    _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
    
    }

   public void mensagemTodosPerdedores() {
        _tela.desabilitaBotao();
        this.MsgJogador = "Enforcados! :(";
    this.TextoStatus = "Todos perderam.";
    _tela.atualizarTextoStatus(TextoStatus, MsgJogador);
    }


    
 /*
    inicalização das Imagens
    @OK
    */
    private void inicializarArrayListImagens() {  
        for(int i = 0; i <=5; i++){
            ImageIcon imageName = new ImageIcon("X:\\CS_JFesm\\CS_JFesm\\src\\Imagens\\HangMan" + i +".png");
            ImagensDaForca.add(imageName);
        }
    }

    public void MensagemErroInputLetra() {
    String t = "Digite outra Letra";
    _tela.atualizarTextoStatus(t, this.MsgJogador);
    }

   
    

        
}//end AtualizadorTela
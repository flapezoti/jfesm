package servidor;



import java.util.ArrayList;
import java.util.LinkedList;



/**
 * @author Murakami
 * @version 1.0
 * @created 19-mai-2016 21:09:35
 *
 * Ã© responsabilidade do conector instanciar JFesm apenas se o jogoEstiver com
 * todos os parÃ¢metros definidos. Se nÃ£o, deve enviar mensagem de volta para a
 * tela nÃ£o esquecer que o conector tb Ã© parte do modelo da aplicaÃ§Ã£o pois nele
 * estaria, inclusive implementado a persistÃªncia.
 */
public class Conector {

    public JFesm m_JFesm;
   
    //atributos de Jogo
    private String IDJogo;
    private String JogadorAtivo;
    private String StatusJogador;
    private boolean JogoPronto;
    private LinkedList<String> ListaDeJogadores;
    private String[] PalavrasSecretas;
    private String[] PoolJogadores;
    private String[] PalavrasPalpitadas;
    private String listaDeLetrasUsadas;
    private String listaPalavrasErradas;
    private boolean fimDojogo;
    private String[] PalavrasSecretasAtualizadas;
    private String[] palavrasSecretasComPontos;
    private int partesForca;
    private String Ganhador;
    private String url;

    
    JdbcMySQL_User db;
    /*MÃ©todo Construtor*/
    public Conector() {
        this.m_JFesm = null;

        this.IDJogo = null;
        this.JogoPronto = false;
        this.ListaDeJogadores = new LinkedList();
        this.PalavrasSecretas = new String[2];
        this.fimDojogo = false;
        this.PalavrasSecretasAtualizadas = null;
        iniciarDB();
        JogoPronto();

    }

    private void iniciarDB(){
        String[] Palavras = { "Otimo", "Trabalho", "Laboratorio", "Computacao", "Celular", "Mouse", "Cafe", "Girafa"};
        
        this.url = "jdbc:mysql://localhost:3306/";
        db = new JdbcMySQL_User(url);
        db.criar("PALAVRAS", "ID int, PALAVRA varchar(15)");
        for ( int i = 0; i < 8; i++){
            db.gravar( i, Palavras[i]);
        }
    }
    
    /*
    O mÃ©todo Jogo pronto Ã© ativado quando O Jogador A decide que o jogo estÃ¡ pronto
     */
    public void JogoPronto() {
        //FalsificarModelo falsificarÃ¡ a passagem de parÃ¢metros e integraÃ§Ã£o de vÃ¡rios usuÃ¡rios.
        falsificarModelo();
        //Fim da FalsificaÃ§Ã£o
        this.m_JFesm = new JFesm(this.PoolJogadores, PalavrasSecretas);
        this.PalavrasSecretasAtualizadas = m_JFesm.getVisualPalavras();
        
        this.JogadorAtivo = m_JFesm.ProximoJogador();
       
        
        
    }

    /*
    //Inicio dos mÃ©todos de comunicaÃ§Ã£o com o JFESM
    */
    //envia char para jfesm, pergunta se acertou a letra
    public String enviarLetraSugerida(char letraSugerida) {

    	String msg;
        m_JFesm.receberLetra(letraSugerida);
        if(!m_JFesm.JogadaInvalida){
        	if (m_JFesm.acertouLetra()){
        		atualizarConector();
        		if (m_JFesm.JogoTerminou()){
        			 msg = "2 mensagemAcertouLetra " + JogadorAtivo + " atualizarPalavrasNaTela " + this.palavrasSecretasComPontos[0] + " " + this.palavrasSecretasComPontos[1] + " " + "FimDoJogo";
        			 
        		} else {
        			msg = "1 mensagemAcertouLetra atualizarPalavrasNaTela " + this.palavrasSecretasComPontos[0] + " " + this.palavrasSecretasComPontos[1] + " " + JogadorAtivo + " " + "MensagemJogadorAtivo";
        		}
           	} else {
        		atualizarConector();
        		if (m_JFesm.JogoTerminou()){
        			msg = "8 atualizarLetrasErradas " + letraSugerida + " atualizarImagemForca " + this.partesForca + " FimDoJogo";
       			 
        		} else {
        			msg = "3 atualizarLetrasErradas " + letraSugerida + " atualizarImagemForca " + this.partesForca + " MensagemJogadorAtivo " + JogadorAtivo;
        		}
              
        	}        	
        
        }else{
            msg = "4 MensagemErroInputLetra";
        }
        return msg;
    }

    //o mÃ©todo conecta a escolha do usuÃ¡rio com o modelo.
    //Deve enviar para o Jfesm o string das Palavras palpitadas.
    
    public String enviarPalpites(String[] palavrasPalpitadas) {
    	String msg = "";
        this.PalavrasPalpitadas = palavrasPalpitadas;
        
        m_JFesm.receberPalavras(palavrasPalpitadas[0], palavrasPalpitadas[1]);
        if(!m_JFesm.JogadaInvalida){
        	if (m_JFesm.acertouPalavras()) {
	            atualizarConector(); //estï¿½ sorteando o proximo jogador ativo, o nome do vencedor estarï¿½ errado
	            msg = "5 mensagemAcertouPalavras " + JogadorAtivo + " AtualizarPalavranaTela " + this.palavrasSecretasComPontos[0] + " " + this.palavrasSecretasComPontos[1] + " mensagem Ganhador" + " FimdoJogo";
	            

        	} else {
	                
	
	            if (m_JFesm.JogoTerminou()) {
	
	                msg = "7 mensagemErrouPalavras mensagemPerdedor atualizarListaPalpites " + palavrasPalpitadas[0] + " " + palavrasPalpitadas[1] + " mensagemTodosPerdedores atualizarImagemForca";
	
	            }else{
	               atualizarConector();
	            	msg = "6 mensagemErrouPalavras mensagemPerdedor atualizarListaPalpites " + palavrasPalpitadas[0] + " " + palavrasPalpitadas[1] + " MensagemJogadorAtivo " + JogadorAtivo;
	             
	            }
	
	        }
	    }
        return msg;
    }

    
    
    

    /*Inicio dos mÃ©todos de Input inicial*/
 /*
    MÃ©todo receberNomeJogador deve adicionar cada jogador que fizer o log in na lista de jogadores.
     */
    public void receberNomeJogador(String Jogador) {
        ListaDeJogadores.add(Jogador);

    }

    /*
    setPalavraSecreta recebe uma String da interface do jogador A e B para formar as palavras Secretas
    @REVER, nÃ£o Ã© usado, lÃ³gica incorreta.
     */
    public void setPalavraSecreta(String Palavra, char Jogador) {
        if(Jogador == 'A'){
            this.PalavrasSecretas[0] = Palavra;
        }
       if(Jogador == 'B'){
             this.PalavrasSecretas[1] = Palavra;
        }
       
    }

    /*Fim dos mÃ©todos de Input inicial*/
 /*
      MÃ©todos para iniciar o Jogo
     
    *Ao receber a mensagem de inÃ­cio de jogo da tela do jogador 1, o conector deve analizar
    *se as informaÃ§Ãµes necessÃ¡rias para a instanciaÃ§Ã£o do jfesm estÃ£o definidas.
     
     */
    
    public String iniciarJogo() {
    	String msg = "";
        verificarJogo();
        
        if (JogoPronto) {
            criarPool();
            //m_JFesm = new JFesm(PoolJogadores, PalavrasSecretas);
            atualizarConector();
            
            msg = "0 " + this.palavrasSecretasComPontos[0] + " " + this.palavrasSecretasComPontos[1] + " " + this.PoolJogadores[0] + " " + this.PoolJogadores[1] + " " + this.PoolJogadores[2] + " " + this.PoolJogadores[3] + " " + this.PoolJogadores[4] + " " + JogadorAtivo;  
            
        } 
        return msg;
    }
    
//verifica se os prÃ© requisitos para a criaÃ§Ã£o do jfesm foi atendido

    private void verificarJogo() {
        if (IDJogo != null && ListaDeJogadores.size() >= 3) {
            if (PalavrasSecretas[0] != null && PalavrasSecretas[1] != null) {
                this.JogoPronto = true;

            }
        }

    }

    //modifica a lista ligada para um array de String
    private void criarPool() {
        this.PoolJogadores = new String[this.ListaDeJogadores.size()];

        for (int i = 0; i < PoolJogadores.length; i++) {
            PoolJogadores[i] = ListaDeJogadores.get(i);
        }

    }


    //O mÃ©todo atualizarConector deve receber alteraÃ§Ãµes do estado do Jfesm proveniente
    //do tratamento das mensagens enviadas.
    private void atualizarConector() {
        
        this.PalavrasSecretasAtualizadas = m_JFesm.getVisualPalavras();
        this.palavrasSecretasComPontos = maskPalavrasSecretas();
        this.partesForca = m_JFesm.partesForca();
        this.listaDeLetrasUsadas = m_JFesm.LetrasSugeridas();
        this.listaPalavrasErradas = m_JFesm.PalpitesErrados();        
        this.fimDojogo = m_JFesm.JogoTerminou();
        
        this.JogadorAtivo = m_JFesm.ProximoJogador();

    }

    /*IMPORTANTE:
    Os mÃ©todos a seguir Falsificam o modelo!!
     */
    
    public void setIDJogo(String IDJogo) {
        this.IDJogo = "Teste01";
    }
    
    void falsificarModelo() {
        // this.ListaDeJogadores[0] = "Flavia"; //Deve ser recebido da tela
        this.ListaDeJogadores.add("Aline");
        this.ListaDeJogadores.add("Danilo");
        this.ListaDeJogadores.add("Flavia");
        this.ListaDeJogadores.add("Mariana");
        this.ListaDeJogadores.add("Victoria");

        criarPool();
        this.IDJogo = "Sala001";

        int n = (int)Math.random()*7;
        int m = (int)Math.random()*7;
        while ( n == m ){
            m = (int)Math.random()*7;
        }
        
        
        this.PalavrasSecretas[0] = db.selecionar(n); // deve ser recebido da tela
        this.PalavrasSecretas[1] = db.selecionar(m);

    }

    private String[] maskPalavrasSecretas() {
        String[] comPontos = new String[2];
        comPontos[0] = "";
        comPontos[1] = "";
        String p1 = PalavrasSecretasAtualizadas[0], p2 = PalavrasSecretasAtualizadas[1];
        char[] pal1 = p1.toCharArray();
        char[] pal2 = p2.toCharArray();
        
        for (int i = 0 ; i< p1.length(); i++){
            if(' ' == pal1[i]){
                comPontos[0] = comPontos[0] +".";
                
            }else{
            comPontos[0] = comPontos[0] + pal1[i];
            }
        
        }
        
         for (int i = 0; i< p2.length(); i++){
            if(' ' == pal2[i]){
                comPontos[1] = comPontos[1] +".";
                
            }else{
            comPontos[1] = comPontos[1] + pal2[i];
            }
        
        }
        
        return comPontos;
    
    }

   

}//end Conector

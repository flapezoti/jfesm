package servidor;


import java.util.ArrayList;


/**
 * @author Pezoti, Murakami
 * @version 1.0
 * @created 19-mai-2016 21:09:22
 *
 * OBS;: para testar a tela, fiz algumas implementações extras, estão no fim.
 */
public class JFesm implements InterfaceModeloConector {

    private Forca _forca;
    private PalavraSecreta[] _palavrasSecretas;

    private ArrayList<Jogador> ListaDeJogadores;
    private String[] ListaJogadoresAtivos;

    //variáveis recebidas a cada Jogada
    private String[] palpitePalavras;
    private char sugestaoLetra;

    //Componentes do Modelo
    public PalavraSecreta[] m_palavraSecreta;
    public Forca m_Forca;
    public JogadorSimples[] jogadoresC;
    public JogadorA jogadorA;
    public JogadorB jogadorB;
    public Jogador jogadorAtivo;
    public ArrayList<Jogador> listaDeJogadores;

    //Variáveis de Instância para guardar informações do JFesm que serão acessadas pelo Conector
    private boolean fimDoJogo;
    private boolean ContinuaJogando;
    private String Ganhador;
    private String NomeJogadorAtivo;
    private String ListaLetrasUsadas;
    private String ListaDePalpitesErrados;
    private boolean acertouJogada;

    public boolean JogadaInvalida;
    private String[] VisualPalavras;
    private String[] poolDeJogadoresAtivos;
    private int PartesForca;

    public String getGanhador() {
        return Ganhador;
    }

    /*
        Construtor original
     */
    public JFesm(String p1, String p2, String nomeA, String nomeB, String[] nomesSimples) {
        _forca = new Forca();

        jogadorA = new JogadorA(nomeA);
        jogadorB = new JogadorB(nomeB);
        jogadoresC = new JogadorSimples[nomesSimples.length];
        for (int k = 0; k < jogadoresC.length; k++) {
            jogadoresC[k] = new JogadorSimples(nomesSimples[k]);
        }

        _palavrasSecretas = new PalavraSecreta[2];
        _palavrasSecretas[0] = jogadorA.cadastrarPalavra(p1);
        _palavrasSecretas[1] = jogadorB.cadastrarPalavra(p2);

        //listaLetrasUsadas = new String[26];
        //palpitePalavras = new String[5][2];
        this.Ganhador = null;
        // jogadorAtivo = sortearPrimeiroJogador(jogadoresC);
        this.fimDoJogo = false;

    } //fim construtor


    /*
Construtor Falso
     */
    public JFesm(String[] PoolJogadores, String[] PalavrasSecretas) {
        _forca = new Forca();
        jogadorA = new JogadorA(PoolJogadores[0]);
        jogadorB = new JogadorB(PoolJogadores[1]);
        jogadoresC = new JogadorSimples[PoolJogadores.length];
        for (int k = 2; k < jogadoresC.length; k++) {
            jogadoresC[k] = new JogadorSimples(PoolJogadores[k]);
        }

        _palavrasSecretas = new PalavraSecreta[2];
        _palavrasSecretas[0] = jogadorA.cadastrarPalavra(PalavrasSecretas[0]);
        _palavrasSecretas[1] = jogadorB.cadastrarPalavra(PalavrasSecretas[1]);

        this.poolDeJogadoresAtivos = PoolJogadores;
        this.NomeJogadorAtivo = definirProximoJogador(PoolJogadores);
        // deve settar o primiro jogador, asism que instanciado
        this.VisualPalavras = new String[2];
        this.VisualPalavras = palavrasModificadas(); //atualiza para um string com os pespaços vazios.
        this.Ganhador = null;
        this.ListaLetrasUsadas = "";
        this.ListaDePalpitesErrados = "";
        this.fimDoJogo = false;
    } //fim do Construtor Falso

    /*
    @author Pezoti
     */
 /*
    public JogadorSimples sortearPrimeiroJogador(JogadorSimples[] j) {
        JogadorSimples ativo;
        int sorteio;
        sorteio = (int) (Math.random() * j.length);
        ativo = j[sorteio];
        ativo.setAtivo(true);

        return ativo;

    }
     */
 /*
    *
    @modificações para auto-mensagem validar 
    *Métodos de recbimento das mensagens do conector
     */ //não faz sentido validar
    //recebe as palavras, se ok, envia para palavras secretas, se acertou, revela as palavras. Salva ganhador. fim do Jogo.
    //se errou, elimina jogador do pool, adiciona na lista de palpites errados, fala que errou
    public void receberPalavras(String palavra1, String palavra2) {
        //ValidarJogada(palavra1, palavra2);
        if (!this.JogadaInvalida) {
            if ((_palavrasSecretas[0].verificarPalavra(palavra1)) && _palavrasSecretas[1].verificarPalavra(palavra2)) {
                _palavrasSecretas[0].revelarPalavra();
                _palavrasSecretas[1].revelarPalavra();
                //jogadorAtivo.setMeuStatus(StatusJogador.GANHOU);

                this.acertouJogada = true;
                this.VisualPalavras = this.palavrasModificadas();
                this.Ganhador = NomeJogadorAtivo;
                this.fimDoJogo = true;
            } else {
                ListaDePalpitesErrados = ListaDePalpitesErrados + " " + palavra1 + " " + palavra2;

               // jogadorAtivo.setMeuStatus(StatusJogador.PERDEU);

                this.ContinuaJogando = false;
                for (int i = 0; i < this.poolDeJogadoresAtivos.length; i++) {
                    
                    if (poolDeJogadoresAtivos[i] == this.NomeJogadorAtivo) {
                        poolDeJogadoresAtivos[i] = null;
                    }
                    if(poolDeJogadoresAtivos[i] != null){
                    this.ContinuaJogando = true;
                    }
                }

                this.acertouJogada = false;
                if(this.ContinuaJogando){
                    this.NomeJogadorAtivo = definirProximoJogador(this.poolDeJogadoresAtivos);
                    
                }else{
                this.fimDoJogo = true;
                this._palavrasSecretas[0].revelarPalavra();
                this._palavrasSecretas[1].revelarPalavra();
                this.VisualPalavras = palavrasModificadas();
            }
            }

        }
    }

    //recebe a letra, valida a jogada, envia mensagem para palavras verificarem se há letra, se há, atualizar visual das palavras.
    //Se não, adicionar parte forca, atualizar lista de letras usadas.
    //sortear Prox jogador
    public void receberLetra(char ch) {
        ValidarJogada(ch);

        if (!this.JogadaInvalida) {
            if ((_palavrasSecretas[0].verificarLetra(ch)) || (_palavrasSecretas[1].verificarLetra(ch))) {

                this.acertouJogada = true;

                if (_palavrasSecretas[0].verificarLetra(ch)) {
                    _palavrasSecretas[0].revelarLetra(ch);
                }
                if (_palavrasSecretas[1].verificarLetra(ch)) {
                    _palavrasSecretas[1].revelarLetra(ch);
                }
                this.VisualPalavras = palavrasModificadas();
                if ( (this._palavrasSecretas[0].getSituacao() == SituacaoPalavra.VISIVEL) && (this._palavrasSecretas[1].getSituacao() == SituacaoPalavra.VISIVEL)){
                	this.fimDoJogo = true;
                	this.Ganhador = NomeJogadorAtivo;
                }

            } else {
                this.acertouJogada = false;
                _forca.adicionarParte();
                this.PartesForca = _forca.getPartesForca();
                if(this.PartesForca == 5){
                    this.fimDoJogo = true;
                    this._palavrasSecretas[0].revelarPalavra();
                this._palavrasSecretas[1].revelarPalavra();
                this.VisualPalavras = palavrasModificadas();
                }
                this.ListaLetrasUsadas = this.ListaLetrasUsadas + " " + ch;

            }
            if(this.fimDoJogo == false){
             this.NomeJogadorAtivo = definirProximoJogador(this.poolDeJogadoresAtivos);
            }
        }
        
        
    }

    /*
    Início
    Métodos de Validação da jogada
     */
    /*
    private void ValidarJogada(String Palavra1, String Palavra2) {
        boolean OK = true;
        int i = 0;

        String l = this.ListaDePalpitesErrados;

        if (l.contains(Palavra1) || l.contains(Palavra2)) {
            OK = false;
        }

        this.JogadaInvalida = !OK;

    }
    */
//Método identifica se a letra psugerida pertence a lista de letras usadas

    private void ValidarJogada(char letra) {

        int i = 0;
        boolean OK = true;
        if (!"".equals(this.ListaLetrasUsadas)) {
            char[] l = this.ListaLetrasUsadas.toCharArray();

            while (OK && i < this.ListaLetrasUsadas.length()) {
                if (letra == l[i]) {
                    OK = false;
                }
                i++;
            }

        }
        this.JogadaInvalida = !OK;
    }

    /*
    FIM
    Métodos de Validação da jogada
     */
    private void atualizarListaLetrasUsadas(String listaLetrasUsadas) {

    }

   

    public boolean JogoTerminou() {
        return this.fimDoJogo;
    }

    public int partesForca() {
        return this.PartesForca;
    }

    public String ProximoJogador() {
        return this.NomeJogadorAtivo;
    }

    /**
     *
     * @param Palavra1
     * @param Palavra2
     */

    public String LetrasSugeridas() {
        return this.ListaLetrasUsadas;
    }
    public String PalpitesErrados(){
        return this.ListaDePalpitesErrados;
    }

   public boolean acertouLetra() {
        return this.acertouJogada;
    }


    public boolean acertouPalavras() {
        return this.acertouJogada;
    }

    /*
    Início das Falsificações Aline
     */
    public String definirProximoJogador(String[] listaJogadores) {
        String ativo = null;
        int sorteio;
        while (ativo == null) {
            sorteio = (int) (Math.random() * listaJogadores.length);
            ativo = listaJogadores[sorteio];
        }
        return ativo;
    }

    //pergunta para a classe palavras e atualiza o crivo.

    public String[] palavrasModificadas() {

        this.VisualPalavras[0] = this._palavrasSecretas[0].getVisualPalavra();

        this.VisualPalavras[1] = this._palavrasSecretas[1].getVisualPalavra();
        return VisualPalavras;

    }

    public String[] getVisualPalavras() {
        return this.VisualPalavras;
    }

}//end JFesm

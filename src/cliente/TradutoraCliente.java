/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.net.UnknownHostException;

import cliente.TelaMediadores.AtualizadorTela;
import cliente.TelaMediadores.MediadorTela;
import cliente.TelaMediadores.Tela.Tela;

/**
 *
 * @author Murakami
 * 
 * É aclasse que instancia a conexão.
 * Deve conhecer as classes mediadoras.
 * É de responsabilidade da Classe TRadutoraCliente transformar as mensagens vindas da tela para o servidor.
 * E também, receber as mensagens do servidor e comunicar com a tela
 */

public class TradutoraCliente {
    	        
        AtualizadorTela _atTela;
        MediadorTela _medTela;
        Tela TelaDoJogo;
        ClienteSocket_JFesm client;
        
        //Variáveis de instância do Jogo
    private String[] PalavrasSecretas = new String[2];
    private String[] PoolJogadores = new String[5];
    private String[] PalavrasPalpitadas;
    private String listaDeLetrasUsadas;
    private String listaPalavrasErradas;
    private boolean fimDojogo;
    private String[] PalavrasSecretasAtualizadas = new String[2];
    private int partesForca;
    private String Ganhador;
    private String JogadorAtivo;
        

    public TradutoraCliente(MediadorTela _med) {
        this._medTela=_med;
        client = new ClienteSocket_JFesm();
        
        passarDadosDoJogo();
                
        this.TelaDoJogo = new Tela(PalavrasSecretasAtualizadas[0], PalavrasSecretasAtualizadas[1], PoolJogadores, this._medTela);
        TelaDoJogo.setVisible(true);
        this._atTela = new AtualizadorTela(TelaDoJogo);
        this._atTela.MensagemJogadorAtivo(this.JogadorAtivo);
        //this._atTela.atualizarJogadorAtivo();
    }

    
        //método recebe os dados iniciais para instanciar a tela
        //Recebe uma string do da forma: 0 PalavraSecreta1 PalavraSecreta2 Jogador1 Jogador2 Jogador3 Jogador4 Jogador5 JogadorAtivo
    public void passarDadosDoJogo(){
    	
        String ret;
            ret = client.enviarDados("0 passarDadosDoJogo");
            
            String[] s = ret.split(" ");
            
            
            this.arrumarStringPalavras(s[1], s[2]);
            
            
            for (int i = 0; i<5; i++){ //sempre 5 jogadores
                this.PoolJogadores[i] = s[i+3];
            }
            this.JogadorAtivo = s[8];
            
                
    }

    //método envia a letra
    public void enviarLetraSugerida(char letraSugerida) {
        String msg = "1 enviarLetraSugerida " + letraSugerida;
        String ret = client.enviarDados(msg);
        traduzirStrRet(ret);
    
    }

    //método envia palpites
    public void enviarPalpites(String[] palavrasPalpitadas) {
        String msg = "2 enviarPalpites " + palavrasPalpitadas[0] + " " + palavrasPalpitadas[1];
        String ret = client.enviarDados(msg);
        traduzirStrRet(ret);  
    }

     
       
    private void traduzirStrRet(String ret) {
        String[] resultado = ret.split(" ");
        
        int type;
        type = parseInt(resultado[0]);
        
        switch (type){
            case 1: //acertou letra, jogo continua
                //1 mensagemAcertouLetra atualizarPalavrasNaTela  PalavraSecretaAtualizada1 PalavraSecretaAtualizada2 JogadorAtivo5 MensagemJogadorAtivo6 
                this._atTela.mensagemAcertouLetra();
                this.arrumarStringPalavras(resultado[3], resultado[4]);
                this._atTela.atualizarPalavrasNaTela(this.PalavrasSecretasAtualizadas[0], this.PalavrasSecretasAtualizadas[1]);
                this._atTela.MensagemJogadorAtivo(resultado[5]);
                //this._atTela.atualizarJogadorAtivo();
                
                break;
                
            case 2: //acertou letra, ganhou
                //2 mensagemAcertouPalavras Jogador atualizarPalavrasNaTela  PalavraSecretaAtualizada1 PalavraSecretaAtualizada2 FimDoJogo
            	this.Ganhador = resultado[2];	
                this._atTela.mensagemAcertouPalavras(Ganhador);
                this.arrumarStringPalavras(resultado[3], resultado[4]);
                this._atTela.atualizarPalavrasNaTela(this.PalavrasSecretasAtualizadas[0], this.PalavrasSecretasAtualizadas[1]);
                
                this._atTela.mensagemGanhador();
                this._atTela.FimDoJogo();
                
                break;
                
           
                
            case 3:
                //3 atualizarLetrasErradas char* atualizarImagemForca int MensagemJogadorAtivo JogadorAtivo 
                this._atTela.atualizarLetrasErradas(resultado[2]);
                this.partesForca = parseInt(resultado[4]);
                this._atTela.atualizarImagemForca(partesForca);
                this.JogadorAtivo=resultado[6];
                this._atTela.MensagemJogadorAtivo(resultado[6]);
                break;
                
            case 8: //errou letra, enforcado
                //8 atualizarLetrasErradas char atualizarImagemForca int FimDoJogo 
                 this._atTela.mensagemAcertouLetra();
                 this._atTela.atualizarLetrasErradas(resultado[2]);
                 this.partesForca = parseInt(resultado[4]);
                 this._atTela.atualizarImagemForca(partesForca);
                 this._atTela.mensagemTodosPerdedores();
                 break;
                
            case 4:
                //4 MensagemErroInputLetra
                this._atTela.MensagemErroInputLetra();
                break;
                
            case 5:
                //5 mensagemAcertouPalavras JogadorAtivo atualizarPalavrasNaTela PalavraSecretaRevelada1  PalavraSecretaRevelada2 mensagemGanhador FimDoJogo
                this.Ganhador = resultado[2];
                this._atTela.mensagemAcertouPalavras(Ganhador);
                this.arrumarStringPalavras(resultado[4], resultado[5]);
                this._atTela.atualizarPalavrasNaTela(this.PalavrasSecretasAtualizadas[0], this.PalavrasSecretasAtualizadas[1]);
                this._atTela.mensagemGanhador();
                this.fimDojogo=true;
                this._atTela.FimDoJogo();
                break;
                
            case 6:
                //6 mensagemErrouPalavras mensagemPerdedor atualizarListaPalpites  PalavraErrada1 PalavraErrada2 MensagemJogadorAtivo JogadorAtivo 
            	
            	this._atTela.mensagemErrouPalavras();
                this._atTela.mensagemPerdedor(JogadorAtivo);
                this.JogadorAtivo = resultado[7];
                String palavrasErradas = resultado[4] + " " + resultado[5];
                System.out.println(palavrasErradas);
                this._atTela.atualizarListaPalpites(palavrasErradas);                
                this._atTela.MensagemJogadorAtivo(JogadorAtivo);
                break;
                
            case 7:
                //7 mensagemErrouPalavras mensagemPerdedor atualizarListaPalpites  PalavraErrada1 PalavraErrada2 mensagemTodosPerdedores atualizarImagemForca
                this._atTela.mensagemErrouPalavras();
                this._atTela.mensagemPerdedor(this.JogadorAtivo);
                String palavrasErradas2 = resultado[4] + " " + resultado[5];
                this._atTela.atualizarListaPalpites(palavrasErradas2);
                this._atTela.mensagemTodosPerdedores();
                this._atTela.atualizarImagemForca(5);
                break;
        } 
    
    }

     public void arrumarStringPalavras(String p1P, String p2P){
         String n1 = "";
         String n2 = "";
         char[] pal1 = p1P.toCharArray();
         char[] pal2 = p2P.toCharArray();
         
         for(int i = 0; i<(p1P.length()); i++){
             if(pal1[i] == '.'){
             n1 = n1 + " ";
         }else{
                 n1 = n1 + pal1[i];
             }}
         
         this.PalavrasSecretasAtualizadas[0] = n1;
         
           for(int i = 0; i<(p2P.length()); i++){
             if(pal2[i] == '.'){
             n2 = n2 + " ";
         }else{
                 n2 = n2 + pal2[i];
             }}
         
         this.PalavrasSecretasAtualizadas[1] = n2;
    
        }
  
}

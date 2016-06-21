package cliente.TelaMediadores;

import cliente.TradutoraCliente;



/**
 * 
 * @author Murakami
 * @version 1.2
 * Cliente/Servidor
 * @created 19-mai-2016 21:09:29
 * 
 * 
 * 
 * Objetivo da classe MediadorTela é receber todas as informações provenientes 
 * do componente da Interface com o usuário. Repassando essas informações
 * para classe TradutoraCliente, a qual transmitirá as informações para o servidor.
 */


public class MediadorTela{

	private String[] InputPalavrasSecretas;
	private String JogadorAtivo;
	private char letraSugerida;
	private String[] palavrasPalpitadas;
	public TradutoraCliente m_Tradutora;
        
       
        boolean JogoPronto;
        private String escolhaJogada;
	
       
           
        
	public MediadorTela(){
            
             this.palavrasPalpitadas = new String[2];
             this.InputPalavrasSecretas = new String[2];
                   
           
	}

           
	/**
	 * 
	 * @param letraSugerida
	 */
	public void recebeLetra(char letraSugerida){
            setLetraSugerida(letraSugerida);
            
            m_Tradutora.enviarLetraSugerida(this.letraSugerida);
            
            
	}

	/**
	 * 
	 * @param Palavra1
	 * @param Palavra2
	 */
	public void recebePalpite(String Palavra1, String Palavra2){
            
            this.palavrasPalpitadas[0] = Palavra1.toUpperCase().trim();
            this.palavrasPalpitadas[1] = Palavra2.toUpperCase().trim();
            
            
            m_Tradutora.enviarPalpites(this.palavrasPalpitadas);
            
	}

        public void setLetraSugerida(char letraSugerida) {
        this.letraSugerida = letraSugerida;
    }

    public void AssociarTradutora(TradutoraCliente _t) {
        this.m_Tradutora = _t;
    
    }
        
       
       
}//end MediadorTela
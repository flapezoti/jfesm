package servidor;


/**
 * @author Murakami
 * @version 1.0
 * @created 19-mai-2016 21:09:24
 */
public class PalavraSecreta {

	private char[] crivo ;
	private String palavra;
	private SituacaoPalavra situacao;
        
        //String com as lacunas
        private String VisualPalavra;     
        
	public PalavraSecreta( String s){
            this.VisualPalavra = new String();
		palavra = s.toUpperCase();		
        crivo = new char[palavra.length()];
        for ( int k = 0; k < palavra.length(); k++){
        	crivo[k] = '0';
               // this.VisualPalavra = this.VisualPalavra + " _ ";               
        }  
       this.atualizaVisualPalavra();
situacao = SituacaoPalavra.INVISIVEL;

        }
	
        
	public void revelarLetra(char letra){
		int k;
		char[] copiaCrivo;
		copiaCrivo = crivo;
		
		for ( k = 0; k < palavra.length(); k++){
			if ( palavra.charAt(k) == letra ){
				copiaCrivo[k] = '1';
                                
			}
		}
		
		this.crivo = copiaCrivo;
		
		if ( crivoCompleto() ){
			this.situacao = SituacaoPalavra.VISIVEL;
		} else {
			this.situacao = SituacaoPalavra.PARCIAL;
		}
                
                atualizaVisualPalavra();
		
	}
	
	private boolean crivoCompleto(){
		boolean completo;
		completo = true;
		int k = 0;
		while ( (k < palavra.length()) && (completo == true)){
			if ( this.crivo[k] != '1'){
				completo = false;
			}
                        k++;
		}
		
		return completo;
	}

	
	public void revelarPalavra(){
		char[] copiaCrivo;
		copiaCrivo = crivo;
		
		for (int k = 0; k < copiaCrivo.length; k++){
			copiaCrivo[k] = '1';
		}
		
		this.crivo = copiaCrivo;
                atualizaVisualPalavra();
	}

	public boolean verificarLetra(char letra){
		boolean achou = false;
		int k = 0;
		while ( (achou == false) && (k < palavra.length())){
			if ( palavra.charAt(k) == letra ){
				achou = true;
			}
                        k++;
		}
		return achou;
	}

	public boolean verificarPalavra(String palpite){
            return palavra.equals(palpite);
        }

    private void atualizaVisualPalavra() {
        this.VisualPalavra = "";
        for(int i = 0; i< palavra.length();i++){
            if(this.crivo[i] == '0'){
                
                this.VisualPalavra = this.VisualPalavra + " _";
                       
            }else{
            this.VisualPalavra = this.VisualPalavra + " " + palavra.charAt(i);
            }
        
        }    
    
    }

    public SituacaoPalavra getSituacao() {
        return situacao;
    }

    public String getVisualPalavra() {
        return VisualPalavra;
    }
        
        
}
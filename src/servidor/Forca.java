package servidor;



import java.io.File;



/**
 * @author Murakami
 * @version 1.0
 * @created 19-mai-2016 21:09:24
 */
public class Forca {

	private int partesForca;
	private EstadoForca status;

	public Forca(){
		partesForca = 0;
		status = EstadoForca.VIVO;
	}

	public void adicionarParte(){
		if (partesForca < 4){
			partesForca ++;
		} else if (partesForca == 4) {
			partesForca++;
			status = EstadoForca.ENFORCADO;
		}
	}

	public boolean forcaViva(){
		if (status == EstadoForca.VIVO){
			return true;
		} else {
			return false;
		}
	}

    public int getPartesForca() {
        return partesForca;
    }
        
        
}//end Forca
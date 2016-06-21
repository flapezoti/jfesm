package servidor;



/**
 * @author Murakami
 * @version 1.0
 * @created 23-mai-2016 22:59:25
 */
public interface InterfaceModeloConector {

	public boolean acertouLetra();

	public boolean acertouPalavras();

	public boolean JogoTerminou();

	/**
	 * 
	 * @param letrasUsadas
	 */
	public String LetrasSugeridas();

	public String[] palavrasModificadas();

	public int partesForca();

	public String ProximoJogador();

	/**
	 * 
	 * @param letraSugerida
	 */
	public void receberLetra(char letraSugerida);

	/**
	 * 
	 * @param palavra1
	 * @param palavra2
	 */
	public void receberPalavras(String palavra1, String palavra2);

}
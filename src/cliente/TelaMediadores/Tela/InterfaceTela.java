package cliente.TelaMediadores.Tela;


import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * @author Murakami
 * @version 1.0
 * @created 19-mai-2016 21:26:04
 *
 * @see JogoPronto
 */
public interface InterfaceTela {

    /**
     *
     * @param FotoForca
     */
    public void atualizarImagemForca(ImageIcon FotoForca);

    /**
     *
     * @param letra
     */
    public void atualizarListaLetrasUsadas(String letras);

    /**
     *
     * @param textoStatus
     * @param textoJogadorAtivo
     */
    public void atualizarTextoStatus(String textoStatus, String textoJogadorAtivo);

    /**
     *
     * @param JogadorAtivo
     * @return 
     */
    public void mostrarJogadorAtivo(String JogadorAtivo);

    /**
     *
     * @param letra
     */
    public void receberLetra(char letra);

    /**
     *
     * @param nome
     */
    public void receberNomeJogador(String nome);

    /**
     *
     * @param Palavra1
     * @param Palavra2
     */
    public void receberPalavras(String Palavra1, String Palavra2);

    /**
     *
     * @param ListaPalpites
     */
    public void atualizarPalpites(String ListaPalpites);

    /**
     *
     * @param jogoPronto
     */
    public void jogoPronto(boolean jogoPronto);
}

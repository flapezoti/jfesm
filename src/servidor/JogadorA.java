/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;
/**
 *
 * @author Murakami
 */
class JogadorA extends Jogador{

    public JogadorA(String username) {
        super(username);
    }
 
    public PalavraSecreta cadastrarPalavra(String palavra){
		PalavraSecreta p;
		p = new PalavraSecreta(palavra);
		return p;
	}
}

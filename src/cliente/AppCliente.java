/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.TelaMediadores.MediadorTela;

public class AppCliente {

    /**	
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        
        
       
        MediadorTela _med = new MediadorTela();
         TradutoraCliente _tradutora = new TradutoraCliente(_med);
        _med.AssociarTradutora(_tradutora);
      
        
		
        
    }
    
}

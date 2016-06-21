package servidor;


public class TradutoraServidor {
	
	Conector cn;
	
	TradutoraServidor() {
		cn = new Conector();
	}
	
	String receberMensagem(String recebida ){
		String mensagem_cliente = "";
		
		String[] msg = recebida.split(" ");
		
		switch (msg[0]){
			case "0":
				mensagem_cliente = cn.iniciarJogo();
				break;
				
			case "1":
				mensagem_cliente = cn.enviarLetraSugerida(msg[2].charAt(0));
				break;
				
			case "2":
				String[] palavras = new String[2];
				palavras[0] = msg[2];
				palavras[1] = msg[3];
				mensagem_cliente = cn.enviarPalpites(palavras);
		}
		
		return mensagem_cliente;
		
		
		
	}

}

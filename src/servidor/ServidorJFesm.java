package servidor;
import java.io.*;
import java.net.*;
public class ServidorJFesm {
	
	
		ServerSocket providerSocket;
		Socket connection = null;
		ObjectOutputStream out;
		ObjectInputStream in;
		String message;
                TradutoraServidor tradutora;
                
                
                void ServidorJFesm(){
                    tradutora = new TradutoraServidor();
                }


		void run()
		{
			try{
				//1. creating a server socket
                                
                                System.out.println("OI!!!");
				providerSocket = new ServerSocket(2037, 10);
				//2. Wait for connection
				System.out.println("Waiting for connection");
				connection = providerSocket.accept();
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());
				//3. get Input and Output streams
				out = new ObjectOutputStream(connection.getOutputStream());
				out.flush();
				in = new ObjectInputStream(connection.getInputStream());
				//4. The two parts communicate via the input and output streams
				do{
					try{

						message = (String)in.readObject();
						System.out.println("server recebendo: " + message);
						//System.out.println("client>" + message);
						
							String resultado = tradutora.receberMensagem(message);
                                                        System.out.println("server recebendo: " + message);
                                                        System.out.println("server recebendo: " + resultado);
							
							sendMessage(resultado);
                                                        System.out.println("server recebendo: " + resultado);
							break;
					}
					catch(Exception ex){
						System.err.println("Data received in unknown format");
						break;
					}
				}while(true);
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
			finally{
				//4: Closing connection
				try{
					in.close();
					out.close();
					providerSocket.close();
				}
				catch(IOException ioException){
					ioException.printStackTrace();
				}
			}
		}
		void sendMessage(String msg)
		{
			try{
				out.writeObject(msg);
				out.flush();
				System.out.println("server enviando: " + msg);
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
		public static void main(String args[]){
                        
			ServidorJFesm server = new ServidorJFesm();
                        
			while(true){
				server.run();
			}
		}

}

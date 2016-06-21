/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Murakami
 */
public class ClienteSocket_JFesm {


	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
	ClienteSocket_JFesm(){}
        
	String enviarDados(String dados)
	{
		try{
			//1. creating a socket to connect to the server
			requestSocket = new Socket("localhost", 2037);
			System.out.println("Connected to 172.17.1.7 in port 2037");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server
                        sendMessage(dados);
                        message = (String)in.readObject();
                        System.out.println("client recebendo: " + message);
			
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
                        System.err.println(":(!");
		} catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
            }
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
			return message;
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client enviando: " + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

}
  
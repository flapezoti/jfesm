package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ra00141210
 */
public abstract class Jbdc_connections{
    
    Connection con;
    Statement st;
    

    String url;

    public Jbdc_connections(String url) {
        try {
            this.url = url;
            this.con = connectDB(url);
            this.st = con.createStatement();
            String SQL = "CREATE DATABASE IF NOT EXISTS JFESM;";
            this.st.execute(SQL);
            
           con.close();
           st.close();
            
            
            System.out.println(url + "JFESM");
            this.con = connectDB(url+ "JFESM");
            this.st = con.createStatement();
            
            //usar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    private Connection connectDB(String url) throws Exception {
        try {

            String user, password;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            user = "root";
            password = "root";
            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }

    }
    
    
    public void createTable(String localDatabase, String tableName, String tablevariables){
		try{
			//usar();
			st = con.createStatement();  
			String SQL = ("CREATE TABLE " + tableName + " (" + 
			tablevariables + ")");
			st.execute(SQL); /// Verificar retorno do comando
			
			
			
		}catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}      
		
		
	}
    
    public void deleteLine(String valorLinha, String localDatabase, String nomeTabela){

     this.url = localDatabase; 
            try{
			
			st = con.createStatement();  
			deleteAluno(st, valorLinha, nomeTabela);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}      
	}
	
	public void deleteAluno(Statement st, String valorLinha, String nomeTabela) throws Exception {
		try {
			
			String SQL = ("DELETE FROM "+ nomeTabela + "WHERE nome = '" + valorLinha + "'");
			st.execute(SQL);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}    	
	}


public void insertLine(String localDatabase, String nomeTabela, String novaLinha){
		try{
			
			st = con.createStatement();  
			String SQL = (novaLinha);			
			
			st.execute(SQL);
			
		}catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}      
		
		
	}
}
	
    


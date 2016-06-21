package servidor;
import java.sql.ResultSet;
/*
 *
 * @author ra00141210
 */
public class JdbcMySQL_User extends Jbdc_connections{
    String localDatabase;
    String tableName; 
    String tablevariables;

    public JdbcMySQL_User(String url) {
        super(url);
        this.localDatabase = url;
        
    }
    

    
    public void criar(String nomeTabela, String Variaveis) {
        this.tableName = nomeTabela;
        this.tablevariables = Variaveis;
       super.createTable(localDatabase, tableName, tablevariables);
    }

   
    public void gravar(int id, String palavra) {
      String novaLinha = "INSERT INTO PALAVRAS VALUES (" + id + ", '" + palavra + "')";
      super.insertLine(localDatabase, "PALAVRAS", novaLinha);  }

 
    public void fechar() {
      
    }

       
    public String selecionar(int i){
        String palavra = "";
        String SQL = ("SELECT palavra FROM palavras WHERE id =" + i);
        ResultSet rs = null;
        try {
          rs= st.executeQuery(SQL);
          palavra = rs.getString("palavra");
        } catch(Exception e) {
                System.out.println(e.getMessage());
        }  
        
        return palavra;
    }
    
    

    
}
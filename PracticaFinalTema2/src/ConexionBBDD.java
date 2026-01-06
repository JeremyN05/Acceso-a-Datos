import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
	
    private static final String URL = "jdbc:mysql://localhost:3306/jugueteria2";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "cfgs";

    public static Connection obtenerConexion() {
        
    	try {
           
    		Class.forName("com.mysql.cj.jdbc.Driver");
            
    		return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        
    	} catch (SQLException | ClassNotFoundException e) {
            
    		System.out.println("Error al conectar a la BBDD: " + e.getMessage());
    		
    		System.out.println("Cerrando el programa");
    		
    		System.exit(0);
            
    		return null;
        
    	}
    
    }
	
}

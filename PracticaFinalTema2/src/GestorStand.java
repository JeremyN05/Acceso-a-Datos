import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorStand {

	public static int comprobarStockEnStand(Connection conexion, int idJuguete, int idStand, int idZona) {
	  
		try {
	     
	    	String sql = "SELECT Cantidad_disponible FROM stock WHERE ID_Juguete = ? AND ID_Stand = ? AND ID_Zona = ?";
	        PreparedStatement ps = conexion.prepareStatement(sql);
	        ps.setInt(1, idJuguete);
	        ps.setInt(2, idStand);
	        ps.setInt(3, idZona);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	      
	        	return rs.getInt("Cantidad_disponible");
	      
	        }
	   
	    } catch (SQLException e) {
	    
	    	e.printStackTrace();
	    
	    }
	   
	    return 0;
	
	}
	
}

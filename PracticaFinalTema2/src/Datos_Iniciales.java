import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Datos_Iniciales {

	public static void insertarZonasYStands(Connection conexion) {
	    
	    if (conexion == null) {
	        System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    }

	    try {
	        
	        String[] nombresZonas = {"Zona Infantil", "Zona Juvenil", "Zona Electrónica", "Zona Exterior"};
	        String[] descripcionesZonas = {
	            "Zona con juguetes para niños pequeños",
	            "Zona con juguetes para adolescentes",
	            "Zona con juguetes electrónicos y gadgets",
	            "Zona con juguetes para exterior y deportes"
	        };

	        for (int i = 0; i < nombresZonas.length; i++) {
	            
	        	String consulta = "SELECT * FROM zona WHERE Nombre = ?";
	            PreparedStatement sentencia = conexion.prepareStatement(consulta);
	           
	            sentencia.setString(1, nombresZonas[i]);
	            ResultSet resultado = sentencia.executeQuery();
	           
	            if (!resultado.next()) {
	                String consultaZona = "INSERT INTO zona (Nombre, Descripcion) VALUES (?, ?)";
	                PreparedStatement sentenciaZona = conexion.prepareStatement(consultaZona);
	                sentenciaZona.setString(1, nombresZonas[i]);
	                sentenciaZona.setString(2, descripcionesZonas[i]);
	                sentenciaZona.executeUpdate();
	           
	            }
	        
	        }

	        String[] nombresStands = {"Stand Juguetes", "Stand Coches", "Stand Drones", "Stand Bicicletas"};
	        
	        String[] descripcionesStands = {
	            "Stand con pelotas y peluches",
	            "Stand con coches y vehículos",
	            "Stand con drones y robots electrónicos",
	            "Stand con bicicletas y juguetes para exterior"
	       
	        };

	        for (int i = 0; i < nombresStands.length; i++) {
	            
	        	String consulta2 = "SELECT * FROM stand WHERE Nombre = ? AND ID_Zona = ?";
	            PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
	            sentencia2.setString(1, nombresStands[i]);
	            sentencia2.setInt(2, i + 1);
	            ResultSet rsStand = sentencia2.executeQuery();
	            
	            if (!rsStand.next()) {
	              
	            	String consultaStand = "INSERT INTO stand (ID_Zona, Nombre, Descripcion) VALUES (?, ?, ?)";
	                PreparedStatement sentenciaStand = conexion.prepareStatement(consultaStand);
	                sentenciaStand.setInt(1, i + 1);
	                sentenciaStand.setString(2, nombresStands[i]);
	                sentenciaStand.setString(3, descripcionesStands[i]);
	                sentenciaStand.executeUpdate();
	           
	            }
	       
	        }

	        System.out.println("Zonas y stands iniciales creados si no existían.");

	    } catch (SQLException e) {
	   
	    	e.printStackTrace();
	   
	    }

	}


}

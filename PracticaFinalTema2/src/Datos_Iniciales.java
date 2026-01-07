import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Datos_Iniciales {
	
	public static void insertarJuguetesYEmpleados(Connection conexion){
	    
	    if (conexion == null) {
	        System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    }

	    try {
	        
	        String[] nombreJuguetes = {"Peluche Doraemon", "Nenuco", "Coche radio control", "Cama elástica"};
	        String[] descripcionJuguete = {
	            "Peluche Nobita",
	            "Muñeco realista bebe",
	            "Coche a control remoto",
	            "Cama para saltar en el exterior"
	        };
	        
	        double[] precioJuguetes = {9.99, 15.50, 12.90, 30.00};
	        int[] cantidadStock = {18, 20, 40, 12};
	        String[] categoria = {"Peluches", "Muñecos", "Vehículos", "Exterior"};
	        
	        for (int i = 0; i < nombreJuguetes.length; i++) {

	           
	            String consulta = "SELECT * FROM juguete WHERE Nombre = ?";
	            PreparedStatement sentencia = conexion.prepareStatement(consulta);
	            sentencia.setString(1, nombreJuguetes[i]);
	            ResultSet resultado = sentencia.executeQuery();

	            if (!resultado.next()) {
	                
	                String consulta2 = "INSERT INTO juguete (Nombre, Descripcion, Precio, Cantidad_en_stock, Categoria) VALUES (?, ?, ?, ?, ?)";
	                PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
	                sentencia2.setString(1, nombreJuguetes[i]);
	                sentencia2.setString(2, descripcionJuguete[i]);
	                sentencia2.setDouble(3, precioJuguetes[i]);
	                sentencia2.setInt(4, cantidadStock[i]);
	                sentencia2.setString(5, categoria[i]);
	                sentencia2.executeUpdate();
	            }

	        }
	        
	        String[] nombres = {"Carlos Pérez", "Laura Gómez", "Miguel Sánchez", "Ana López"};
	        String[] cargos = {"Vendedor", "Cajera", "Supervisor", "Inventario"};
	        String[] fechasIngreso = {"2021-03-10", "2022-01-05", "2020-11-20", "2023-06-15"};

	        for (int i = 0; i < nombres.length; i++) {

	            
	            String consulta = "SELECT * FROM empleado WHERE Nombre = ?";
	            PreparedStatement sentencia = conexion.prepareStatement(consulta);
	            sentencia.setString(1, nombres[i]);
	            ResultSet resultado = sentencia.executeQuery();

	            if (!resultado.next()) {
	                
	                String insert = "INSERT INTO empleado (Nombre, Cargo, Fecha_ingreso) VALUES (?, ?, ?)";
	                PreparedStatement ps = conexion.prepareStatement(insert);
	                ps.setString(1, nombres[i]);
	                ps.setString(2, cargos[i]);
	                ps.setString(3, fechasIngreso[i]);
	                ps.executeUpdate();
	            }

	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}
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
	               
	            	String consulta2 = "INSERT INTO zona (Nombre, Descripcion) VALUES (?, ?)";
	                PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
	                sentencia2.setString(1, nombresZonas[i]);
	                sentencia2.setString(2, descripcionesZonas[i]);
	                sentencia2.executeUpdate();
	           
	            }
	      
	        }


	        String[] nombresStands = {"Stand Pelotas", "Stand Coches", "Stand Drones", "Stand Bicicletas"};
	        String[] descripcionesStands = {
	            "Stand con pelotas y peluches",
	            "Stand con coches y vehículos",
	            "Stand con drones y robots electrónicos",
	            "Stand con bicicletas y juguetes para exterior"
	        };


	        for (int i = 0; i < nombresStands.length; i++) {

	            String obtenerZona = "SELECT ID_Zona FROM zona WHERE Nombre = ?";
	            PreparedStatement psZona = conexion.prepareStatement(obtenerZona);
	            psZona.setString(1, nombresZonas[i]);
	            ResultSet rsZona = psZona.executeQuery();

	            if (rsZona.next()) {
	                int idZona = rsZona.getInt("ID_Zona");

	                String consulta3 = "SELECT * FROM stand WHERE Nombre = ? AND ID_Zona = ?";
	                PreparedStatement sentencia3 = conexion.prepareStatement(consulta3);
	                sentencia3.setString(1, nombresStands[i]);
	                sentencia3.setInt(2, idZona);
	                ResultSet resultado = sentencia3.executeQuery();

	                if (!resultado.next()) {
	                    String consulta4 = "INSERT INTO stand (ID_Zona, Nombre, Descripcion) VALUES (?, ?, ?)";
	                    PreparedStatement sentencia4 = conexion.prepareStatement(consulta4);
	                    sentencia4.setInt(1, idZona);
	                    sentencia4.setString(2, nombresStands[i]);
	                    sentencia4.setString(3, descripcionesStands[i]);
	                    sentencia4.executeUpdate();
	                }
	            }
	        }


	        System.out.println("Zonas y stands iniciales creados si no existían.");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}

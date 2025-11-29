import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorDatosTienda {
	
	public static void listarProductosOrdenadosPorPrecio(Connection conexion) {		
		
		if (conexion == null) {
		       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}
	   
		String consulta = "SELECT ID_Juguete, Nombre, Precio FROM juguete ORDER BY Precio ASC";

	    try {
	       
	    	PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        ResultSet resultado = sentencia.executeQuery();

	        System.out.println("\n===== PRODUCTOS ORDENADOS POR PRECIO =====");

	        while (resultado.next()) {
	         
	        	System.out.println("ID: " + resultado.getInt("ID_Juguete")
	                    + " | Nombre: " + resultado.getString("Nombre")
	                    + " | Precio: " + resultado.getDouble("Precio") + " €");
	       
	        }

	    } catch (SQLException e) {
	      
	    	e.printStackTrace();
	   
	    }
	
	}
	
	public static void mostrarCambiosEmpleados(Connection conexion) {
		
		if (conexion == null) {
		       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}
	   
		String consulta = """
	       
	         SELECT c.ID_Cambio, e.Nombre AS Empleado, 
	               j1.Nombre AS Juguete_Original,
	               j2.Nombre AS Juguete_Nuevo,
	               c.Motivo, c.Fecha,
	               c.Stand_origen, c.ID_Zona_origen,
	               c.Stand_destino, c.ID_Zona_destino
	        FROM cambio c
	        JOIN empleado e ON c.ID_Empleado = e.ID_Empleado
	        JOIN juguete j1 ON c.ID_Juguete_Original = j1.ID_Juguete
	        JOIN juguete j2 ON c.ID_Juguete_Nuevo = j2.ID_Juguete
	        ORDER BY c.Fecha DESC;
	        """;

	    try {
	      
	    	PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        ResultSet resultado = sentencia.executeQuery();

	        System.out.println("\n===== CAMBIOS DE EMPLEADOS =====");

	        while (resultado.next()) {
	           
	        	System.out.println("ID Cambio: " + resultado.getInt("ID_Cambio"));
	            System.out.println("Empleado: " + resultado.getString("Empleado"));
	            System.out.println("Juguete original: " + resultado.getString("Juguete_Original"));
	            System.out.println("Juguete nuevo: " + resultado.getString("Juguete_Nuevo"));
	            System.out.println("Motivo: " + resultado.getString("Motivo"));
	            System.out.println("Fecha: " + resultado.getTimestamp("Fecha"));
	            System.out.println("Origen: Stand " + resultado.getInt("Stand_origen") +
	                               " | Zona " + resultado.getInt("ID_Zona_origen"));
	            System.out.println("Destino: Stand " + resultado.getInt("Stand_destino") +
	                               " | Zona " + resultado.getInt("ID_Zona_destino"));
	            System.out.println("-----------------------------------------");
	        
	        }

	    } catch (SQLException e) {
	     
	    	e.printStackTrace();
	   
	    }
	
	}
	
	public static void obtenerVentasEmpleadoMes(Connection conexion, Scanner entrada) {

		if (conexion == null) {
		       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}
		
	    try {
	        
	    	System.out.println("Ingrese el ID del empleado:");
	        int idEmpleado = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Ingrese el mes (1-12):");
	        int mes = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Ingrese el año (YYYY):");
	        int año = entrada.nextInt();
	        entrada.nextLine();

	        String consulta = "SELECT v.ID_Venta, v.ID_Juguete, j.Nombre, v.Monto, v.Fecha " +
	                     "FROM venta v " +
	                     "INNER JOIN juguete j ON v.ID_Juguete = j.ID_Juguete " +
	                     "WHERE v.ID_Empleado = ? AND MONTH(v.Fecha) = ? AND YEAR(v.Fecha) = ? " +
	                     "ORDER BY v.Fecha";

	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	       
	        sentencia.setInt(1, idEmpleado);
	        sentencia.setInt(2, mes);
	        sentencia.setInt(3, año);

	        ResultSet resultado = sentencia.executeQuery();

	        System.out.println("\n=== VENTAS DEL EMPLEADO " + idEmpleado + " EN " + mes + "/" + año + " ===");

	        double total = 0;
	        boolean hayResultados = false;

	        while (resultado.next()) {
	           
	        	hayResultados = true;
	            System.out.println("--------------------------------------");
	            System.out.println("ID Venta: " + resultado.getInt("ID_Venta"));
	            System.out.println("Juguete: " + resultado.getString("Nombre"));
	            System.out.println("Monto: " + resultado.getDouble("Monto"));
	            System.out.println("Fecha: " + resultado.getTimestamp("Fecha"));
	            total += resultado.getDouble("Monto");
	       
	        }

	        if (!hayResultados) {
	        
	        	System.out.println("No hay ventas registradas para este empleado en ese mes.");
	        
	        } else {
	         
	        	System.out.println("--------------------------------------");
	            System.out.println("TOTAL FACTURADO: " + total + " €");
	        
	        }

	    } catch (SQLException e) {
	    
	    	e.printStackTrace();
	    
	    }
	
	}

	public static void obtenerJuguetesEnStand(Connection conexion, Scanner entrada) {
		
		if (conexion == null) {
		       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}

	    try {
	       
	    	System.out.println("Introduce el ID del stand:");
	        int idStand = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Introduce el ID de la zona:");
	        int idZona = entrada.nextInt();
	        entrada.nextLine();

	        String consulta = 
	            "SELECT j.ID_Juguete, j.Nombre, j.Precio, s.Cantidad_disponible " +
	            "FROM stock s " +
	            "JOIN juguete j ON s.ID_Juguete = j.ID_Juguete " +
	            "WHERE s.ID_Stand = ? AND s.ID_Zona = ? AND s.Cantidad_disponible > 0";

	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, idStand);
	        sentencia.setInt(2, idZona);

	        ResultSet resultado = sentencia.executeQuery();

	        System.out.println("\n--- JUGUETES DISPONIBLES EN EL STAND " + idStand + " - ZONA " + idZona + " ---");

	        boolean hayResultados = false;

	        while (resultado.next()) {
	            hayResultados = true;
	            System.out.println(
	                "ID Juguete: " + resultado.getInt("ID_Juguete") +
	                " | Nombre: " + resultado.getString("Nombre") +
	                " | Precio: " + resultado.getDouble("Precio") + "€" +
	                " | Stock: " + resultado.getInt("Cantidad_disponible")
	            );
	        }

	        if (!hayResultados) {
	        
	        	System.out.println("No hay juguetes disponibles en este stand/zona.");
	       
	        }

	    } catch (SQLException e) {
	     
	    	e.printStackTrace();
	        System.out.println("Error al obtener los juguetes del stand.");
	    
	    }
	
	}

	public static void mostrarVentasPorMes(Connection conexion, Scanner entrada) {
		
		if (conexion == null) {
		       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}

	    try {

	        System.out.println("Introduce el año (ejemplo: 2024):");
	        int anio = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Introduce el mes (1-12):");
	        int mes = entrada.nextInt();
	        entrada.nextLine();

	        String consulta = """
	                SELECT v.ID_Venta, v.ID_Empleado, v.ID_Juguete, v.ID_Stand, v.ID_Zona,
	                       v.Fecha, v.Monto, v.Tipo_pago
	                FROM venta v
	                WHERE YEAR(v.Fecha) = ? AND MONTH(v.Fecha) = ?
	                ORDER BY v.Fecha ASC
	                """;

	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, anio);
	        sentencia.setInt(2, mes);

	        ResultSet resultado = sentencia.executeQuery();

	        boolean hayResultados = false;

	        System.out.println("\n=== VENTAS DEL MES " + mes + "/" + anio + " ===");

	        while (resultado.next()) {
	           
	        	hayResultados = true;

	            System.out.println("-------------------------------");
	            System.out.println("ID Venta: " + resultado.getInt("ID_Venta"));
	            System.out.println("Empleado: " + resultado.getInt("ID_Empleado"));
	            System.out.println("Juguete: " + resultado.getInt("ID_Juguete"));
	            System.out.println("Stand: " + resultado.getInt("ID_Stand"));
	            System.out.println("Zona: " + resultado.getInt("ID_Zona"));
	            System.out.println("Fecha: " + resultado.getTimestamp("Fecha"));
	            System.out.println("Monto: " + resultado.getDouble("Monto") + " €");
	            System.out.println("Tipo de pago: " + resultado.getString("Tipo_pago"));
	       
	        }

	        if (!hayResultados) {
	        
	        	System.out.println("No se encontraron ventas en ese mes.");
	        
	        }

	    } catch (SQLException e) {
	     
	    	e.printStackTrace();
	    
	    }
	
	}

}

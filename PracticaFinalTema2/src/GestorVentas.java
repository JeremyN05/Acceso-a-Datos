import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorVentas {
	
	public static void empleadosConMasVentas(Connection conexion) {
	   
		if (conexion == null) {
	       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}

	    try {

	        String consulta = "SELECT e.Nombre, COUNT(v.ID_Venta) AS Total_Ventas " +
	                     "FROM venta v " +
	                     "JOIN empleado e ON v.ID_Empleado = e.ID_Empleado " +
	                     "GROUP BY e.ID_Empleado, e.Nombre " +
	                     "ORDER BY Total_Ventas DESC";

	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-5s %-25s %-10s%n", "Rank", "Empleado", "Ventas");
	        System.out.println("-----------------------------------------------");

	        int rank = 1;
	       
	        while (resultado.next()) {
	         
	        	String nombre = resultado.getString("Nombre");
	            int totalVentas = resultado.getInt("Total_Ventas");
	         
	            System.out.printf("%-5d %-25s %-10d%n", rank, nombre, totalVentas);
	            rank++;
	       
	        }

	    } catch (SQLException e) {
	      
	    	e.printStackTrace();
	   
	    }
	
	}

	public static void productosMasVendidos(Connection conexion) {
	  
		if (conexion == null) {
	     
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}

	    try {

	        String consulta = "SELECT j.Nombre, COUNT(v.ID_Venta) AS Total_Ventas " +
	                     "FROM venta v " +
	                     "JOIN juguete j ON v.ID_Juguete = j.ID_Juguete " +
	                     "GROUP BY j.ID_Juguete, j.Nombre " +
	                     "ORDER BY Total_Ventas DESC " +
	                     "LIMIT 5";

	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-5s %-30s %-10s%n", "Rank", "Producto", "Ventas");
	        System.out.println("----------------------------------------------------");

	        int rank = 1;
	       
	        while (resultado.next()) {
	         
	        	String nombre = resultado.getString("Nombre");
	            int totalVentas = resultado.getInt("Total_Ventas");
	            System.out.printf("%-5d %-30s %-10d%n", rank, nombre, totalVentas);
	            rank++;
	        
	        }

	    } catch (SQLException e) {
	    
	    	e.printStackTrace();
	    
	    }

	}

	public static void realizarDevolucion(Connection conexion, Scanner entrada) {

		if (conexion == null) {
		       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}
		
	    try {

	        System.out.println("\n--- JUGUETES DISPONIBLES ---");
	        String sqlJuguetes = "SELECT ID_Juguete, Nombre, Precio FROM juguete";
	        
	        PreparedStatement psJuguetes = conexion.prepareStatement(sqlJuguetes);
	        
	        ResultSet resultadoJuguetes = psJuguetes.executeQuery();

	        while (resultadoJuguetes.next()) {
	           
	        	System.out.println("ID: " + 
	        	resultadoJuguetes.getInt("ID_Juguete") + " | Nombre: " + 
	        	resultadoJuguetes.getString("Nombre") + " | Precio: " + 
	        	resultadoJuguetes.getDouble("Precio") + "€");
	       
	        }

	        System.out.println("\nIntroduce el ID del juguete a devolver:");
	        int idOriginal = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("\n--- STANDS Y ZONAS DISPONIBLES ---");
	        String sqlStands = "SELECT s.ID_Stand, s.ID_Zona, st.Nombre AS Stand, z.Nombre AS Zona " +
	                           "FROM stand st JOIN zona z ON st.ID_Zona = z.ID_Zona " +
	                           "JOIN stock s ON st.ID_Stand = s.ID_Stand";
	        
	        PreparedStatement psStands = conexion.prepareStatement(sqlStands);
	        
	        ResultSet resultadoStands = psStands.executeQuery();

	        while (resultadoStands.next()) {
	           
	        	System.out.println("Stand: " + 
	        			resultadoStands.getInt("ID_Stand") + " | Zona: " + 
	        			resultadoStands.getInt("ID_Zona") + " (" + 
	        			resultadoStands.getString("Stand") + " - " + 
	        			resultadoStands.getString("Zona") + ")");
	        }

	        System.out.println("\nIntroduce el ID del stand de origen:");
	        int standOrigen = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Introduce el ID de la zona de origen:");
	        int zonaOrigen = entrada.nextInt();
	        entrada.nextLine();


	        int stockOriginal = GestorStand.comprobarStockEnStand(conexion, idOriginal, standOrigen, zonaOrigen);
	        if (stockOriginal <= 0) {

	        	System.out.println("No hay stock en el stand de origen para devolver.");
	            return;
	        
	        }


	        System.out.println("Cantidad a devolver:");
	        int cantidad = entrada.nextInt();
	        entrada.nextLine();

	        if (cantidad <= 0 || cantidad > stockOriginal) {
	           
	        	System.out.println("Cantidad no válida. Stock disponible: " + stockOriginal);
	            return;
	        
	        }

	        double precioOriginal = obtenerPrecioJuguete(conexion, idOriginal);

	        System.out.println("\n--- JUGUETES DEL MISMO PRECIO (" + precioOriginal + "€) ---");
	        String sqlCompatibles = "SELECT ID_Juguete, Nombre FROM juguete WHERE Precio = ?";
	        
	        PreparedStatement psCompatibles = conexion.prepareStatement(sqlCompatibles);
	        psCompatibles.setDouble(1, precioOriginal);
	        
	        ResultSet rsCompatibles = psCompatibles.executeQuery();

	        while (rsCompatibles.next()) {
	            
	        	System.out.println("ID: " + 
	        	rsCompatibles.getInt("ID_Juguete") + " | Nombre: " + 
	        	rsCompatibles.getString("Nombre"));
	       
	        }

	        System.out.println("\nIngrese el ID del nuevo juguete a cambiar:");
	        int idNuevo = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("\n--- STANDS DONDE HAY STOCK DEL NUEVO JUGUETE ---");
	        String sqlStandsNuevo = "SELECT ID_Stand, ID_Zona, Cantidad_disponible " +
	                                "FROM stock WHERE ID_Juguete = ? AND Cantidad_disponible > 0";
	        PreparedStatement psStandsNuevo = conexion.prepareStatement(sqlStandsNuevo);
	        psStandsNuevo.setInt(1, idNuevo);
	        ResultSet rsStandsNuevo = psStandsNuevo.executeQuery();

	        while (rsStandsNuevo.next()) {
	            System.out.println("Stand: " + 
	        rsStandsNuevo.getInt("ID_Stand") + " | Zona: " + 
	        rsStandsNuevo.getInt("ID_Zona") + " | Stock: " + 
	        rsStandsNuevo.getInt("Cantidad_disponible"));
	        }

	        System.out.println("\nIngrese el ID del stand destino:");
	        int standDestino = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Ingrese el ID de la zona destino:");
	        int zonaDestino = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("\n--- EMPLEADOS DISPONIBLES ---");
	        String sqlEmpleados = "SELECT ID_Empleado, Nombre FROM empleado";
	        PreparedStatement psEmpleados = conexion.prepareStatement(sqlEmpleados);
	        ResultSet rsEmpleados = psEmpleados.executeQuery();

	        while (rsEmpleados.next()) {
	            System.out.println("Empleado ID: " + 
	        rsEmpleados.getInt("ID_Empleado") +" | Nombre: " + 
	        rsEmpleados.getString("Nombre"));
	        
	        }

	        System.out.println("\nIngrese su ID de empleado:");
	        int idEmpleado = entrada.nextInt();
	        entrada.nextLine();


	        System.out.println("Motivo de la devolución:");
	        String motivo = entrada.nextLine();

	        String consulta = "UPDATE stock SET Cantidad_disponible = Cantidad_disponible - ? " +
	                          "WHERE ID_Juguete = ? AND ID_Stand = ? AND ID_Zona = ?";
	       
	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        
	        sentencia.setInt(1, cantidad);
	        sentencia.setInt(2, idOriginal);
	        sentencia.setInt(3, standOrigen);
	        sentencia.setInt(4, zonaOrigen);
	        sentencia.executeUpdate();

	        String consulta2 = "UPDATE stock SET Cantidad_disponible = Cantidad_disponible + ? " +
	                           "WHERE ID_Juguete = ? AND ID_Stand = ? AND ID_Zona = ?";
	        
	        PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
	       
	        sentencia2.setInt(1, cantidad);
	        sentencia2.setInt(2, idNuevo);
	        sentencia2.setInt(3, standDestino);
	        sentencia2.setInt(4, zonaDestino);
	        sentencia2.executeUpdate();

	        String consulta3 = "INSERT INTO cambio (ID_Empleado, ID_Juguete_Original, ID_Juguete_Nuevo, Motivo, Fecha, " +
	                           "Stand_origen, ID_Zona_origen, Stand_destino, ID_Zona_destino) " +
	                           "VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?)";
	       
	        PreparedStatement sentencia3 = conexion.prepareStatement(consulta3);
	        
	        sentencia3.setInt(1, idEmpleado);
	        sentencia3.setInt(2, idOriginal);
	        sentencia3.setInt(3, idNuevo);
	        sentencia3.setString(4, motivo);
	        sentencia3.setInt(5, standOrigen);
	        sentencia3.setInt(6, zonaOrigen);
	        sentencia3.setInt(7, standDestino);
	        sentencia3.setInt(8, zonaDestino);
	        sentencia3.executeUpdate();

	        System.out.println("\nDevolución realizada y cambio registrado correctamente.");

	    } catch (SQLException e) {
	     
	    	e.printStackTrace();
	    
	    }
	
	}

	public static void realizarVenta(Connection conexion, Scanner entrada) {
	   
		if (conexion == null) {
		       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}
		
		try {
	        
	    	System.out.println("Introduce el ID del juguete a comprar:");
	        int idJuguete = entrada.nextInt();
	        entrada.nextLine();

	        String consultaStock = "SELECT s.ID_Stand, s.ID_Zona, s.Cantidad_disponible, st.Nombre AS NombreStand, z.Nombre AS NombreZona " +
	                               "FROM stock s " +
	                               "JOIN stand st ON s.ID_Stand = st.ID_Stand AND s.ID_Zona = st.ID_Zona " +
	                               "JOIN zona z ON s.ID_Zona = z.ID_Zona " +
	                               "WHERE s.ID_Juguete = ? AND s.Cantidad_disponible > 0";
	        
	        PreparedStatement sentencia= conexion.prepareStatement(consultaStock);
	        sentencia.setInt(1, idJuguete);
	        
	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-10s %-15s %-15s %-10s%n", "Stand ID", "Stand", "Zona", "Stock");
	        System.out.println("---------------------------------------------------");

	        boolean tieneStock = false;
	        
	        while (resultado.next()) {
	           
	        	int standID = resultado.getInt("ID_Stand");
	            int zonaID = resultado.getInt("ID_Zona");
	            String nombreStand = resultado.getString("NombreStand");
	            String nombreZona = resultado.getString("NombreZona");
	            int stockDisponible = resultado.getInt("Cantidad_disponible");

	            System.out.printf("%-10d %-15s %-15s %-10d%n", standID, nombreStand, nombreZona, stockDisponible);
	            tieneStock = true;
	        
	        }

	        if (!tieneStock) {
	          
	        	System.out.println("No hay stock disponible para este juguete en ningún stand.");
	            return;
	        
	        }

	        System.out.println("Indique el ID del stand donde desea comprar:");
	        int idStand = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Indique el ID de la zona del stand:");
	        int idZona = entrada.nextInt();
	        entrada.nextLine();

	        int stock = GestorStand.comprobarStockEnStand(conexion, idJuguete, idStand, idZona);

	        if (stock <= 0) {
	         
	        	System.out.println("No hay stock en ese stand.");
	            return;
	       
	        }

	        System.out.println("Cantidad a comprar:");
	        int cantidad = entrada.nextInt();
	        entrada.nextLine();

	        if (cantidad <= 0 || cantidad > stock) {
	            System.out.println("Cantidad no válida. Stock disponible: " + stock);
	            return;
	        }

	        System.out.println("Ingrese su ID de empleado:");
	        int idEmpleado = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Ingrese el tipo de pago (efectivo/tarjeta/paypal):");
	        String tipoPago = entrada.nextLine();

	        String consulta2 = "UPDATE stock SET Cantidad_disponible = Cantidad_disponible - ? " +
	                                "WHERE ID_Juguete = ? AND ID_Stand = ? AND ID_Zona = ?";
	        PreparedStatement sentecia2 = conexion.prepareStatement(consulta2);
	        sentecia2.setInt(1, cantidad);
	        sentecia2.setInt(2, idJuguete);
	        sentecia2.setInt(3, idStand);
	        sentecia2.setInt(4, idZona);

	        int filas = sentecia2.executeUpdate();
	        
	        if (filas <= 0) {
	           
	        	System.out.println("No se pudo actualizar el stock. Compruebe stand y zona.");
	            return;
	        
	        }

	        double precioUnitario = obtenerPrecioJuguete(conexion, idJuguete);
	        double montoTotal = precioUnitario * cantidad;

	        String consulta3 = "INSERT INTO venta (ID_Empleado, ID_Juguete, ID_Stand, ID_Zona, Fecha, Monto, Tipo_pago) " +
	                          "VALUES (?, ?, ?, ?, NOW(), ?, ?)";
	        
	        PreparedStatement sentencia3 = conexion.prepareStatement(consulta3);
	        
	        sentencia3.setInt(1, idEmpleado);
	        sentencia3.setInt(2, idJuguete);
	        sentencia3.setInt(3, idStand);
	        sentencia3.setInt(4, idZona);
	        sentencia3.setDouble(5, montoTotal);
	        sentencia3.setString(6, tipoPago);

	        sentencia3.executeUpdate();

	        System.out.println("Venta realizada correctamente. Total: " + montoTotal + " €");

	    } catch (SQLException e) {
	    
	    	e.printStackTrace();
	   
	    }
	
	}

	private static double obtenerPrecioJuguete(Connection conexion, int idJuguete) { 
		
		double precio = 0; 
		
		try { 
			
			String consulta = "SELECT Precio FROM juguete WHERE ID_Juguete = ?"; 
			PreparedStatement sentencia = conexion.prepareStatement(consulta); 
			sentencia.setInt(1, idJuguete); 
			
			ResultSet resultado = sentencia.executeQuery(); 
			
			if (resultado.next()) { 
				
				precio = resultado.getDouble("Precio"); 
				
			}
			
		} catch (SQLException e) { 
				
			e.printStackTrace(); 
		
		} 
		
		return precio;
		
	}
	
}

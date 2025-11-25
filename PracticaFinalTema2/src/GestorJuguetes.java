import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorJuguetes {
	
	public static int comprobarStock(Connection conexion, int id) {

	    if (conexion == null) {
	      
	    	System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return -2;
	    
	    }

	    String consulta = "SELECT Cantidad_en_stock FROM Juguete WHERE ID_Juguete = ?";

	    try {

	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, id);

	        ResultSet resultado = sentencia.executeQuery();

	        if (!resultado.next()) {
	       
	        	System.out.println("No se encontró ningún juguete con ese ID.");
	            return -1;
	       
	        }

	        int stock = resultado.getInt("Cantidad_en_stock");
	        System.out.println("Stock disponible: " + stock);

	        return stock;

	    } catch (SQLException e) {
	     
	    	e.printStackTrace();
	        return -2;
	   
	    }
	
	}
	
	public static int comprobarIDJuguete(Connection conexion, int id) {

	    if (conexion == null) {
	       
	    	System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return -1;
	   
	    }

	    String consulta = "SELECT * FROM Juguete WHERE ID_Juguete = ?";

	    try {

	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, id);

	        ResultSet resultado = sentencia.executeQuery();

	        if (!resultado.next()) {
	          
	        	System.out.println("No se encontró ningún juguete con ese ID.");
	            return -1;
	       
	        }

	        System.out.printf("Juguete encontrado: ID: %d, Nombre: %s, Descripcion: %s, Precio: %.2f, Stock: %d, Categoria: %s %n",
	               
	        		resultado.getInt("ID_Juguete"),
	                resultado.getString("Nombre"),
	                resultado.getString("Descripcion"),
	                resultado.getDouble("Precio"),
	                resultado.getInt("Cantidad_en_stock"),
	                resultado.getString("Categoria")
	        );

	        return id;

	    } catch (SQLException e) {
	       
	    	e.printStackTrace();
	        return -1;
	    
	    }
	
	}
	
	public static void eliminarJuguete(Connection conexion, Scanner entrada) {

	    if (conexion == null) {
	        System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    }

	    int codigo = 0;

	    System.out.println("Introduce el ID del juguete a eliminar:");
	    codigo = entrada.nextInt();
	    entrada.nextLine();

	    String consulta = "SELECT * FROM Juguete WHERE ID_Juguete = ?";

	    try {
	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, codigo);
	        ResultSet resultado = sentencia.executeQuery();

	        if (!resultado.next()) {
	            System.out.println("No se encontró ningún juguete con ese ID.");
	            return;
	        }

	        System.out.printf("Juguete a eliminar: ID=%d, Nombre=%s, Descripcion=%s, Precio=%.2f, Stock=%d, Categoria=%s%n",
	                resultado.getInt("ID_Juguete"),
	                resultado.getString("Nombre"),
	                resultado.getString("Descripcion"),
	                resultado.getDouble("Precio"),
	                resultado.getInt("Cantidad_en_stock"),
	                resultado.getString("Categoria")
	        );

	        System.out.println("¿Está seguro de eliminar este juguete? (S/N)");
	        String confirmacion = entrada.nextLine().trim().toUpperCase();

	        if (!confirmacion.equals("S")) {
	          
	        	System.out.println("Eliminación cancelada.");
	            return;
	       
	        }

	        String consultaEliminar = "DELETE FROM juguete WHERE ID_Juguete = ?";
	        try (PreparedStatement sentencia2 = conexion.prepareStatement(consultaEliminar)) {
	           
	        	sentencia2.setInt(1, codigo);
	            int filasAfectadas = sentencia2.executeUpdate();

	            if (filasAfectadas > 0) {
	           
	            	System.out.println("Juguete eliminado correctamente.");
	          
	            } else {
	          
	            	System.out.println("No se pudo eliminar el juguete.");
	           
	            }

	        } catch (SQLException e) {
	        
	        	e.printStackTrace();
	        
	        }

	    } catch (SQLException e) {
	     
	    	e.printStackTrace();
	   
	    }
	
	}

	private static void modificarCategoriaJuguete(Connection conexion, Scanner entrada) {

	    if (conexion == null) {
	      
	    	System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
	    }

	    int codigo = 0;
	    String nuevaCategoria;

	    System.out.println("Introduce el ID del juguete a modificar:\n");
	    codigo = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("Juguete elegido:\n");

	    String consulta = "SELECT * FROM juguete WHERE ID_Juguete = ?";

	    try {
	       
	    	PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, codigo);

	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-15s %-20s %-20s %-10s %-10s %-15s%n", "ID", "Nombre", "Descripcion", "Precio", "Stock", "Categoria");
	        System.out.println("---------------------------------------------------------------------------------------------------");

	        while (resultado.next()) {
	           
	        	int id = resultado.getInt("ID_Juguete");
	            String nombre = resultado.getString("Nombre");
	            String descripcion = resultado.getString("Descripcion");
	            double precio = resultado.getDouble("Precio");
	            int stock = resultado.getInt("Cantidad_en_stock");
	            String categoria = resultado.getString("Categoria");

	            System.out.printf("%-15s %-20s %-20s %-10.2f %-10s %-15s%n", id, nombre, descripcion, precio, stock, categoria);
	       
	        }

	        do {
	        	
	            System.out.println("\n");
	        	System.out.println("Ingrese la nueva categoría del juguete:");
	            nuevaCategoria = entrada.nextLine().trim();
	           
	            if (nuevaCategoria.isEmpty()) {
	            
	            	System.out.println("\n");
	            	System.out.println("La categoría no puede estar vacía. Intente nuevamente.");

	            }
	        
	        } while (nuevaCategoria.isEmpty());

	        String consulta2 = "UPDATE juguete SET Categoria = ? WHERE ID_Juguete = ?";

	        try (PreparedStatement sentencia2 = conexion.prepareStatement(consulta2)) {
	         
	        	sentencia2.setString(1, nuevaCategoria);
	            sentencia2.setInt(2, codigo);

	            int filasAfectadas = sentencia2.executeUpdate();

	            if (filasAfectadas > 0) {
	          
	            	System.out.println("\n");
	            	System.out.println("Categoría del juguete actualizada correctamente.");
	         
	            } else {
	         
	            	System.out.println("\n");
	            	System.out.println("No se encontró el juguete con ese ID.");
	         
	            }

	        } catch (SQLException e) {
	        
	        	e.printStackTrace();
	        
	        }

	    } catch (SQLException e) {
	      
	    	e.printStackTrace();
	    
	    }
	
	}

	
	private static void modificarStockJuguete(Connection conexion, Scanner entrada) {

	    if (conexion == null) {
	        System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    }

	    int codigo = 0;
	    int nuevoStock = -1;

	    System.out.println("Introduce el ID del juguete a modificar:");
	    codigo = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("Juguete elegido:");

	    String consulta = "SELECT * FROM juguete WHERE ID_Juguete = ?";

	    try {
	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, codigo);
	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-15s %-20s %-20s %-10s %-10s %-15s%n", "ID", "Nombre", "Descripcion", "Precio", "Stock", "Categoria");
	        System.out.println("---------------------------------------------------------------------------------------------------");

	        while (resultado.next()) {
	            int id = resultado.getInt("ID_Juguete");
	            String nombre = resultado.getString("Nombre");
	            String descripcion = resultado.getString("Descripcion");
	            double precio = resultado.getDouble("Precio");
	            int stock = resultado.getInt("Cantidad_en_stock");
	            String categoria = resultado.getString("Categoria");

	            System.out.printf("%-15s %-20s %-20s %-10.2f %-10s %-15s%n", id, nombre, descripcion, precio, stock, categoria);
	        }

	        while (nuevoStock < 0) {
	            try {
	                System.out.println("Ingrese el nuevo stock del juguete:");
	                nuevoStock = Integer.parseInt(entrada.nextLine());

	                if (nuevoStock < 0) {
	                    System.out.println("El stock no puede ser negativo. Intente nuevamente.");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Stock inválido. Introduzca un número entero válido.");
	            }
	        }

	        // Pedir stand y zona
	        System.out.println("Ingrese el ID del stand:");
	        int idStand = entrada.nextInt();
	        entrada.nextLine();
	        System.out.println("Ingrese el ID de la zona:");
	        int idZona = entrada.nextInt();
	        entrada.nextLine();

	        // Actualizar stock en la tabla juguete
	        String consultaJuguete = "UPDATE juguete SET Cantidad_en_stock = ? WHERE ID_Juguete = ?";
	        try (PreparedStatement sentencia2 = conexion.prepareStatement(consultaJuguete)) {
	            sentencia2.setInt(1, nuevoStock);
	            sentencia2.setInt(2, codigo);
	            int filasAfectadas = sentencia2.executeUpdate();
	            if (filasAfectadas > 0) {
	                System.out.println("Stock del juguete actualizado correctamente en la tabla Juguete.");
	            } else {
	                System.out.println("No se encontró el juguete con ese ID.");
	            }
	        }

	        // Actualizar stock en la tabla stock
	        String consultaStock = "INSERT INTO stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) " +
	                "VALUES (?, ?, ?, ?) " +
	                "ON DUPLICATE KEY UPDATE Cantidad_disponible = ?";
	        try (PreparedStatement sentencia3 = conexion.prepareStatement(consultaStock)) {
	            sentencia3.setInt(1, idStand);
	            sentencia3.setInt(2, idZona);
	            sentencia3.setInt(3, codigo);
	            sentencia3.setInt(4, nuevoStock);
	            sentencia3.setInt(5, nuevoStock);

	            sentencia3.executeUpdate();
	            System.out.println("Stock del juguete actualizado correctamente en la tabla Stock.");

	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private static void modificarPrecioJuguete(Connection conexion, Scanner entrada) {

	    if (conexion == null) {
	        
	    	System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    }

	    int codigo = 0;
	    double nuevoPrecio = -1;

	    System.out.println("Introduce el ID del juguete a modificar:\n");
	    codigo = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("Juguete elegido:\n");

	    String consulta = "SELECT * FROM juguete WHERE ID_Juguete = ?";

	    try {
	        
	    	PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, codigo);

	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-15s %-20s %-20s %-10s %-10s %-15s%n", "ID", "Nombre", "Descripcion", "Precio", "Stock", "Categoria");
	        System.out.println("---------------------------------------------------------------------------------------------------");

	        while (resultado.next()) {
	            
	        	int id = resultado.getInt("ID_Juguete");
	            String nombre = resultado.getString("Nombre");
	            String descripcion = resultado.getString("Descripcion");
	            double precio = resultado.getDouble("Precio");
	            int stock = resultado.getInt("Cantidad_en_stock");
	            String categoria = resultado.getString("Categoria");

	            System.out.printf("%-15s %-20s %-20s %-10.2f %-10s %-15s%n", id, nombre, descripcion, precio, stock, categoria);
	        
	        }

	        while (nuevoPrecio < 0) {
	            
	        	try {
	                
	        		System.out.println("\n");
	        		System.out.println("Ingrese el nuevo precio del juguete:");
	        		nuevoPrecio = Double.parseDouble(entrada.nextLine().replace(",", "."));
	                
	        		if (nuevoPrecio < 0) {
	        			
	        			System.out.println("\n");
	                	System.out.println("El precio no puede ser negativo. Intente nuevamente.");
	                
	                }
	           
	        	} catch (NumberFormatException e) {
	        		
	        		System.out.println("\n");
	        		System.out.println("Precio inválido. Introduzca un número válido.");
	            
	        	}
	        
	        }

	        String consulta2 = "UPDATE juguete SET Precio = ? WHERE ID_Juguete = ?";

	        try (PreparedStatement sentencia2 = conexion.prepareStatement(consulta2)) {
	           
	        	sentencia2.setDouble(1, nuevoPrecio);
	            sentencia2.setInt(2, codigo);

	            int filasAfectadas = sentencia2.executeUpdate();

	            if (filasAfectadas > 0) {
	            	
	            	System.out.println("\n");
	            	System.out.println("Precio del juguete actualizado correctamente.");

	            } else {
	            
	            	System.out.println("\n");
	            	System.out.println("No se encontró el juguete con ese ID.");
	            
	            }

	        } catch (SQLException e) {
	          
	        	e.printStackTrace();
	        
	        }
	    
	    } catch (SQLException e) {
	      
	    	e.printStackTrace();
	   
	    }
	
	}
	
	private static void modificarDescripcionJuguete(Connection conexion, Scanner entrada) {

	    if (conexion == null) {
	        
	    	System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    
	    }

	    int codigo = 0;
	    String nuevaDescripcion;

	    System.out.println("Introduce el ID del juguete a modificar:\n");
	    codigo = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("Juguete elegido:\n");

	    String consulta = "SELECT * FROM juguete WHERE ID_Juguete = ?";

	    try {
	        
	    	PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, codigo);

	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-15s %-20s %-20s %-10s %-10s %-15s%n", "ID", "Nombre", "Descripcion", "Precio", "Stock", "Categoria");
	        System.out.println("---------------------------------------------------------------------------------------------------");

	        while (resultado.next()) {
	           
	        	int id = resultado.getInt("ID_Juguete");
	            String nombre = resultado.getString("Nombre");
	            String descripcion = resultado.getString("Descripcion");
	            double precio = resultado.getDouble("Precio");
	            int stock = resultado.getInt("Cantidad_en_stock");
	            String categoria = resultado.getString("Categoria");

	            System.out.printf("%-15s %-20s %-20s %-10.2f %-10s %-15s%n", id, nombre, descripcion, precio, stock, categoria);
	        
	        }

	        System.out.println("\n");
	        System.out.println("Ingrese la nueva descripción del juguete:");
	        nuevaDescripcion = entrada.nextLine();

	        String consulta2 = "UPDATE juguete SET Descripcion = ? WHERE ID_Juguete = ?";

	        try (PreparedStatement sentencia2 = conexion.prepareStatement(consulta2)) {
	           
	        	sentencia2.setString(1, nuevaDescripcion);
	            sentencia2.setInt(2, codigo);

	            int filasAfectadas = sentencia2.executeUpdate();

	            if (filasAfectadas > 0) {
	            	
	            	System.out.println("\n");
	            	System.out.println("Descripción del juguete actualizada correctamente.");
	           
	            } else {
	            
	            	System.out.println("No se encontró el juguete con ese ID.");
	           
	            }
	       
	        } catch (SQLException e) {
	        
	        	e.printStackTrace();
	       
	        }
	    
	    } catch (SQLException e) {
	    
	    	e.printStackTrace();
	    
	    }
	
	}

	
	private static void modificarNombreJuguete(Connection conexion, Scanner entrada) {
		
		if (conexion == null) {
            
		       System.out.println("No se pudo establecer la conexión con la base de datos.");
		               
		       return;
		            
		   	}
		
		int codigo = 0;
		String nombre;

		System.out.println("Introduce el id del juguete a modificar:");
		codigo = entrada.nextInt();
		entrada.nextLine();

		System.out.println("\n");
		
		System.out.println("Juguete elegido: \n");
		
		String consulta = "Select * from juguete WHERE ID_Juguete = ?";
		
		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			sentencia.setInt(1, codigo);
			
			ResultSet resultado = sentencia.executeQuery();
			
			System.out.printf("%-15s %-20s %-20s %-10s %-10s %-15s%n", "ID", "Nombre", "Descripcion", "Precio", "Stock", "Categoria");
			System.out.println("---------------------------------------------------------------------------------------------------");
		
			while(resultado.next()) {
				
				int id = resultado.getInt("ID_Juguete");
				String nombre2 = resultado.getString("Nombre");
				String descripcion = resultado.getString("Descripcion");
				Double precio = resultado.getDouble("Precio");
				int stock = resultado.getInt("Cantidad_en_stock");
				String categoria = resultado.getString("Categoria");
				
				System.out.printf("%-15s %-20s %-20s %-10.2f %-10s %-15s%n\n", id, nombre2, descripcion, precio, stock, categoria);
				
			}
			
			System.out.println("Ingrese el nuevo nombre del juguete:");
			nombre = entrada.nextLine();
			
			String consulta2 = "UPDATE juguete SET Nombre = ? WHERE ID_Juguete = ?";
			
			try (PreparedStatement sentencia2 = conexion.prepareStatement(consulta2)) {
			   
				sentencia2.setString(1, nombre);
			    sentencia2.setInt(2, codigo);

			    int filasAfectadas = sentencia2.executeUpdate();
			    
			    if (filasAfectadas > 0) {
			   
			    	System.out.println("Juguete actualizado correctamente.");
			    
			    } else {
			    
			    	System.out.println("No se encontró el juguete con ese ID.");
			
			    }
			
			} catch (SQLException e) {
			
				e.printStackTrace();
			
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		
	
	}
	
	public static void modificarJuguetes(Connection conexion, Scanner entrada) {
		
		if (conexion == null) {
            
		       System.out.println("No se pudo establecer la conexión con la base de datos.");
		               
		       return;
		            
		   	}
		
		int eleccion = 0;
		
		do {
		
			System.out.println("----------MENÚ MODIFICAR JUGUETE----------");
			System.out.println("------------------------------------------");
		
			System.out.println("1. Modificar nombre del juguete.");
			System.out.println("2. Modificar descripcion del juguete.");
			System.out.println("3. Modificar el precio del juguete.");
			System.out.println("4. Modificar el stock del juguete.");
			System.out.println("5. Modificar la categoria del juguete.");
			
			eleccion = entrada.nextInt();
			entrada.nextLine();
		
		}while(eleccion < 0 || eleccion > 5);
		
		switch (eleccion) {
		
		case 1:
		
			modificarNombreJuguete(conexion, entrada);
			break;

		case 2:
			
			modificarDescripcionJuguete(conexion, entrada);
			break;
			
		case 3:
			
			modificarPrecioJuguete(conexion, entrada);
			break;
			
		case 4:
			
			modificarStockJuguete(conexion, entrada);
			break;
			
		case 5:
			
			modificarCategoriaJuguete(conexion, entrada);
			break;
			
		default:
			break;
		
		}
		
	}

	public static void crearJuguete(Connection conexion, Scanner entrada) {

	    if (conexion == null) {
	     
	    	System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
	    }

	    try {

	        String nombre, descripcion, categoria;
	        double precio;
	        int cantidad_stock = 0;
	        int idStand = 0;
	        int idZona = 0;

	        System.out.println("Introduzca el nombre del juguete:");
	        nombre = entrada.nextLine().trim();

	        System.out.println("Introduzca la descripción del juguete:");
	        descripcion = entrada.nextLine().trim();

	        System.out.println("Introduzca el precio del juguete:");
	        precio = Double.parseDouble(entrada.nextLine().replace(",", "."));

	        System.out.println("Introduzca el stock del juguete:");
	        cantidad_stock = Integer.parseInt(entrada.nextLine());

	        System.out.println("Introduzca la categoría del juguete:");
	        categoria = entrada.nextLine().trim();

	        String consulta = "INSERT INTO juguete (Nombre, Descripcion, Precio, Cantidad_en_stock, Categoria) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement sentencia = conexion.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);

	        sentencia.setString(1, nombre);
	        sentencia.setString(2, descripcion);
	        sentencia.setDouble(3, precio);
	        sentencia.setInt(4, cantidad_stock);
	        sentencia.setString(5, categoria);

	        int filasAfectadas = sentencia.executeUpdate();

	        if (filasAfectadas > 0) {
	           
	        	System.out.println("El juguete se ha creado correctamente.");

	            ResultSet resultado = sentencia.getGeneratedKeys();
	            int idJuguete = 0;
	            
	            if (resultado.next()) {
	            
	            	idJuguete = resultado.getInt(1);
	           
	            }

	            System.out.println("\nZonas y stands disponibles:");
	            String consultaZonasStands = "SELECT s.ID_Stand, z.ID_Zona, s.Nombre AS Stand, z.Nombre AS Zona " + "FROM stand s " + "JOIN zona z ON s.ID_Zona = z.ID_Zona";
	            
	            PreparedStatement sentencia2 = conexion.prepareStatement(consultaZonasStands);
	            ResultSet resultado2 = sentencia2.executeQuery();
	            
	            System.out.printf("%-10s %-10s %-20s %-20s%n", "ID_Stand", "ID_Zona", "Stand", "Zona");
	            System.out.println("--------------------------------------------------------");
	           
	            while (resultado2.next()) {
	               
	            	System.out.printf("%-10d %-10d %-20s %-20s%n",
	                
	            			resultado2.getInt("ID_Stand"),
	            			resultado2.getInt("ID_Zona"),
	            			resultado2.getString("Stand"),
	            			resultado2.getString("Zona"));
	           
	            }

	            System.out.println("\nIntroduce el ID del stand donde estará el juguete:");
	            idStand = entrada.nextInt();
	            entrada.nextLine();

	            System.out.println("Introduce el ID de la zona donde estará el stand:");
	            idZona = entrada.nextInt();
	            entrada.nextLine();

	            String consultaStock = "INSERT INTO stock (ID_Stand, ID_Zona, ID_Juguete, Cantidad_disponible) VALUES (?, ?, ?, ?)";
	            PreparedStatement sentenciaStock = conexion.prepareStatement(consultaStock);
	            sentenciaStock.setInt(1, idStand);
	            sentenciaStock.setInt(2, idZona);
	            sentenciaStock.setInt(3, idJuguete);
	            sentenciaStock.setInt(4, cantidad_stock);

	            try {
	            
	            	sentenciaStock.executeUpdate();
	                System.out.println("Stock registrado correctamente en el stand y zona seleccionados.");
	            
	            } catch (SQLException e) {
	            
	            	System.out.println("Error al registrar el stock: revisa que el stand y la zona existan.");
	                e.printStackTrace();
	            
	            }

	        } else {
	        
	        	System.out.println("Error, no se pudo crear el juguete.");
	        
	        }

	    } catch (SQLException e) {
	     
	    	e.printStackTrace();
	   
	    }

	}

	
}
	

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorEmpleados {
	
	private static void modificarFechaIngreso(Connection conexion, Scanner entrada) {
	    
	    if (conexion == null) {
	        System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    }

	    int codigo = 0;
	    String fechaStr;

	    System.out.println("Introduce el id del empleado a modificar:");
	    codigo = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("\nEmpleado elegido: \n");

	    String consulta = "SELECT * FROM empleado WHERE ID_Empleado = ?";

	    try {
	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, codigo);

	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-15s %-20s %-20s %-20s%n", "ID", "Nombre", "Cargo", "Fecha Ingreso");
	        System.out.println("--------------------------------------------------------------------------");

	        if (resultado.next()) {

	            int id = resultado.getInt("ID_Empleado");
	            String nombre = resultado.getString("Nombre");
	            String cargo = resultado.getString("Cargo");
	            Date fecha = resultado.getDate("Fecha_ingreso");

	            System.out.printf("%-15s %-20s %-20s %-20s%n\n", id, nombre, cargo, fecha);

	        } else {
	            System.out.println("No existe un empleado con ese ID.");
	            return;
	        }

	        System.out.println("Ingrese la nueva fecha de ingreso (YYYY-MM-DD):");
	        fechaStr = entrada.nextLine().trim();

	        java.sql.Date fechaNueva;

	        try {
	            fechaNueva = java.sql.Date.valueOf(fechaStr);
	        } catch (IllegalArgumentException e) {
	            System.out.println("Formato inválido. Debe ser YYYY-MM-DD.");
	            return;
	        }

	        String consulta2 = "UPDATE empleado SET Fecha_ingreso = ? WHERE ID_Empleado = ?";

	        try (PreparedStatement sentencia2 = conexion.prepareStatement(consulta2)) {
	            
	            sentencia2.setDate(1, fechaNueva);
	            sentencia2.setInt(2, codigo);

	            int filasAfectadas = sentencia2.executeUpdate();

	            if (filasAfectadas > 0) {
	                System.out.println("Empleado actualizado correctamente.");
	            } else {
	                System.out.println("No se pudo actualizar el empleado.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	private static void modificarCargo(Connection conexion, Scanner entrada) {
	    
	    if (conexion == null) {
	        System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    }

	    int codigo = 0;
	    String cargo;

	    System.out.println("Introduce el id del empleado a modificar:");
	    codigo = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("\nEmpleado elegido: \n");

	    String consulta = "SELECT * FROM empleado WHERE ID_Empleado = ?";

	    try {
	        
	    	PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, codigo);

	        ResultSet resultado = sentencia.executeQuery();

	        System.out.printf("%-15s %-20s %-20s %-20s%n", "ID", "Nombre", "Cargo", "Fecha Ingreso");
	        System.out.println("--------------------------------------------------------------------------");

	        if (resultado.next()) {

	            int id = resultado.getInt("ID_Empleado");
	            String nombre = resultado.getString("Nombre");
	            String cargoActual = resultado.getString("Cargo");
	            Date fecha = resultado.getDate("Fecha_ingreso");

	            System.out.printf("%-15s %-20s %-20s %-20s%n\n", id, nombre, cargoActual, fecha);

	        } else {
	          
	        	System.out.println("No existe un empleado con ese ID.");
	            return;
	       
	        }

	        System.out.println("Ingrese el nuevo cargo del empleado:");
	        cargo = entrada.nextLine().trim();

	        String consulta2 = "UPDATE empleado SET Cargo = ? WHERE ID_Empleado = ?";

	        try (PreparedStatement sentencia2 = conexion.prepareStatement(consulta2)) {
	            
	            sentencia2.setString(1, cargo);
	            sentencia2.setInt(2, codigo);

	            int filasAfectadas = sentencia2.executeUpdate();

	            if (filasAfectadas > 0) {
	            
	            	System.out.println("Empleado actualizado correctamente.");
	           
	            } else {
	            
	            	System.out.println("No se pudo actualizar el empleado.");
	           
	            }

	        } catch (SQLException e) {
	          
	        	e.printStackTrace();
	       
	        }

	    } catch (SQLException e) {
	        
	    	e.printStackTrace();
	    
	    }
	
	}

	
	private static void modificarNombre(Connection conexion, Scanner entrada) {

	    if (conexion == null) {
	       
	    	System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
	    }

	    System.out.println("Introduce el id del empleado a modificar:\n");
	    int codigo = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("Empleado elegido:\n");

	    String consulta = "SELECT * FROM empleado WHERE ID_Empleado = ?";

	    try {
	        
	    	PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, codigo);

	        ResultSet resultado = sentencia.executeQuery();

	        if (!resultado.next()) {
	            
	        	System.out.println("No existe un empleado con ese ID.");
	            return;
	       
	        }

	        System.out.printf("%-10s %-20s %-20s %-15s%n", "ID", "Nombre", "Cargo", "Fecha Ingreso");
	        System.out.println("------------------------------------------------------------------");

	        int id = resultado.getInt("ID_Empleado");
	        String nombreActual = resultado.getString("Nombre");
	        String cargo = resultado.getString("Cargo");
	        Date fechaIngreso = resultado.getDate("Fecha_ingreso");

	        System.out.printf("%-10d %-20s %-20s %-15s%n", id, nombreActual, cargo, fechaIngreso);

	        System.out.println("\nIngrese el nuevo nombre:");
	        String nuevoNombre = entrada.nextLine().trim();

	        String consulta2 = "UPDATE empleado SET Nombre = ? WHERE ID_Empleado = ?";

	        try (PreparedStatement sentencia2 = conexion.prepareStatement(consulta2)) {

	            sentencia2.setString(1, nuevoNombre);
	            sentencia2.setInt(2, codigo);

	            int filasAfectadas = sentencia2.executeUpdate();

	            if (filasAfectadas > 0) {
	           
	            	System.out.println("Empleado actualizado correctamente.");
	            
	            } else {
	            
	            	System.out.println("No se pudo actualizar el empleado.");
	            
	            }

	        }
	   
	    } catch (SQLException e) {
	       
	    	e.printStackTrace();
	    
	    }
	
	}
	
	public static void modificarEmpleado(Connection conexion, Scanner entrada) {
		
		if (conexion == null) {
            
		       System.out.println("No se pudo establecer la conexión con la base de datos.");
		               
		       return;
		            
		}
		
		int eleccion = 0;
		
		do {

			System.out.println("----------MENÚ MODIFICAR JUGUETE----------");
			System.out.println("------------------------------------------");
		
			System.out.println("1. Modificar nombre del empleado.");
			System.out.println("2. Modificar el cargo del empleado.");
			System.out.println("3. Modificar la fecha de ingreso del empleado.");
			
			eleccion = entrada.nextInt();
			entrada.nextLine();
			
		}while(eleccion < 0 || eleccion > 3);
		
		switch (eleccion) {
		
		case 1:
			modificarNombre(conexion, entrada);
			break;
			
		case 2:
			modificarCargo(conexion, entrada);
			break;
			
		case 3:
			modificarFechaIngreso(conexion, entrada);
			break;

		default:
			break;
		
		}
		
	}

	public static void registrarEmpleado(Connection conexion, Scanner entrada) {
		
		if (conexion == null) {
	        
	    	System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	    
    	}
		
    	try {
    	
    		String nombre;
    		String cargo;
    		String fecha_ingreso;
    		
    		System.out.println("Ingrese el nombre del nuevo empleado: ");
    		nombre = entrada.nextLine().trim();
    		
    		System.out.println("Ingrese el cargo del empleado: ");
    		cargo = entrada.nextLine().trim();
    	
    		System.out.println("Ingrese la fecha de ingreso (YYYY-MM-DD) del empleado: ");
    		fecha_ingreso = entrada.nextLine().trim();
    		
    		java.sql.Date fecha = null;

    		try {
    		    
    			fecha = java.sql.Date.valueOf(fecha_ingreso);
    		
    		} catch (IllegalArgumentException e) {
    		   
    			System.out.println("Formato de fecha inválido. Debe ser exactamente YYYY-MM-DD");
    		    return;
    		
    		}
    		
    		String consulta = "INSERT INTO empleado (Nombre, Cargo, Fecha_ingreso) VALUES (?, ?, ?)";
    		
    		try(PreparedStatement sentencia = conexion.prepareStatement(consulta)){
    			
    			try {
    				
    				sentencia.setString(1, nombre);
    				sentencia.setString(2, cargo);
    				sentencia.setDate(3, fecha);
    				
    				int filasAfectadas = sentencia.executeUpdate();
    			    
					if (filasAfectadas > 0) {
			        
						System.out.println("El empleado se ha creado correctamente.");
			    
					} else {
			        
						System.out.println("Error, no se pudo crear el empleado.");
			    
					}
    				
    			}catch(SQLException e) {
        			
        			e.printStackTrace();
        			
        		}
    			
    		}
    	
    	}catch (SQLException e) {
    	
    		e.printStackTrace();
    	
    	}
		
	}
	
}

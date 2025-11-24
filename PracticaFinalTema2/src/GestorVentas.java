import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorVentas {

	public static void realizarVenta(Connection conexion, Scanner entrada) {
		
		int id = 0;
		int stock = 0;
		
		System.out.println("Introduce el id del juguete a comprar: ");
		id = entrada.nextInt();
		entrada.nextLine();
		
		int idSeleccionado = GestorJuguetes.comprobarIDJuguete(conexion, id);
		
		if(idSeleccionado == -1) {
			
			System.out.println("Error, no existe el id indicado");
			
		}
		
		GestorJuguetes.comprobarStock(conexion, idSeleccionado);
		
		if (stock == -1) {
		    
			System.out.println("No existe el juguete.");
		    return;
		
		}

		if (stock <= 0) {
		   
			System.out.println("No hay stock para la venta.");
		    return;
		
		} 
		
		System.out.println("Indique la cantidad que quiere comprar: ");
		int cantidad = entrada.nextInt();
		entrada.nextLine();
		
		String consulta = "UPDATE Juguete SET Cantidad_en_stock = Cantidad_en_stock - ? WHERE ID_Juguete = ?";
		
	    if (cantidad <= 0) {
	       
	    	System.out.println("Cantidad no válida.");
	        return;
	    
	    }

	    if (cantidad > stock) {
	       
	    	System.out.println("No hay suficiente stock. Stock disponible: " + stock);
	        return;
	    
	    }
	    
	    try {

	        PreparedStatement sentencia = conexion.prepareStatement(consulta);

	        sentencia.setInt(1, cantidad);
	        sentencia.setInt(2, idSeleccionado);

	        int filasAfectadas = sentencia.executeUpdate();

	        if (filasAfectadas > 0) {
	        
	        	System.out.println("Venta realizada correctamente.");
	            System.out.println("Se han vendido " + cantidad + " unidades.");
	            System.out.println("Stock actualizado.");
	       
	        } else {
	        
	        	System.out.println("No se pudo realizar la venta.");
	       
	        }

	    } catch (SQLException e) {
	       
	    	e.printStackTrace();
	    
	    }
		
	}
	
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	    if (idSeleccionado == -1) {
	        System.out.println("Error, no existe el id indicado");
	        return;
	    }

	    stock = GestorJuguetes.comprobarStock(conexion, idSeleccionado);

	    if (stock <= 0) {
	        System.out.println("No hay stock para la venta.");
	        return;
	    }

	    System.out.println("Indique la cantidad que quiere comprar: ");
	    int cantidad = entrada.nextInt();
	    entrada.nextLine();

	    if (cantidad <= 0) {
	        System.out.println("Cantidad no válida.");
	        return;
	    }

	    if (cantidad > stock) {
	        System.out.println("No hay suficiente stock. Stock disponible: " + stock);
	        return;
	    }

	    System.out.println("Ingrese el id del stand donde se realiza la venta:");
	    int idStand = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("Ingrese su ID de empleado:");
	    int idEmpleado = entrada.nextInt();
	    entrada.nextLine();

	    System.out.println("Ingrese el tipo de pago (efectivo/tarjeta/paypal):");
	    String tipoPago = entrada.nextLine();

	    try {

	        String consulta = "UPDATE stock SET Cantidad = Cantidad - ? WHERE JUGUETE_idJuguete = ? AND STAND_idStand = ?";
	        PreparedStatement sentencia = conexion.prepareStatement(consulta);
	        sentencia.setInt(1, cantidad);
	        sentencia.setInt(2, idSeleccionado);
	        sentencia.setInt(3, idStand);

	        int filasAfectadas = sentencia.executeUpdate();

	        if (filasAfectadas > 0) {
	            System.out.println("Venta realizada correctamente.");
	            System.out.println("Se han vendido " + cantidad + " unidades.");
	            System.out.println("Stock actualizado.");

	            String sqlVenta = "INSERT INTO venta (Fecha, Monto, tipo_pago, EMPLEADO_idEMPLEADO, stock_STAND_idStand, stock_JUGUETE_idJuguete) " +
	                              "VALUES (NOW(), ?, ?, ?, ?, ?)";
	            PreparedStatement stmtVenta = conexion.prepareStatement(sqlVenta);

	            double precioUnitario = obtenerPrecioJuguete(conexion, idSeleccionado);
	            double montoTotal = precioUnitario * cantidad;

	            stmtVenta.setDouble(1, montoTotal);
	            stmtVenta.setString(2, tipoPago);
	            stmtVenta.setInt(3, idEmpleado);
	            stmtVenta.setInt(4, idStand);
	            stmtVenta.setInt(5, idSeleccionado);

	            stmtVenta.executeUpdate();

	        } else {
	            System.out.println("No se pudo realizar la venta. Verifique el stand o el stock.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private static double obtenerPrecioJuguete(Connection conexion, int idJuguete) {
	    double precio = 0;
	   
	    try {
	        
	    	String consulta = "SELECT Precio FROM juguete WHERE ID_Juguete = ?";
	        PreparedStatement stmt = conexion.prepareStatement(consulta);
	        stmt.setInt(1, idJuguete);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	         
	        	precio = rs.getDouble("Precio");
	       
	        }
	   
	    } catch (SQLException e) {
	     
	    	e.printStackTrace();
	   
	    }
	   
	    return precio;
	
	}

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorDatosTienda {

	public static void obtenerJuguetesEnStand(Connection conexion, Scanner entrada) {

	    try {
	        System.out.println("Introduce el ID del stand:");
	        int idStand = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Introduce el ID de la zona:");
	        int idZona = entrada.nextInt();
	        entrada.nextLine();

	        String sql = 
	            "SELECT j.ID_Juguete, j.Nombre, j.Precio, s.Cantidad_disponible " +
	            "FROM stock s " +
	            "JOIN juguete j ON s.ID_Juguete = j.ID_Juguete " +
	            "WHERE s.ID_Stand = ? AND s.ID_Zona = ? AND s.Cantidad_disponible > 0";

	        PreparedStatement ps = conexion.prepareStatement(sql);
	        ps.setInt(1, idStand);
	        ps.setInt(2, idZona);

	        ResultSet rs = ps.executeQuery();

	        System.out.println("\n--- JUGUETES DISPONIBLES EN EL STAND " + idStand + " - ZONA " + idZona + " ---");

	        boolean hayResultados = false;

	        while (rs.next()) {
	            hayResultados = true;
	            System.out.println(
	                "ID Juguete: " + rs.getInt("ID_Juguete") +
	                " | Nombre: " + rs.getString("Nombre") +
	                " | Precio: " + rs.getDouble("Precio") + "€" +
	                " | Stock: " + rs.getInt("Cantidad_disponible")
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

	    try {

	        System.out.println("Introduce el año (ejemplo: 2024):");
	        int anio = entrada.nextInt();
	        entrada.nextLine();

	        System.out.println("Introduce el mes (1-12):");
	        int mes = entrada.nextInt();
	        entrada.nextLine();

	        String sql = """
	                SELECT v.ID_Venta, v.ID_Empleado, v.ID_Juguete, v.ID_Stand, v.ID_Zona,
	                       v.Fecha, v.Monto, v.Tipo_pago
	                FROM venta v
	                WHERE YEAR(v.Fecha) = ? AND MONTH(v.Fecha) = ?
	                ORDER BY v.Fecha ASC
	                """;

	        PreparedStatement ps = conexion.prepareStatement(sql);
	        ps.setInt(1, anio);
	        ps.setInt(2, mes);

	        ResultSet rs = ps.executeQuery();

	        boolean hayResultados = false;

	        System.out.println("\n=== VENTAS DEL MES " + mes + "/" + anio + " ===");

	        while (rs.next()) {
	            hayResultados = true;

	            System.out.println("-------------------------------");
	            System.out.println("ID Venta: " + rs.getInt("ID_Venta"));
	            System.out.println("Empleado: " + rs.getInt("ID_Empleado"));
	            System.out.println("Juguete: " + rs.getInt("ID_Juguete"));
	            System.out.println("Stand: " + rs.getInt("ID_Stand"));
	            System.out.println("Zona: " + rs.getInt("ID_Zona"));
	            System.out.println("Fecha: " + rs.getTimestamp("Fecha"));
	            System.out.println("Monto: " + rs.getDouble("Monto") + " €");
	            System.out.println("Tipo de pago: " + rs.getString("Tipo_pago"));
	        }

	        if (!hayResultados) {
	            System.out.println("No se encontraron ventas en ese mes.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
}

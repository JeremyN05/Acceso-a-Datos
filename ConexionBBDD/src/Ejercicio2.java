import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

public class Ejercicio2 {
	
	public static Scanner entrada = new Scanner(System.in);
	
	public static Connection ConexionBD(String url, String usuario, String password) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection(url, usuario, password);
		
	}
	
	private static void ficharJugador(Connection conexion) {
		
		try {
			
			System.out.println("Se ha conectado correctamente a la base de datos\n");
			
			String consulta = "Select * from equipos";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			ResultSet resultado = sentencia.executeQuery();
			
			System.out.printf("%-15s %-20s %-20s %-10s%n", "Nombre", "Ciudad", "Conferencia", "División");
			System.out.println("------------------------------------------------------------------------------");
			
			while(resultado.next()) {
				
				String nombre = resultado.getString("Nombre");
				String ciudad = resultado.getString("Ciudad");
				String conferencia = resultado.getString("Conferencia");
				String division = resultado.getString("Division");
				
				System.out.printf("%-15s %-20s %-20s %-10s%n", nombre, ciudad, conferencia, division);
				
			}
			
			System.out.println("\n");
			
			System.out.println("Indique el nombre del equipo para ver sus jugadores y ficharlos: ");
			String nombre_equipo = entrada.nextLine();
			
			System.out.println("\n");
			
			String consulta2 = "select * from jugadores where Nombre_equipo = (select Nombre from equipos where Nombre = ?)";
			PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
			
			sentencia2.setString(1, nombre_equipo);
			
			ResultSet resultado2 = sentencia2.executeQuery();
			
			while(resultado2.next()) {

				int codigo = resultado2.getInt("codigo");
				String nombre = resultado2.getString("Nombre");
				String procedencia = resultado2.getString("Procedencia");
				String altura = resultado2.getString("Altura");
				int peso = resultado2.getInt("Peso");
				String posicion = resultado2.getString("Posicion");
				String nombreEquipo = resultado2.getString("Nombre_equipo");
				
			    System.out.printf("%-15d %-20s %-20s %-10s %-10s %-10s %-10s%n", codigo, nombre, procedencia, altura, peso, posicion, nombreEquipo);
				
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	private static void eliminarJugador(Connection conexion) {
		
		try {
			
			System.out.println("Se ha conectado correctamente a la base de datos");
			
			String consulta = "Select * from jugadores";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			ResultSet resultado = sentencia.executeQuery();
			
			System.out.printf("%-15s %-20s %-20s %-10s %-10s %-10s %-10s%n", "Codigo", "Nombre ", "Procedencia", "Altura", "Peso", "Posicion", "Nombre equipo");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
			
			while(resultado.next()) {
				
				int codigo = resultado.getInt("codigo");
				String nombre = resultado.getString("Nombre");
				String procedencia = resultado.getString("Procedencia");
				String altura = resultado.getString("Altura");
				int peso = resultado.getInt("Peso");
				String posicion = resultado.getString("Posicion");
				String nombreEquipo = resultado.getString("Nombre_equipo");
				
			    System.out.printf("%-15d %-20s %-20s %-10s %-10s %-10s %-10s%n", codigo, nombre, procedencia, altura, peso, posicion, nombreEquipo);
			    
			}
			
			int codigo = 0;
			
			System.out.println("Ingrese el codigo del jugador a eliminar");
			codigo = entrada.nextInt();
			entrada.nextLine();
			
			String consulta2 = "DELETE from jugadores where codigo = ?";
			try {
			
			PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
			
			sentencia2.setInt(1, codigo);
			
			int resultado2 = sentencia2.executeUpdate();
			
			if (resultado2 > 0) {
	            
				System.out.println("Jugador eliminado correctamente.");
	        
			} else {
	           
	        	System.out.println("No se encontró un jugador con ese código.");
	       
	        }
			
			}catch(Exception e) {
				
				e.printStackTrace();
				
			}
		
		}catch(Exception e) {
			
			e.printStackTrace();
		
		}
		
	}

	public static void main(String[] args) {
		
		int opcion = 0;
		
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		
		do {
		
		System.out.println("------------------------------------MENU------------------------------------");
		System.out.println("1. Borrar jugadores.");
		System.out.println("2. Fichar jugador.");
		System.out.println("3. Inserta partido.");
		System.out.println("4. Mostrar estadisticas jugadores de un equipo");
		System.out.println("5 Salir");
		
		opcion = entrada.nextInt();
		entrada.nextLine();
		
		}while(opcion < 1 || opcion > 5);
		
		switch (opcion) {
		
		case 1:
			
			try(Connection conexion = ConexionBD(url,usuario,password)){
			
				eliminarJugador(conexion);
			
			}catch(SQLException | ClassNotFoundException e) {
				
				e.printStackTrace();
				
			}
			
			break;
			
		case 2:
			
			try(Connection conexion = ConexionBD(url,usuario,password)){
				
				ficharJugador(conexion);
				
				}catch(SQLException | ClassNotFoundException e) {
					
					e.printStackTrace();
					
				}
			
			break;
			
		case 3:
			
			break;
			
		case 4:
			
			break;
			
		case 5:
			
			System.out.println("Saliendo del programa...");
			break;

		default:
			break;
		}

	}

}

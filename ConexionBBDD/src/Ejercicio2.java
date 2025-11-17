import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Ejercicio2 {
	
	public static Scanner entrada = new Scanner(System.in);
	
	private static void eliminarJugador() {
		
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			
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
			PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
			
			ResultSet resultado2 = sentencia.executeQuery();
		
		}catch(Exception e) {
			
			e.printStackTrace();
		
		}
		
	}

	public static void main(String[] args) {
		
		int opcion = 0;
		
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
			
			eliminarJugador();
			break;
			
		case 2:
			
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

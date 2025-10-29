import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Ejercicio1 {

public static Scanner entrada = new Scanner(System.in);

	public static void añadirJugador(Scanner entrada) {
		
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			
			System.out.println("Se ha conectado correctamente a la base de datos");
			
			String nombre;
			String procedencia;
			String altura;
			int peso;
			String posicion;
			String nombreEquipo;
			
			System.out.println("Introduzca el nombre del jugador");
			nombre = entrada.nextLine();
			
			System.out.println("Introduzca la procedencia del jugador");
			procedencia = entrada.nextLine();
			
			System.out.println("Introduzca la altura del jugador");
			altura = entrada.nextLine();
			
			System.out.println("Introduzca el peso del jugador");
			peso = entrada.nextInt();
			entrada.nextLine();
			
			System.out.println("Introduzca la posición del jugador");
			posicion = entrada.nextLine();
			
			System.out.println("Introduzca el nombre del equipo");
			nombreEquipo = entrada.nextLine();
			
			String consulta = "Insert into jugadores (codigo, Nombre, Procedencia, Altura, Peso, Posicion, Nombre_equipo) values (NULL, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setString(1, nombre);
			sentencia.setString(2, procedencia);
			sentencia.setString(3, altura);
			sentencia.setInt(4, peso);
			sentencia.setString(5, posicion);
			sentencia.setString(6, nombreEquipo);
			
			ResultSet resultado = sentencia.executeQuery();
			
			System.out.printf("%-15s %-20s %-20s %-10s %-10s %-10s %-10s%n", "Codigo", "Nombre ", "Procedencia", "Altura", "Peso", "Posicion", "Nombre equipo");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
			
			while(resultado.next()) {
				
				int codigo = resultado.getInt("codigo");
				nombre = resultado.getString("Nombre");
				procedencia = resultado.getString("Procedencia");
				altura = resultado.getString("Altura");
				peso = resultado.getInt("Peso");
				posicion = resultado.getString("Posicion");
				nombreEquipo = resultado.getString("Nombre_equipo");
				
			    System.out.printf("%-15d %-20s %-20s %-10s %-10s %-10s %-10s%n", codigo, nombre, procedencia, altura, peso, posicion, nombreEquipo);
				
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void mostrarEquiposYMostrarJugadores(Scanner entrada) {
		
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			
			System.out.println("Se ha conectado correctamente a la base de datos");
			
			String consulta = "SELECT * FROM equipos;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			ResultSet resultado = sentencia.executeQuery();
			
			System.out.printf("%-20s %-20s %-20s %-20s%n", "Nombre", "Ciudad", "Conferencia", "Division");
			System.out.println("--------------------------------------------------------------------------");
			
			while(resultado.next()) {
				
				String nombre = resultado.getString("Nombre");
				String ciudad = resultado.getString("Ciudad");
				String conferencia = resultado.getString("Conferencia");
				String division = resultado.getString("Division");
				
			    System.out.printf("%-20s %-20s %-20s %-20s %n", nombre, ciudad, conferencia, division);
				
			}
			
			String nombreEquipoSeleccionado;	
			System.out.println("\n");
			
			System.out.println("Introduzca el nombre del equipo seleccionado para ver sus jugadores: ");
			nombreEquipoSeleccionado = entrada.nextLine();
			System.out.println("\n");
			
			String consulta2 = "SELECT * FROM jugadores where Nombre_equipo = ? ";
			PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
			
			sentencia2.setString(1, nombreEquipoSeleccionado);
			ResultSet resultado2 = sentencia2.executeQuery();
			
			System.out.printf("%-15s %-20s %-20s %-10s %-10s %-10s %-10s%n", "Codigo", "Nombre ", "Procedencia", "Altura", "Peso", "Posicion", "Nombre equipo");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
			
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
	
	public static void mostrarMediaPesoJugadores() {
		
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			
			System.out.println("Se ha conectado correctamente a la base de datos");
			
			String consulta = "SELECT avg(Peso) AS pesoMedio from jugadores";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			ResultSet resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				
				Double peso = resultado.getDouble("pesoMedio");
				
			    System.out.println("El peso medio de todos los jugadores es: "+ peso);
				
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	public static void obtenerJugadoresPorLetra(Scanner entrada) {
		
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			
			System.out.println("Se ha conectado correctamente a la base de datos");
			
			String letra;
			
			System.out.println("Introduzca la letra del jugador a buscar");
			letra = entrada.nextLine();
			String consulta = "SELECT * FROM jugadores where Nombre like ? ";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setString(1, letra + "%");
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
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static void main(String[] args) {
		
		int opcion = 0;
		
		do {
			System.out.println("------------------------------------------------------Menú------------------------------------------------------");
			System.out.println("Qué desea realizar: ");
			System.out.println("1. Mostrar datos de jugadores por que empiecen por una letra");
			System.out.println("2. Mostrar el peso medio de los jugadores.");
			System.out.println("3. Mostrar la lista de equipos y seleccionar un equipo para mostrar todos sus jugadores.");
			System.out.println("4. Insertar un jugador");
			System.out.println("5. Salir");
			
			opcion = entrada.nextInt();
			entrada.nextLine();
			
		}while(opcion > 5);
		
		switch (opcion) {
		
		case 1:
			
			obtenerJugadoresPorLetra(entrada);
			break;
			
		case 2:
			
			 mostrarMediaPesoJugadores();
			break;
			
		case 3:
			
			mostrarEquiposYMostrarJugadores(entrada);
			break;
			
		case 4:
			
			
			break;
			
		case 5:
			System.out.println("El programa se ha cerrado correctamente");
			break;

		default:
	
			break;
		}

	}

}

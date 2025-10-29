import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mydb";
		String usuario = "root";
		String password = "cfgs";
		
		try {
			
			// 1. Cargar el drive de la base de datos
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. Crear conexion
			
			Connection conexion = DriverManager.getConnection(url, usuario, password);
			
			System.out.println("Se ha conectado correctamente a la base de datos");
			
			// 3.1 crear un Statement
			
			/*Statement sentencia = conexion.createStatement();
			
			String consulta = "SELECT * FROM usuario";
			ResultSet resultado = sentencia.executeQuery(consulta);*/
			
			//3.2 Crear un PreparedStatement
			
			String consulta = "SELECT * FROM usuario where idUsuario = ? OR nombre=?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			int numero = 1;
			sentencia.setInt(1, numero);
			sentencia.setString(2, "Leo");
			
			ResultSet resultado = sentencia.executeQuery();
			
			//Mostrar resultados
			
			System.out.printf("%-15s %-20s %-20s %-10s%n", "ID Usuario", "Nombre Usuario", "Fecha Nacimiento", "Género");
			System.out.println("------------------------------------------------------------------------------------");
			
			while(resultado.next()) {
				
				int idUSUARIO = resultado.getInt("idUSUARIO");
				String NombreUsuario = resultado.getString("NombreUsuario");
				Date FechaNac = resultado.getDate("FechaNac");
				String genero = resultado.getString("genero");
				
			    System.out.printf("%-15d %-20s %-20s %-10s%n", idUSUARIO, NombreUsuario, FechaNac.toString(), genero);
				
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}


	}

}

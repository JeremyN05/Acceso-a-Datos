import java.util.Scanner;

public class InicioSesion {

	public static Scanner entrada = new Scanner(System.in);
	
	public static void IniciarSesion(Scanner entrada) {
		
		String usuario;
		String contraseña;
		
		System.out.println("Ingrese el nombre de usuario: ");
		usuario = entrada.nextLine();
		
		System.out.println("Ingrese la contraseña del usuario: ");
		contraseña = entrada.nextLine();
		
		GestorEmpleados.ComprobarEmpleado(usuario, contraseña);
		
	}
	
}

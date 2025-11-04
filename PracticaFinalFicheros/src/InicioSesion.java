import java.util.Scanner;

public class InicioSesion {

	public static Scanner entrada = new Scanner(System.in);
	
	public static void IniciarSesion(Scanner entrada) {
		
		int id;
		String contraseña;
		
		System.out.println("Ingrese el id de usuario: ");
		id = entrada.nextInt();
		entrada.nextLine();
		
		System.out.println("Ingrese la contraseña del usuario: ");
		contraseña = entrada.nextLine();
		
		GestorEmpleados.ComprobarEmpleado(id, contraseña);
		
	}
	
}

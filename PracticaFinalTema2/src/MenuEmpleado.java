import java.sql.Connection;
import java.util.Scanner;

public class MenuEmpleado {
	
	private static Scanner entrada = new Scanner(System.in);

	public static void mostrarMenuEmpleado(Connection conexion, Scanner entrada) {
		
		int opcionEmpleado = 0;
		
		System.out.println("                    MENÚ EMPLEADO                    ");
		System.out.println("-----------------------------------------------------");
		
		do {
			
			System.out.println("1. Registrar un nuevo empleado.");
			System.out.println("2. Modificar los datos de un nuevo empleado.");
			System.out.println("3. Eliminar empleado");
			System.out.println("4. Salir.\n");
			
			System.out.println("Indique que desea hacer: ");
			opcionEmpleado = entrada.nextInt();
			entrada.nextLine();
			
		}while(opcionEmpleado < 1 || opcionEmpleado > 4);
		
		switch (opcionEmpleado) {
		
		case 1:
			
			GestorEmpleados.registrarEmpleado(conexion, entrada);
			break;
			
		case 2:
			
			GestorEmpleados.modificarEmpleado(conexion, entrada);
			break;

		
		default:
			break;
		
		}
	
	}
		
}

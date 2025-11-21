import java.sql.Connection;
import java.util.Scanner;

public class MenuVentas {

	private static Scanner entrada = new Scanner(System.in);
	
	public static void mostrarMenuVentas(Connection conexion) {
		
		int opcionVentas = 0;
		
		System.out.println("                    MENÚ EMPLEADO                    ");
		System.out.println("-----------------------------------------------------");
		
		do {
			
			System.out.println("1. Realizar una venta.");
			System.out.println("2. Realizar una devolución.");
			System.out.println("3. Producto más vendido (5 primeros).");
			System.out.println("4. Mostrar empleados que más venden.");
			System.out.println("5. Salir.\n");
			
			System.out.println("Indique que desea hacer: ");
			opcionVentas = entrada.nextInt();
			entrada.nextLine();
			
		}while(opcionVentas < 1 || opcionVentas > 5);
		
	}
	
}

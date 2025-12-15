import java.sql.Connection;
import java.util.Scanner;

public class MenuDatosTienda {

	public static void menuDatosTienda(Connection conexion, Scanner entrada) {
		
		if (conexion == null) {
		       
			System.out.println("No se pudo establecer la conexión con la base de datos.");
	        return;
	   
		}
		
		int opcionDatos = 0;
		
		do {
			
			System.out.println("--------Menú Mostrar Datos--------");
			System.out.println("1. Mostrar juguetes disponibles en un stand.");
			System.out.println("2. Mostrar ventas realizadas en un mes.");
			System.out.println("3. Mostrar ventas realizadas por un empleado en un mes");
			System.out.println("4. Mostrar cambios y el motivo del cambio");
			System.out.println("5. Mostrar lista de productos ordenados por precio");
			System.out.println("6. Salir");
			
			opcionDatos = entrada.nextInt();
			entrada.nextLine();
			
		}while(opcionDatos < 0 || opcionDatos > 6);
		
		switch (opcionDatos) {
		
		case 1:
			
			GestorDatosTienda.obtenerJuguetesEnStand(conexion, entrada);
			break;
			
		case 2:
			
			GestorDatosTienda.mostrarVentasPorMes(conexion, entrada);
			break;
			
		case 3:
			
			GestorDatosTienda.obtenerVentasEmpleadoMes(conexion, entrada);
			break;
			
		case 4:
			
			GestorDatosTienda.mostrarCambiosEmpleados(conexion);
			break;
			
		case 5:
			
			GestorDatosTienda.listarProductosOrdenadosPorPrecio(conexion);
			break;
			
		case 6:
			
			System.out.println("Saliendo del programa...");
			System.exit(0);
			break;

		default:
			System.out.println("Error opción introducida inválida");
			break;
		}
		
	}
	
}

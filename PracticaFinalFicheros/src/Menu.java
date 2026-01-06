import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
	
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";
	static final String VERDE_AZULADO = "\u001B[38;2;80;240;255m";
	static final String VERDE_CLARITO = "\u001B[38;2;144;238;144m";

	
	public static void mostrarMenuGestores(Empleados empleados, Scanner entrada) {
		
		int opcion = 0;
		
		do {
			
			System.out.println(VERDE_CLARITO + "--------------------------MENÚ GESTORES--------------------------" + RESET);
			System.out.println("1. Dar de alta las plantas.");
			System.out.println("2. Recuperar planta de baja");
			System.out.println("3. Dar de baja las plantas.");
			System.out.println("4. Modificar campos de las plantas.");
			System.out.println("5. Dar de alta empleados.");
			System.out.println("6. Dar de baja empleados.");
			System.out.println("7. Recuperar o recontrartar empleado de baja.");
			System.out.println("8. Estadísticas (datos extraídos de los tickets).");
			System.out.println("9. Salir");
			
			opcion = entrada.nextInt();
			entrada.nextLine();
			
			switch (opcion) {
			case 1:
				
				GestorPlantas.añadirPlantas(entrada);
				break;
				
			case 2:
				
				GestorBaja_AltaPlantas.darAltaPlantas(entrada);
				break;
				
			case 3:
				
				GestorBaja_AltaPlantas.darBajaPlantas(entrada);
				break;
				
			case 4:
				
				GestorPlantas.modificarCamposPlanta(entrada);
				break;
				
			case 5:
				
				GestorEmpleados.darAltaEmpleados();
				break;
				
			case 6:
				
				GestorEmpleados.darBajaEmpleado();
				break;
				
			case 7:
				
				GestorEmpleados.recuperarEmpleadoBaja();
				break;
				
			case 8:
				
				Estadisticas.mostrarEstadisticas();
				break;
				
			case 9:
				
				System.out.println("Saliendo del programa.");
				System.exit(0);
				
				break;

			default:
				break;
			}
			
		}while(opcion > 9);
		
	}

	public static void mostrarMenuVendedores(Empleados empleados, Scanner entrada) {
		
		int opcion = 0;
		String respuesta;
		ArrayList<Compra> cesta = new ArrayList<>();
	
		do {
			
			System.out.println(VERDE_AZULADO + "----------------MENÚ VENDEDORES----------------" + RESET);
			System.out.println("1. Visualizar catálogo plantas.");
			System.out.println("2. Realizar compra.");
			System.out.println("3. Realizar devolución.");
			System.out.println("4. Buscar por número de ticket.");
			System.out.println("5. Salir del menú.");
			
			System.out.println("Seleccione una opción: ");
			opcion = entrada.nextInt();
			entrada.nextLine();
			
			switch (opcion) {
			case 1:
				
				System.out.println("------------------------------------------------------------------------------PLANTAS------------------------------------------------------------------------------");
				System.out.printf("%-15s %-15s %-15s %-65s %-10s %-15s \n", "ID", "Nombre", "Foto", "Descripcion", "Precio", "Stock");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				
				GestorPlantas.mostrarPlantas();
				
				System.out.println("\n");
				
				System.out.println(CYAN + "Desea realizar una compra S/N" + RESET);
				respuesta = entrada.nextLine();
				
				if(respuesta.equalsIgnoreCase("S")) {
					
					GestorVentas.GestionVentas(cesta, entrada);
					
				}else {
					
					System.out.println("Operación cancelada, cerrando programa");
				}
				
				break;

			case 2:
				
				GestorVentas.GestionVentas(cesta, entrada);
				break;
				
			case 3:
				
				GestorDevoluciones.realizarDevolucion(entrada);
				break;
				
			case 4:

				ArrayList<Ticket> lineas = Tickets.buscarTicketPorId(entrada);
				
				break;
				
			case 5:
				
				System.out.println("Saliendo del programa");
				break;
				
			default:
				break;
			}
			
		}while(opcion > 5);
	
	}
	
}

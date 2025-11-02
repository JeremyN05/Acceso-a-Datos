
import java.util.Scanner;

public class Main {

	static Scanner entrada = new Scanner(System.in);
	static final String VERDE = "\u001B[32m";
	static final String AZUL = "\u001B[34m";
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";
	
	public static void main(String[] args) {
		
		GestorEmpleados.EscribirEmpleado();
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.println(VERDE + "------------------------------------------------------------------------------PLANTAS------------------------------------------------------------------------------");
		System.out.printf("%-15s %-15s %-15s %-65s %-10s %-15s \n", "ID", "Nombre", "Foto", "Descripcion", "Precio", "Stock");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
		
		GestorPlantas.mostrarPlantas();
		System.out.println("\n");
		
		System.out.println(CYAN + "---------------------------------------EMPLEADOS---------------------------");
		System.out.printf("%-15s %-15s %-15s %-15s \n", "ID", "Nombre", "contraseña", "cargo");
		System.out.println("---------------------------------------------------------------------------" + RESET);
		GestorEmpleados.leerEmpleados();
		
		System.out.println("\n");
		
		System.out.println("----------------Inicio Sesión----------------\n");
		InicioSesion.IniciarSesion(entrada);
		
		/*int opcion;
		System.out.println("-----------------------------------------");
		System.out.println("¿Qué desea hacer?");
		System.out.println("1. Mostrar todas las plantas");
		System.out.println("2. Mostrar todos los empleados");
		System.out.println("-----------------------------------------");
		
		switch (opcion) {
		case 1:
			mostrarPlantas();
			break;
		
		case 2:
			mostrarEmpleados();
			break;
			
		case 3:
			
			
		default:
			break;
		}*/


	}

	private static void inIniciarSesion(Scanner entrada2) {
		// TODO Auto-generated method stub
		
	}

}

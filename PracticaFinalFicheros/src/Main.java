
import java.util.Scanner;

public class Main {

	static Scanner entrada = new Scanner(System.in);
	static final String VERDE = "\u001B[32m";
	static final String AZUL = "\u001B[34m";
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";
	static final String VERDE_CLARITO = "\u001B[38;2;144;238;144m";
	
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
		GestorEmpleados.leerEmpleados(true);
		
		System.out.println("\n");
		
		System.out.println(VERDE_CLARITO + "----------------Inicio Sesión----------------" + RESET);
		InicioSesion.IniciarSesion(entrada);
		

	}

}

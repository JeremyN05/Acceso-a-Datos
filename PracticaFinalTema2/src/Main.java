import java.util.Scanner;

public class Main {
	
	private static Scanner entrada = new Scanner(System.in); 

	public static void main(String[] args) {
		int opcion = 0;
		
		do {
			
			System.out.println("               MENÚS               ");
			System.out.println("-----------------------------------");
		
			System.out.println("1. Menú juguetes");
			System.out.println("2. Menú empleado");
			System.out.println("3. Menú ventas");
			System.out.println("4. Menú datos tienda");
			System.out.println("5. Salir\n");
		
			System.out.println("Indique el menú al que quiera acceder");
			opcion = entrada.nextInt();
			entrada.nextLine();
			
		}while(opcion < 1 || opcion > 5);
		
		switch (opcion) {
		
		case 1:
			
			MenuJuguete.mostrarMenuJuguetes();
			break;

		case 2:
			
			MenuEmpleado.mostrarMenuEmpleado();
			break;
			
		case 3:
			
			MenuVentas.mostrarMenuVentas();
			break;
			
		default:
			break;
		}
		
	}

}

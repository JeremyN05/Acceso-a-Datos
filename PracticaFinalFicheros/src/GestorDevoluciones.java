import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorDevoluciones {
	
	public static ArrayList<ListaTicket> obtenerLineas(int idTicket) {
		
		
		
		return ListaTicket;
	
	}

	public static void realizarDevolucion(Scanner entrada) {
		
		int idTicket = 0;
		
		System.out.println("Ingrese el número del ticket a devolver: ");
		idTicket = entrada.nextInt();
		entrada.nextLine();
		
		File fichero = new File(idTicket + ".txt");
		
		if(!fichero.exists()) {
			
			System.out.println("El ticket introducido no existe.");
			
		}else {
			
			ListaTicket lineas = Ticket.obtenerLineas(idTicket);
			
		}
		
	}
	
}

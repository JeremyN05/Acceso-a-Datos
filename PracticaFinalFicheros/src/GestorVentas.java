import java.util.ArrayList;
import java.util.Scanner;

public class GestorVentas {
	
public static void GestionVentas(ArrayList<Compra> cesta, Scanner entrada) {
		
		int id = 0;
		int cantidad;
		String confirmacion;		
		
		System.out.println("Introduzca el id de la planta a comprar: ");
		id = entrada.nextInt();
		entrada.nextLine();
		
		
		Plantas plantaSeleccionada = GestorPlantas.buscaPlantaID(id);
		
		if(plantaSeleccionada != null) {
			
			System.out.println("El id introducido es correcto");
			System.out.println("Introduzca la cantidad a comprar: ");
			cantidad = entrada.nextInt();
			entrada.nextLine();
			
			GestorPlantas.comprobarCantidad(id, cantidad);
			
			System.out.println("¿Está seguro de realizar esta compra (S/N) ? \n");
			confirmacion = entrada.nextLine();
			
			if(confirmacion.equalsIgnoreCase("s")) {
				
				Tickets.CrearTicket(id, cantidad);
				
			}else {
				
				System.out.println("Cancelando compra");
				
			}
			
		}else {
			
			System.out.println("El id introducido es erroneo.");
			
		}
	
	}

}

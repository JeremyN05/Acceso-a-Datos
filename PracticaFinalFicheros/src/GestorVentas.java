import java.util.ArrayList;
import java.util.Scanner;

public class GestorVentas {
	
	public static void GestionVentas(ArrayList<Compra> cesta, Scanner entrada) {
		
		int id;
		int cantidad;
		float precioUnitario;
		String confirmacion;
		String confirmacion2;
		
		do {
		
			System.out.println("Introduzca el id de la planta a comprar: ");
			id = entrada.nextInt();
			entrada.nextLine();
		
			Plantas plantaSeleccionada = GestorPlantas.buscaPlantaID(id);
			
		
			if(plantaSeleccionada != null) {
				
				precioUnitario = plantaSeleccionada.getPrecio();
			
				System.out.println("El id introducido es correcto");
				System.out.println("Introduzca la cantidad a comprar: ");
				cantidad = entrada.nextInt();
				entrada.nextLine();
			
				GestorPlantas.comprobarCantidad(id, cantidad);
			
				System.out.println("¿Está seguro de realizar esta compra (S/N) ? \n");
				confirmacion = entrada.nextLine();
			
				if(confirmacion.equalsIgnoreCase("s")) {
				
					Tickets.CrearTicket(id, cantidad, precioUnitario);
				
				}else {
				
					System.out.println("Cancelando compra");
				
				}
			
			}else {
			
				System.out.println("El id introducido es erroneo.");
				
			}
			
			System.out.println("Desea comprar más plantas S/N");
			confirmacion2 = entrada.nextLine();
			
		}while(confirmacion2.equalsIgnoreCase("S"));
	
	}

}

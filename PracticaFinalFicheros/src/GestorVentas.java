import java.util.ArrayList;
import java.util.Scanner;

public class GestorVentas {
	
public static void GestionVentas(ArrayList<Compra> cesta, Scanner entrada) {
		
		int id;
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
			
			System.out.println("¿Está seguro de realizar esta compra (S/N) ?");
			confirmacion = entrada.nextLine();
			
			if(confirmacion.equalsIgnoreCase("s")) {
				
				System.out.println("--------------------RESUMEN DE COMPRA--------------------");
				System.out.println("Planta a comprar: ");
				
				GestorPlantas.buscaPlantaID(id);
				
				System.out.println("Cantidad a comprar: ");
				System.out.println(cantidad);
				
			}
			
		}else {
			
			System.out.println("El id introducido es erroneo.");
			
		}
	
	}

}

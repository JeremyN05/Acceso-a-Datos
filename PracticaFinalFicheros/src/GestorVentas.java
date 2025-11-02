import java.util.ArrayList;
import java.util.Scanner;

public class GestorVentas {
	
	public static void GestionVentas(ArrayList<Compra> cesta, Scanner entrada) {
		
		int id;
		int cantidad;
		String respuesta;
		
		/*System.out.println("¿Los datos son correctos? S/N" + "id: " + id + " cantidad: " + cantidad);
		respuesta = entrada.nextLine();*/
		
		
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
			
		}else {
			
			System.out.println("El id introducido es erroneo.");
			
		}
	
	}

}

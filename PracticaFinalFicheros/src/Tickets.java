
public class Tickets {
	
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";

	public static void CrearTicket(int id, int cantidad) {
		
		Empleados e = Sesion.getEmpleadoActual();
		
		int numeroTicket = 0;
		
		numeroTicket++;
		
		System.out.println(CYAN + "---------------------------------------TICKET---------------------------------------\n" + RESET);
		
		System.out.println("Número Ticket: " + numeroTicket + "\n");
		
		System.out.println("——————————————//———————————------------------------\n");
		
		System.out.println("Id del empleado que ha atendido: " + e.getIdentificacion()  + "\n");
		
		System.out.println("Nombre del empleado: " + e.getNombre() + "\n");
		
		System.out.printf("%-25s %-25s\n", "CodigoProducto", "Cantidad");
		System.out.printf("%-25s %-25s\n", id, cantidad);
		
	}
	
}

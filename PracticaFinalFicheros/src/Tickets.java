import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Tickets {
	
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";
	
	public static ArrayList<Ticket> buscarTicketPorId(Scanner entrada) {
	    
		System.out.print("Ingrese el ID del ticket a buscar: ");
	    int idTicket = entrada.nextInt();
	    entrada.nextLine();

	    File archivoTicket = new File("PracticaFinalFicheros_NarváezLobatoJeremy/TICKETS/" + idTicket + ".txt");

	    if (!archivoTicket.exists()) {
	        
	    	System.out.println("❌ El ticket con ID " + idTicket + " no existe.");
	        return new ArrayList<>();
	    
	    }

	    System.out.println("\n----- 📄 TICKET " + idTicket + " -----");

	    try (Scanner lector = new Scanner(archivoTicket)) {
	        
	    	while (lector.hasNextLine()) {
	            
	    		System.out.println(lector.nextLine());
	        
	        }
	    
	    } catch (IOException e) {
	       
	    	System.out.println("⚠️ Error al leer el ticket.");
	        e.printStackTrace();
	    
	    }

	    System.out.println("--------------------------------------");

	    ArrayList<Ticket> lineas = obtenerLineas(idTicket);

	    if (lineas.isEmpty()) {
	        
	    	System.out.println("El ticket no contiene líneas de productos.");
	    
	    } else {
	        
	    	System.out.println("Se han cargado " + lineas.size() + " líneas de productos del ticket.");
	    
	    }

	    return lineas;
	}
	
	private static ArrayList<Ticket> obtenerLineas(int idTicket) {
	    
		ArrayList<Ticket> lineas = new ArrayList<>();
	    File fichero = new File("PracticaFinalFicheros_NarváezLobatoJeremy/TICKETS/" + idTicket + ".txt");

	    if (!fichero.exists()) {
	        
	    	System.out.println("El ticket no existe.");
	        
	        return lineas;
	    
	    }

	    boolean leerProductos = false;

	    try (Scanner lector = new Scanner(fichero)) {
	        
	    	while (lector.hasNextLine()) {
	            
	    		String linea = lector.nextLine().trim();

	            if (linea.equals("LINEAS DE PRODUCTOS")) {
	                
	            	leerProductos = true;
	                lector.nextLine();
	                
	                continue;
	            }

	            if (leerProductos) {
	                
	            	if (linea.startsWith("---") || linea.startsWith("TOTAL") || linea.isEmpty()) break;

	                String[] partes = linea.split("\\s+");
	                if (partes.length == 4) {
	                    
	                	try {
	                        
	                		int codigo = Integer.parseInt(partes[0]);
	                        int unidades = Integer.parseInt(partes[1]);
	                        float precio = Float.parseFloat(partes[2]);
	                        float total = Float.parseFloat(partes[3]);
	                        lineas.add(new Ticket(codigo, unidades, precio, total));
	                    
	                	} catch (NumberFormatException e) {
	                    
	                	}
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return lineas;
	}
	
	private static int ticketSiguiente() {
		
		File carpetaTickets = new File("PracticaFinalFicheros_NarváezLobatoJeremy/TICKETS");
		File[] archivos = carpetaTickets.listFiles();
		
		int ultimoTicket = 0;
		
		if (archivos != null) {
		    
			for (File f : archivos) {
		        String nombre = f.getName();


		        if (nombre.endsWith(".txt")) {
		            
		        	try {
		                
		            	int num = Integer.parseInt(nombre.replace(".txt", ""));
		               
		                if (num > ultimoTicket) {
		                	
		                	ultimoTicket = num;
		                
		                }
		            
		            } catch (NumberFormatException e) {
		                
		            }
		        }
		    }
		}

		int numeroTicket = ultimoTicket + 1;
		
		return numeroTicket;
	}

	public static void CrearTicket(int idProducto, int cantidad, float precioUnitario) {

	    Empleados e = Sesion.getEmpleadoActual();
	    int numeroTicket = ticketSiguiente();

	    File carpetaTickets = new File("PracticaFinalFicheros_NarváezLobatoJeremy/TICKETS");
	    if (!carpetaTickets.exists()) carpetaTickets.mkdirs();

	    File archivoTicket = new File(carpetaTickets, numeroTicket + ".txt");

	    LocalDateTime fechaVenta = LocalDateTime.now();
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    float totalLinea = cantidad * precioUnitario;

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTicket))) {

	        writer.write("==================================== TICKET DE COMPRA ====================================\n");
	        writer.write("Número Ticket: " + numeroTicket + "\n");
	        writer.write("Empleado: " + e.getNombre() + " (ID: " + e.getIdentificacion() + ")\n");
	        writer.write("Cargo: " + e.getCargo() + "\n");
	        writer.write("Fecha de venta: " + fechaVenta.format(formato) + "\n");
	        writer.write("-------------------------------------------------------------------------------\n");
	        writer.write("LINEAS DE PRODUCTOS\n"); // MARCA de inicio de productos
	        writer.write(String.format("%-10s %-10s %-10s %-10s\n", "Código", "Cant", "Precio", "Total"));
	        writer.write(String.format("%-10d %-10d %-10.2f %-10.2f\n", idProducto, cantidad, precioUnitario, totalLinea));
	        writer.write("-------------------------------------------------------------------------------\n");
	        writer.write(String.format("TOTAL FINAL: %.2f €\n", totalLinea));
	        writer.write("===============================================================================\n");

	        System.out.println("Ticket creado correctamente.");
	        
	        try (Scanner lector = new Scanner(archivoTicket)) {
	            
	        	while (lector.hasNextLine()) System.out.println(lector.nextLine());
	        
	        }

	        GestorPlantas.actualizarStock(idProducto, cantidad);
	        GestorPlantas.guardarPlantasDat();

	    } catch (IOException ex) {
	        
	    	System.out.println("No se pudo guardar el ticket.");
	        ex.printStackTrace();
	    
	    }
	}
	
}

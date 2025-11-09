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
	
	public static ArrayList<Ticket> buscarTicketID(int idTicket) {
		
		ArrayList<Ticket> lineas = new ArrayList<>();
        File carpetaTickets = new File("PracticaFinalFicheros_NarváezLobatoJeremy/TICKETS");

        if (!carpetaTickets.exists()) {
            
        	System.out.println("La carpeta de tickets no existe.");
            return lineas;
        
        }

        File archivoTicket = new File(carpetaTickets, idTicket + ".txt");

        if (!archivoTicket.exists()) {
            
        	System.out.println("El ticket con ID " + idTicket + " no existe.");
            
            return lineas;
        }

        System.out.println("Contenido del ticket " + idTicket + ":");

        try (Scanner lector = new Scanner(archivoTicket)) {
            while (lector.hasNextLine()) {
                
            	String linea = lector.nextLine();
                System.out.println(linea); // mostramos todo tal cual
                String[] partes = linea.trim().split("\\s+");

                // intentamos crear Ticket solo si tiene 4 columnas numéricas (productos)
                if (partes.length == 4) {
                    
                	try {
                        
                		int codigoProducto = Integer.parseInt(partes[0]);
                        int unidades = Integer.parseInt(partes[1]);
                        float precioUnitario = Float.parseFloat(partes[2]);
                        float total = Float.parseFloat(partes[3]);

                        lineas.add(new Ticket(codigoProducto, unidades, precioUnitario, total));
                    
                	} catch (NumberFormatException e) {
                        // ignorar líneas que no sean productos
                    }
                }
            }
        } catch (FileNotFoundException e) {
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

		        // Solo considerar los archivos .txt
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

	public static void CrearTicket(int id, int cantidad, float precioUnitario) {
		
		Empleados e = Sesion.getEmpleadoActual();
		
		int numeroTicket = ticketSiguiente(); //Guardo el numero de ticket que guarda la funcion ticketSiguiente.
		
		File carpetaTickets = new File("PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "TICKETS");
	     
		if (!carpetaTickets.exists()) {
			
			 carpetaTickets.mkdirs();
			 
		}
		
		File archivoTicket = new File(carpetaTickets, numeroTicket + ".txt"); //Guardo el ticket con su número correcto sin sobreescribir los anteriores.
		
		LocalDateTime fechaVenta = LocalDateTime.now();
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTicket))) {
		    
		    writer.write("==================================== TICKET DE COMPRA ====================================\n");
		    writer.write("Número Ticket: " + numeroTicket + "\n");
		    writer.write("Empleado: " + e.getNombre() + " (ID: " + e.getIdentificacion() + ")\n");
		    writer.write("Cargo: " + e.getCargo() + "\n");
		    writer.write("Fecha de venta: " + fechaVenta.format(formato) + "\n");
		    writer.write("-------------------------------------------------------------------------------\n");
		    
		    writer.write(String.format("%-15s %-15s %-15s %-15s\n",
		            "CódigoProd", "Cantidad", "PrecioUnit", "TotalLinea"));
		    
		    // Aquí escribes la línea de la venta actual
		    float totalLinea = cantidad * precioUnitario;
		    writer.write(String.format("%-15d %-15d %-15.2f %-15.2f\n",
		            id, cantidad, precioUnitario, totalLinea));

		    writer.write("-------------------------------------------------------------------------------\n");
		    writer.write(String.format("TOTAL FINAL: %.2f €\n", totalLinea));
		    writer.write("===============================================================================");
		    
		    System.out.println("Ticket creado correctamente");
		    
		    writer.flush();
		    
		    System.out.println("Ticket:");
		    
		    try (Scanner lector = new Scanner(archivoTicket)) {
		        
		    	while (lector.hasNextLine()) {
		            
		    		System.out.println(lector.nextLine());
		        
		    	}
		    
		    } catch (IOException i) {
		        
		    	System.out.println("No se pudo mostrar el ticket.");
		    
		    }
		    
		    GestorPlantas.actualizarStock(id, cantidad);
	        GestorPlantas.guardarPlantasDat(); // guardo cambios en plantas.dat

		} catch (IOException ex) {
		
			System.out.println("No se pudo guardar el ticket.");
		
		}
		
	}
	
}

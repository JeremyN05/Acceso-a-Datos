import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorDevoluciones {
	
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

		            if (linea.startsWith("CódigoProd")) {
		                
		            	leerProductos = true;
		                
		            	continue;
		            
		            }

		            if (leerProductos) {
		                
		                if (linea.startsWith("---") || linea.startsWith("TOTAL") || linea.isEmpty()) {
		                    
		                	break;
		                
		                }

		                String[] partes = linea.split("\\s+");
		                
		                if (partes.length >= 4) {
		                    
		                	try {
		                        
		                    	int codigo = Integer.parseInt(partes[0]);
		                        int cantidad = Integer.parseInt(partes[1]);
		                        float precio = Float.parseFloat(partes[2]);
		                        float total = Float.parseFloat(partes[3]);
		                        lineas.add(new Ticket(codigo, cantidad, precio, total));
		                    
		                    } catch (NumberFormatException e) {
		                       
		                    
		                    }
		                
		                }
		            
		            }
		        
		        }
		    
		    } catch (FileNotFoundException e) {
		        
		    	e.printStackTrace();
		    
		    }

		    return lineas;
		
		}

	public static void realizarDevolucion(Scanner entrada) {
		
		System.out.println("Ingrese el número del ticket a devolver: ");
        int idTicket = entrada.nextInt();
        entrada.nextLine();
        
        File fichero = new File("PracticaFinalFicheros_NarváezLobatoJeremy/TICKETS/" + idTicket + ".txt");
        
        if (!fichero.exists()) {
            System.out.println("El ticket introducido no existe.");
            return;
        }
        
        // Obtener las líneas del ticket
        ArrayList<Ticket> lineas = GestorDevoluciones.obtenerLineas(idTicket);
        
        if (lineas.isEmpty()) {
            
        	System.out.println("El ticket no tiene productos para devolver.");
            
        	return;

        }
        
        File carpetaDevoluciones = new File("PracticaFinalFicheros_NarváezLobatoJeremy/DEVOLUCIONES");
        
        if (!carpetaDevoluciones.exists()) {
            
        	carpetaDevoluciones.mkdirs();
       
        }
        
        int numeroDevolucion = 1;
        
        File[] archivos = carpetaDevoluciones.listFiles();
        
        if (archivos != null) {
            
        	for (File f : archivos) {
                
        		String nombre = f.getName();
                
        		if (nombre.startsWith("devolucion") && nombre.endsWith(".txt")) {
                    
        			try {
                        
        				int num = Integer.parseInt(nombre.replace("devolucion", "").replace(".txt", ""));
                        
        				if (num >= numeroDevolucion) {
                           
        					numeroDevolucion = num + 1;
                        
        				}
                    
        			} catch (NumberFormatException e) {

                    
        			}
                
        		}
            
        	}
        
        }
        
        
        File archivoDevolucion = new File(carpetaDevoluciones, numeroDevolucion + ".txt");
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDevolucion))) {
            
            writer.write("=========== DEVOLUCION DE TICKET ===========\n");
            writer.write("Ticket original: " + idTicket + "\n");
            writer.write("Fecha de devolución: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
            writer.write("--------------------------------------------\n");
            
            boolean seguirDevolviendo = true;
            
            while (seguirDevolviendo) {
                
                System.out.println("Líneas del ticket:");
                
                for (int i = 0; i < lineas.size(); i++) {
                    
                	Ticket t = lineas.get(i);
                    
                	System.out.println((i+1) + ". " + t);
               
                }
                
                System.out.println("Ingrese el número de la línea a devolver: ");
                int numLinea = entrada.nextInt();
                entrada.nextLine();
                
                if (numLinea < 1 || numLinea > lineas.size()) {
                    
                	System.out.println("Número de línea inválido.");
                    
                	continue;
                
                }
                
                Ticket t = lineas.get(numLinea - 1);
                
                System.out.println("Ingrese la cantidad a devolver: ");
                int cantidadDevuelta = entrada.nextInt();
                entrada.nextLine();
                
                if (cantidadDevuelta > t.getUnidades()) {
                    
                	System.out.println("Error: no se puede devolver más unidades de las compradas.");
                    
                	continue;
                
                }
                
                GestorPlantas.actualizarStock(t.getCodigoProducto(), cantidadDevuelta);
                
                // Guardar cambios en DEVOLUCIONES.TXT
                
                writer.write(String.format("Producto: %d | Cantidad devuelta: %d | Precio unitario: %.2f | Total: %.2f\n",
                        t.getCodigoProducto(), cantidadDevuelta, t.getPrecioUnitario(), cantidadDevuelta * t.getPrecioUnitario()));
                
                System.out.println("Devolución realizada correctamente.");
                
                // Resto las unidades en el ticket original
                t.setUnidades(t.getUnidades() - cantidadDevuelta);

                
                if (t.getUnidades() == 0) {
                    
                	writer.write("Línea devuelta completamente: Producto " + t.getCodigoProducto() + "\n");
                
                }
                
                System.out.println("¿Desea devolver otra línea? (s/n)");
                String resp = entrada.nextLine();
                
                if (!resp.equalsIgnoreCase("s")) {
                    
                	seguirDevolviendo = false;
                
                }
            
            }
            
            writer.write("--------------------------------------------\n");
            writer.write("============================================\n");
            
        } catch (IOException e) {
            
        	System.out.println("Error al registrar la devolución.");
           
            e.printStackTrace();
        }
		
	}
	
}

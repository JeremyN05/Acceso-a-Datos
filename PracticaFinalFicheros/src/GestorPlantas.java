
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GestorPlantas {
	
	public static List<Plantas> listaPlantas = new ArrayList<>();
	private static final String BASE_DIR = "PracticaFinalFicheros_NarváezLobatoJeremy";
    private static final String PLANTAS_DIR = BASE_DIR + File.separator + "PLANTAS";
    private static final String VERDE_OSCURITO = "\u001B[38;2;34;85;34m";
    private static final String RESET = "\u001B[0m";
    
    private static int obtenerSiguienteID() {

        int maxId = 0;

        for (Plantas p : listaPlantas) {
            if (p.getCodigo() > maxId) {
                maxId = p.getCodigo();
            }
        }

        return maxId + 1;
    }

    
    public static void añadirPlantas(Scanner entrada) {

        if (listaPlantas.isEmpty()) {
            mostrarPlantas();
        }
        
        int id = obtenerSiguienteID();
        System.out.println("ID asignado automáticamente: " + id);

        String nombre;
        
        do {
         
        	System.out.println("Introduce el nombre de la planta: ");
            nombre = entrada.nextLine();
        
        } while (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$"));

        String foto;
        
        do {
          
        	System.out.println("Introduce el nombre de la foto (ej: planta.jpg): ");
            foto = entrada.nextLine();
       
        } while (!foto.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+\\.jpg$"));

        String descripcion;
       
        do {
          
        	System.out.println("Introduce la descripción: ");
            descripcion = entrada.nextLine();
       
        } while (!descripcion.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$"));

        System.out.println("Introduce el precio: ");
        float precio = entrada.nextFloat();

        System.out.println("Introduce el stock: ");
        int stock = entrada.nextInt();
        entrada.nextLine();

        Plantas nueva = new Plantas(
                id,
                nombre,
                foto,
                descripcion,
                precio,
                stock
        );

        listaPlantas.add(nueva);

        guardarPlantasXML();
        guardarPlantasDat();

        System.out.println("Planta añadida correctamente.");
        
        mostrarPlantas();
        
    }

    
    private static void modificarStock(Scanner entrada) {
		
    	int id = 0;
        int stock = 0;

        System.out.print("Introduce el id de la planta a modificar su stock: ");
        id = entrada.nextInt();
        entrada.nextLine(); // limpiar buffer

        Plantas plantaSeleccionada = buscaPlantaID(id);

        if (plantaSeleccionada == null) {
           
        	System.out.println("Error: planta no encontrada");
            
        	return;
        	
        }

        String input;
        do {
            
        	System.out.print("Introduzca el nuevo stock: ");
            input = entrada.nextLine();

            if (!input.matches("^\\d+$")) {
                
            	System.out.println("Error: debes introducir un número entero válido.");
            
            } else {
                
            	stock = Integer.parseInt(input);
            
            }

        } while (!input.matches("^\\d+$"));

        plantaSeleccionada.setStock(stock);
        guardarPlantasDat();

        System.out.println("Stock modificado correctamente.");
    	
	}
    
    private static void modificarPrecio(Scanner entrada) {
		
    	int id = 0;
    	float precio = 0;
    	
    	System.out.println("Introduce el id de la planta a modificar su precio");
    	id = entrada.nextInt();
    	entrada.nextLine();
    	
    	Plantas plantaSeleccionada = buscaPlantaID(id);
    	
    	if(plantaSeleccionada == null) {
    	    
    		System.out.println("Error planta no encontrada");
    	    
    		return;
    		
    	}
    	
    	String input;
    	
        do {
            
        	System.out.print("Introduzca el nuevo precio: ");
            input = entrada.nextLine();

            if (!input.matches("^\\d+(\\.\\d+)?$")) {
                
            	System.out.println("Error: debes introducir un número decimal válido.");
            
            } else {
                
            	precio = Float.parseFloat(input);
            
            }

        } while (!input.matches("^\\d+(\\.\\d+)?$"));

        plantaSeleccionada.setPrecio(precio);
        guardarPlantasDat();

        System.out.println("Precio modificado correctamente.");
    	
	}
    
    private static void modificarDescripcion(Scanner entrada) {
		
    	int id = 0;
    	String descripcion;
    	
    	System.out.println("Introduce el id de la planta a modificar su descripción");
    	id = entrada.nextInt();
    	entrada.nextLine();
    	
    	Plantas plantaSeleccionada = buscaPlantaID(id);
    	
    	if(plantaSeleccionada == null) {
    	    
    		System.out.println("Error planta no encontrada");
    	    
    		return;
    		
    	}
    	
    	do {
    		
            System.out.print("Introduzca la nueva descripcion de la planta: ");
            descripcion = entrada.nextLine();

            if (!descripcion.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$")) {
               
            	System.out.println("Error: la descripcion solo puede contener letras y espacios.");
            
            }

    	} while (!descripcion.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$"));
    	
    	plantaSeleccionada.setFoto(descripcion);
    	
        guardarPlantasXML();
        
        System.out.println("Descripcion modificada correctamente.");
    	
	}

    private static void modificarFoto(Scanner entrada) {
    	
    	int id = 0;
    	String nombre;
    	
    	System.out.println("Introduce el id de la planta a cambiar la foto");
    	id = entrada.nextInt();
    	entrada.nextLine();
    	
    	Plantas plantaSeleccionada = buscaPlantaID(id);
    	
    	if(plantaSeleccionada == null) {
    	    
    		System.out.println("Error planta no encontrada");
    	    
    		return;
    		
    	}
    	
    	do {
    		
            System.out.print("Introduzca el nuevo nombre de foto de la planta (nombre.jpg): ");
            nombre = entrada.nextLine();

            if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+\\.jpg$")) {
               
            	System.out.println("Error: el nombre de la foto solo puede contener letras y espacios y debe terminar en .jpg");
            
            }

    	} while (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+\\.jpg$"));
    	
    	plantaSeleccionada.setFoto(nombre);
    	
        guardarPlantasXML();
        
        System.out.println("foto de la planta modificada correctamente.");
    	
    }
    
    private static void modificarNombre(Scanner entrada) {
    	
    	int id = 0;
    	String nombre;
    	
    	System.out.println("Introduzca el id de la planta a cambiar el nombre: ");
    	id = entrada.nextInt();
    	entrada.nextLine();
    	
    	Plantas plantaSeleccionada = buscaPlantaID(id);
    	
    	if(plantaSeleccionada == null) {
    	    
    		System.out.println("Error planta no encontrada");
    	    
    		return;
    		
    	}
    	
    	do {
    		
            System.out.print("Introduzca el nuevo nombre de la planta: ");
            nombre = entrada.nextLine();

            if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$")) {
                
            	System.out.println("Error: el nombre solo puede contener letras y espacios.");
            
            }

        } while (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$"));

        plantaSeleccionada.setNombre(nombre);
        
        guardarPlantasXML();
        
        System.out.println("Nombre modificado correctamente.");
    
	}

    public static void modificarCamposPlanta(Scanner entrada) {
    	
    	int opcion = 0;
		
    	System.out.println(VERDE_OSCURITO + "----------------------------MENÚ MODIFICAR CAMPOS----------------------------" + RESET);
    	System.out.println("1. Modificar nombre.");
    	System.out.println("2. Modificar la foto de la planta.");
    	System.out.println("3. Modificar la descripción de la planta.");
    	System.out.println("4. Modificar precio de la planta.");
    	System.out.println("5. Modificar stock.");
    	
    	opcion = entrada.nextInt();
    	entrada.nextLine();
    	
    	switch (opcion) {
		
    	case 1:
    		
    		modificarNombre(entrada);
			break;
			
    	case 2:
    		
    		modificarFoto(entrada);
			break;
			
    	case 3:
    		
    		modificarDescripcion(entrada);
			break;
			
    	case 4:
    		
    		modificarPrecio(entrada);
			break;
			
    	case 5:
    		
    		modificarStock(entrada);
			break;

		default:
			System.out.println("Número introducido incorrecto, cerrando programa");
			break;
		}
    	
    }

	public static void actualizarStock(int id, int cantidadComprada) {
		
		Plantas p = buscaPlantaID(id);
		
		if (p != null) {
			
	        int nuevoStock = p.getStock() - cantidadComprada;

	        if (nuevoStock >= 0) {
	        	
	            p.setStock(nuevoStock);
	            guardarPlantasDat();
	            
	            System.out.println("Stock actualizado. Nuevo stock: " + nuevoStock);
	            
	        } else {
	        	
	            System.out.println("Error: no hay suficiente stock para actualizar.");
	            
	        }
	        
	    } else {
	    	
	        System.out.println("Planta no encontrada, no se puede actualizar el stock.");
	        
	    }
		
	}

	public static boolean comprobarCantidad(int id, int cantidad) {
		
		Plantas p = buscaPlantaID(id);
		
		 if (p != null) {
		        if (cantidad <= p.getStock()) {
		        	
		            return true;
		            
		        } else {
		        	
		            System.out.println("No hay stock suficiente");
		            
		            return false;
		            
		        }
		        
		    } else {
		    	
		        System.out.println("Planta no encontrada");
		        return false;
		    
		    }
		
	}
	
	public static Plantas buscaPlantaID(int id) {
		
		for(Plantas p : listaPlantas) {
			
			if(p != null && p.getCodigo() == id) {
				
				return p;
				
			}
		}
		
		return null;
		
	}
	
	public static void guardarPlantasXML() {
	    
		File archivoXML = new File(PLANTAS_DIR, "plantas.xml");
	    
	    try (PrintWriter pw = new PrintWriter(archivoXML)) {
	        
	    	pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        pw.println("<plantas>");
	        
	        for (Plantas p : listaPlantas) {
	            
	        	pw.println("  <planta>");
	            pw.println("    <codigo>" + p.getCodigo() + "</codigo>");
	            pw.println("    <nombre>" + p.getNombre() + "</nombre>");
	            pw.println("    <foto>" + p.getFoto() + "</foto>");
	            pw.println("    <descripcion>" + p.getDescripcion() + "</descripcion>");
	            pw.println("  </planta>");
	        
	        }
	        
	        pw.println("</plantas>");
	    
	    } catch (IOException e) {
	        
	    	e.printStackTrace();
	    
	    }
	
	}
	
	public static void guardarPlantasDat() {
	    
		File archivoDat = new File(PLANTAS_DIR, "plantas.dat");

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDat, false))) {
	        
	    	ArrayList<Plantas> listaActiva = new ArrayList<>();

	        for (Plantas p : listaPlantas) {
	        	
	            if (p.getCodigo() > 0) {
	                
	            	listaActiva.add(new Plantas(
	                    p.getCodigo(),
	                    p.getNombre(),
	                    p.getFoto(),
	                    p.getDescripcion(),
	                    p.getPrecio(),
	                    p.getStock()
	                
	            			));
	            
	            }
	        
	        }

	        oos.writeObject(listaActiva);
	        System.out.println("plantas.dat actualizado correctamente con " + listaActiva.size() + " plantas activas.");
	    
	    } catch (IOException e) {
	        
	    	System.out.println("Error al guardar plantas.dat");
	        e.printStackTrace();
	    
	    }
	
	}
	
	 public static void crearPlantasDat(float precioPorDefecto, int stockPorDefecto) {
		 
		 File archivoXml = new File(PLANTAS_DIR + File.separator + "plantas.xml");
		 File archivoDat = new File(PLANTAS_DIR + File.separator + "plantas.dat");

		    if (!archivoXml.exists()) {
		        
		    	System.out.println("No se encontró el archvio plantas.xml");
		        return;
		    
		    }

		    if (archivoDat.exists()) {
		       System.out.println("El archivo plantas.dat ya existe");
		        return;
		   
		    }

		    try {
		        
		        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXml);
		        NodeList nodos = doc.getElementsByTagName("planta");

		        ArrayList<Plantas> listaDatos = new ArrayList<>();

		        for (int i = 0; i < nodos.getLength(); i++) {
		        	
		            Element e = (Element) nodos.item(i);
		            int codigo = Integer.parseInt(e.getElementsByTagName("codigo").item(0).getTextContent());
		            String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();
		            String foto = e.getElementsByTagName("foto").item(0).getTextContent();
		            String descripcion = e.getElementsByTagName("descripcion").item(0).getTextContent();
		            
		            float precio;
		            int stock;

		            switch (codigo) {
		            
		                case 1: // Rosa
		                    
		                	precio = 15.0f;
		                    stock = 80;
		                    break;
		                    
		                case 2: // Tulipán
		                    
		                	precio = 12.5f;
		                    stock = 120;
		                    break;
		                    
		                case 3: // Cactus
		                    
		                	precio = 9.0f;
		                    stock = 60;
		                    break;
		                    
		                case 4: //Girasol
		                	
		                	precio = 18.99f;
		                	stock = 180;
		                	break;
		                	
		                case 5: //Margarita
		                	
		                	precio = 5.99f;
		                	stock = 140;
		                	break;
		                    
		                default:
		                    precio = precioPorDefecto;
		                    stock = stockPorDefecto;
		                    break;
		            }

		            listaDatos.add(new Plantas(codigo, nombre, foto, descripcion, precio, stock));
		        }

		        // Escribir en plantas.dat
		        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDat))) {
		            oos.writeObject(listaDatos);
		        }

		        System.out.println("El archvio plantas.dat se ha creado correctamente con precios y stock por defecto.");

		    } catch (Exception e) {
		        System.out.println("Error al crear plantas.dat.");
		        e.printStackTrace();
		    }
	   }
	
	public static void CrearPlantas() {
		
		String ruta = "PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "PLANTAS" + File.separator + "plantas.xml";
        
        File archivo = new File(ruta);
        
        if (archivo.exists()) {
            System.out.println("El archivo plantas.xml ya existe");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {

            writer.write("<plantas>\n");
            writer.write("    <planta>\n");
            writer.write("        <codigo>1</codigo>\n");
            writer.write("        <nombre>Rosa</nombre>\n");
            writer.write("        <tipo>Flor</tipo>\n");
            writer.write("        <foto>rosa.jpg</foto>\n");
            writer.write("        <descripcion>Flor roja muy común en jardines.</descripcion>\n");
            writer.write("    </planta>\n");
            
            writer.write("    <planta>\n");
            writer.write("        <codigo>2</codigo>\n");
            writer.write("        <nombre>Tulipán</nombre>\n");
            writer.write("        <tipo>Flor</tipo>\n");
            writer.write("        <foto>tulipan.jpg</foto>\n");
            writer.write("        <descripcion>Flor colorida originaria de los Países Bajos.</descripcion>\n");
            writer.write("    </planta>\n");
            
            writer.write("    <planta>\n");
            writer.write("        <codigo>3</codigo>\n");
            writer.write("        <nombre>Cactus</nombre>\n");
            writer.write("        <tipo>Suculenta</tipo>\n");
            writer.write("        <foto>cactus.jpg</foto>\n");
            writer.write("        <descripcion>Planta resistente de zonas áridas.</descripcion>\n");
            writer.write("    </planta>\n");
            
            writer.write("    <planta>\n");
            writer.write("        <codigo>4</codigo>\n");
            writer.write("        <nombre>Girasol</nombre>\n");
            writer.write("        <tipo>flor de jardin</tipo>\n");
            writer.write("        <foto>girasol.jpg</foto>\n");
            writer.write("        <descripcion>Planta alta con grandes flores amarillas.</descripcion>\n");
            writer.write("    </planta>\n");
            
            writer.write("    <planta>\n");
            writer.write("        <codigo>5</codigo>\n");
            writer.write("        <nombre>Lavanda</nombre>\n");
            writer.write("        <tipo>flor de jardin</tipo>\n");
            writer.write("        <foto>margarita.jpg</foto>\n");
            writer.write("        <descripcion>Flor blanca con centro amarillo.</descripcion>\n");
            writer.write("    </planta>\n");
            
            writer.write("</plantas>\n");

            System.out.println("El archivo plantas.xml se ha creado correctamente.");

        } catch (IOException e) {
            System.out.println("Error no se pudo crear el archivo plantas.xml.");
        }
		
	}

	
	public static void mostrarPlantas() {
	    
		try {
	        
	    	File xmlFile = new File(PLANTAS_DIR, "plantas.xml");
	       
	        if (!xmlFile.exists()) {
	           
	        	System.out.println("No existe el archivo plantas.xml");
	            return;
	       
	        }
	        
	        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
	        NodeList lista = doc.getElementsByTagName("planta");
	        listaPlantas.clear();

	        for (int i = 0; i < lista.getLength(); i++) {
	            
	        	Element e = (Element) lista.item(i);
	            
	        	int codigo = Integer.parseInt(e.getElementsByTagName("codigo").item(0).getTextContent());
	            String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();
	            String foto = e.getElementsByTagName("foto").item(0).getTextContent();
	            String descripcion = e.getElementsByTagName("descripcion").item(0).getTextContent();


	            listaPlantas.add(new Plantas(codigo, nombre, foto, descripcion, 0f, 0));
	        }

	        File datFile = new File(PLANTAS_DIR, "plantas.dat");
	        
	        if (datFile.exists() && datFile.length() > 0) {
	            
	        	try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(datFile))) {
	                
	        		ArrayList<Plantas> datList = (ArrayList<Plantas>) ois.readObject();
	                
	        		for (Plantas xmlPlanta : listaPlantas) {
	                    
	        			for (Plantas datPlanta : datList) {
	                        
	        				if (xmlPlanta.getCodigo() == datPlanta.getCodigo()) {
	                           
	        					xmlPlanta.setPrecio(datPlanta.getPrecio());
	                            xmlPlanta.setStock(datPlanta.getStock());
	                            
	                            break;
	                        
	        				}
	                   
	        			}
	               
	        		}

	        	}
	        
	        }

	        for (Plantas p : listaPlantas) {
	           
	        	System.out.println(p);
	       
	        }

	    } catch (Exception e) {
	        
	    	e.printStackTrace();
	    
	    }
	
	}

}

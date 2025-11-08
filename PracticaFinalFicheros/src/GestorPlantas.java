
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GestorPlantas {
	
	public static List<Plantas> listaPlantas = new ArrayList<>();
	private static final String BASE_DIR = "PracticaFinalFicheros_NarváezLobatoJeremy";
    private static final String PLANTAS_DIR = BASE_DIR + File.separator + "PLANTAS";


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
	
	public static void guardarPlantasDat() {
		
		File archivoDat = new File(PLANTAS_DIR + File.separator + "plantas.dat");

	    if (!archivoDat.exists()) {
	    	
	        System.out.println("No se encontró plantas.dat.");
	        
	        return;
	    
	    }

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDat))) {
	    	
	        oos.writeObject(new ArrayList<>(listaPlantas)); // Guardamos la lista completa
	        
	        System.out.println("plantas.dat actualizado correctamente.");
	        
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
		
		String ruta = "PracticaFinalFicheros_NarváezLobatoJeremy"
                + File.separator + "PLANTAS" + File.separator + "plantas.xml";
        
        File archivo = new File(ruta);
        
        // Si ya existe, no lo volvemos a crear
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
            
            writer.write("</plantas>\n");

            System.out.println("El archivo plantas.xml se ha creado correctamente.");

        } catch (IOException e) {
            System.out.println("Error no se pudo crear el archivo plantas.xml.");
        }
		
	}

	
	public static void mostrarPlantas() {
		
		 try {
			 
			 // Leer XML   
			 File fichero = new File(PLANTAS_DIR + File.separator + "plantas.xml");
		        
			 Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
		        
			 NodeList lista = doc.getElementsByTagName("planta");

		     listaPlantas.clear();

		     for (int i = 0; i < lista.getLength(); i++) {
		        
		    	 Element e = (Element) lista.item(i);
		           
		    	 int codigo = Integer.parseInt(e.getElementsByTagName("codigo").item(0).getTextContent());
		         String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();
		         String foto = e.getElementsByTagName("foto").item(0).getTextContent();
		         String descripcion = e.getElementsByTagName("descripcion").item(0).getTextContent();

		         // Inicializamos precio y stock
		         listaPlantas.add(new Plantas(codigo, nombre, foto, descripcion, 0f, 0));
		        
		     }

		        // Leer plantas.dat
		        ArrayList<Plantas> listaDatos;
		        
		        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PLANTAS_DIR + File.separator + "plantas.dat"))){
		            
		        	listaDatos = (ArrayList<Plantas>) ois.readObject();
		        
		        }

		        // Combinar datos     
		        for (Plantas xmlPlanta : listaPlantas) {
		            
		        	for (Plantas datPlanta : listaDatos) {
		                
		        		if (xmlPlanta.getCodigo() == datPlanta.getCodigo()) {
		        			xmlPlanta.setPrecio(datPlanta.getPrecio());
		                    xmlPlanta.setStock(datPlanta.getStock());
		                    
		                    break;
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

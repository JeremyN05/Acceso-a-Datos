
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestorPlantas {
	
public static List<Plantas> listaPlantas = new ArrayList<>();

	public static void actualizarStock(int id, int cantidadComprada) {
		
		Plantas p = buscaPlantaID(id);
		
		if (p != null) {
			
	        int nuevoStock = p.getStock() - cantidadComprada;

	        if (nuevoStock >= 0) {
	        	
	            p.setStock(nuevoStock);
	            
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

	
public static void mostrarPlantas() {
		
		ArrayList<Plantas> plantas = null;
		
		try {
			
			File ficheroXML = new File("plantas.xml");
		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();
		
			Document doc = docB.parse(ficheroXML);
			
			plantas = new ArrayList<>();
		
			doc.getDocumentElement().normalize();
			
			NodeList lista = doc.getElementsByTagName("planta");
			
			int cantidad = lista.getLength();
			
			for(int i = 0; i < cantidad; i++) {
				
				Node nodo = lista.item(i);
				
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					
					Element planta = (Element)nodo;
					
					int codigo = Integer.parseInt(planta.getElementsByTagName("codigo").item(0).getTextContent());
					String nombre = planta.getElementsByTagName("nombre").item(0).getTextContent();
					String foto = planta.getElementsByTagName("foto").item(0).getTextContent();
					String descripcion = planta.getElementsByTagName("descripcion").item(0).getTextContent();
					
					// Precio y stock de plantas
					
					float precio = 0;
					int stock = 0;
					
					try {
						
						File fichero = new File("plantas.dat");
						RandomAccessFile raf = new RandomAccessFile(fichero, "r");
						
						long tamRegistro = 12;
						long posicion = (codigo - 1) * tamRegistro;
						
						if(posicion < raf.length()) {
						
							raf.seek(posicion);
							int cod = raf.readInt();
							precio = raf.readFloat();
							stock = raf.readInt();
							
						}
						
						raf.close();
						
					}catch(IOException e) {
						
						e.printStackTrace();
						
					}
					
					Plantas vivero = new Plantas(codigo, nombre, foto, descripcion, precio, stock);
					plantas.add(vivero);
				}
				
			}
			
			listaPlantas.clear();
	        listaPlantas.addAll(plantas);

			
			for(Plantas f : plantas) {
				
				System.out.println(f.toString());
				
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}

	}
	
}

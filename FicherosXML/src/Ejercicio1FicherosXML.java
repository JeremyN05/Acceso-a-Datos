import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio1FicherosXML {
	
	static Scanner entrada = new Scanner(System.in);

	public static void filtrarPorPrecio(Scanner entrada, ArrayList<Frutas> frutas) {
		
		int opcion;
		
		System.out.println("Introduzca el precio a filtrar:");
		String texto = entrada.nextLine().replace(",", "."); // reemplaza coma por punto
		
		double precio;

		try {
		    
			precio = Double.parseDouble(texto);
		      
		} catch (NumberFormatException e) {
		    
			System.out.println("Valor no válido. Introduzca un número válido.");
		    return;
		    
		}
		
		System.out.println("Seleccione el metodo de filtrado: ");
		
		System.out.println("1. Precio más grande");
		System.out.println("2. Precio igual");
		System.out.println("3. Precio más pequeño");
		
		opcion = entrada.nextInt();
		
		System.out.println("\n--- Resultado del filtrado ---");

	    for (Frutas f : frutas) {
	        double precioFruta;

	        try {
	            precioFruta = Double.parseDouble(f.getPrecio());
	        } catch (NumberFormatException e) {
	            
	            continue;
	        }

	        switch (opcion) {
	            case 1:
	                if (precioFruta > precio) System.out.println(f);
	                break;
	            case 2:
	                if (precioFruta == precio) System.out.println(f);
	                break;
	            case 3:
	                if (precioFruta < precio) System.out.println(f);
	                break;
	            default:
	                System.out.println("Opción no válida.");
	        }
	    }
		
	}

	public static void filtrarPorNutriente(Scanner entrada, ArrayList<Frutas> frutas) {

		entrada.nextLine();
		
		System.out.println();
		
	    System.out.println("Introduzca el nutriente a buscar:");
	    String nutrienteBuscado = entrada.nextLine().toLowerCase();

	    System.out.println("\n--- Frutas con el nutriente '" + nutrienteBuscado + "' ---");

	    for (Frutas f : frutas) {
	        for (String n : f.getNutriente()) {
	            if (n.toLowerCase().contains(nutrienteBuscado)) {
	                System.out.println(f);
	                break; // no repetir la fruta si tiene varios nutrientes que coinciden
	            }
	        }
	    }
	}

	
	public static void main(String[] args) {
		
		ArrayList<Frutas> frutas = null;
		
		try {
			
			File ficheroXML = new File("frutas.xml");
		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();
		
			Document doc = docB.parse(ficheroXML);
			
			frutas = new ArrayList<>();
		
			doc.getDocumentElement().normalize();
			
			NodeList lista = doc.getElementsByTagName("fruta");
			
			int cantidad = lista.getLength();

			for(int i = 0; i < cantidad; i++) {
				
				Node nodo = lista.item(i);
				
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					
					Element fruta = (Element)nodo;
					
					String nombre = fruta.getElementsByTagName("nombre").item(0).getTextContent();
					String tipo = fruta.getElementsByTagName("tipo").item(0).getTextContent();
					String color = fruta.getElementsByTagName("color").item(0).getTextContent();
					String origen = fruta.getElementsByTagName("origen").item(0).getTextContent();
					String precio = fruta.getElementsByTagName("precio").item(0).getTextContent();
					String temporada = fruta.getElementsByTagName("temporada").item(0).getTextContent();
					String[] nutriente = {fruta.getElementsByTagName("nutriente").item(0).getTextContent(), fruta.getElementsByTagName("nutriente").item(1).getTextContent()};
					
					Frutas frutilla = new Frutas(nombre, tipo, color, origen, precio, temporada, nutriente);
					frutas.add(frutilla);
				}
				
			}
			
			for(Frutas f : frutas) {
				
				System.out.println(f.toString());
				
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		filtrarPorPrecio(entrada, frutas);
		filtrarPorNutriente(entrada, frutas);
		
	}

}


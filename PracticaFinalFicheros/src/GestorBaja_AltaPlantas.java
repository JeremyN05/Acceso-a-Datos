import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class GestorBaja_AltaPlantas {
	
	public static void darAltaPlantas(Scanner entrada) {
	    File carpetaPlantas = new File("PracticaFinalFicheros_NarváezLobatoJeremy/PLANTAS");

	    System.out.println("Ingrese el ID de la planta a dar de alta:");
	    int idPlanta = entrada.nextInt();
	    entrada.nextLine();

	    // Leer lista de bajas
	    ArrayList<PlantasBaja_Alta> listaBajas = new ArrayList<>();
	    PlantasBaja_Alta plantaAlta = null;
	    File plantasBajaXML = new File(carpetaPlantas, "plantasBaja.xml");

	    if (plantasBajaXML.exists() && plantasBajaXML.length() > 0) {
	       
	    	try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(plantasBajaXML))) {
	            
	        	listaBajas = (ArrayList<PlantasBaja_Alta>) decoder.readObject();
	            
	            for (PlantasBaja_Alta p : listaBajas) {
	                
	            	if (p.getCodigo() == idPlanta) {
	                    
	                	plantaAlta = p;
	                    break;
	                
	                }
	            
	            }
	        
	        } catch (IOException e) {
	            
	        	e.printStackTrace();
	            return;
	       
	        }
	    
	    }

	    if (plantaAlta == null) {
	        
	    	System.out.println("Planta no encontrada en bajas.");
	        return;
	    
	    }

	    listaBajas.remove(plantaAlta);
	   
	    try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(plantasBajaXML))) {
	        
	    	encoder.writeObject(listaBajas);
	    
	    } catch (IOException e) {
	        
	    	e.printStackTrace();
	    
	    }

	    Plantas plantaActiva = new Plantas(
	    	    
	    		plantaAlta.getCodigo(),
	    	    plantaAlta.getNombre(),
	    	    plantaAlta.getFoto(),
	    	    plantaAlta.getDescripcion(),
	    	    plantaAlta.getPrecio(),
	    	    plantaAlta.getStock()
	    	
	    		);

	    	GestorPlantas.listaPlantas.add(plantaActiva);

	    	GestorPlantas.guardarPlantasXML();
	    	GestorPlantas.guardarPlantasDat();

	    	System.out.println("Planta dada de alta correctamente: " + plantaAlta.getNombre());
	}

	public static void darBajaPlantas(Scanner entrada) {
		
	    File carpetaPlantas = new File("PracticaFinalFicheros_NarváezLobatoJeremy/PLANTAS");
	    
	    if (!carpetaPlantas.exists()) {
	        
	    	System.out.println("La carpeta de plantas no existe.");
	        return;
	    
	    }

	    System.out.println("Ingrese el ID de la planta a dar de baja:");
	    int idPlanta = entrada.nextInt();
	    entrada.nextLine();

	    Plantas plantaSeleccionada = GestorPlantas.buscaPlantaID(idPlanta);
	    
	    if (plantaSeleccionada == null) {
	        
	    	System.out.println("Error, planta no encontrada.");
	        return;
	    
	    }

	    PlantasBaja_Alta plantaBaja = new PlantasBaja_Alta(
	        plantaSeleccionada.getCodigo(),
	        plantaSeleccionada.getNombre(),
	        plantaSeleccionada.getFoto(),
	        plantaSeleccionada.getDescripcion(),
	        plantaSeleccionada.getPrecio(),
	        plantaSeleccionada.getStock()
	    );

	    ArrayList<PlantasBaja_Alta> listaBajas = new ArrayList<>();
	    File plantasBajaXML = new File(carpetaPlantas, "plantasBaja.xml");

	    if (plantasBajaXML.exists() && plantasBajaXML.length() > 0) {
	        
	    	try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(plantasBajaXML))) {
	            listaBajas = (ArrayList<PlantasBaja_Alta>) decoder.readObject();
	        
	    	} catch (IOException e) {
	           
	    		e.printStackTrace();
	        
	    	}
	    
	    }

	    listaBajas.add(plantaBaja);

	    try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(plantasBajaXML))) {
	        
	    	encoder.writeObject(listaBajas);
	    
	    } catch (IOException e) {
	       
	    	e.printStackTrace();
	    
	    }

	    listaBajas.remove(plantaSeleccionada);

	    GestorPlantas.guardarPlantasXML();

	    GestorPlantas.guardarPlantasDat();

	    System.out.println("Planta dada de baja correctamente.");
	}

	
}

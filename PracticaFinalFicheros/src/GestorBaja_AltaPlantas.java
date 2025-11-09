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
import org.w3c.dom.NodeList;

public class GestorBaja_AltaPlantas {
	
	public static void darAltaPlantas(Scanner entrada) {
	    File carpetaPlantas = new File("PracticaFinalFicheros_NarváezLobatoJeremy/PLANTAS");
	    
	    if (!carpetaPlantas.exists()) {
	        
	    	System.out.println("La carpeta de plantas no existe.");
	        
	        return;
	    
	    }

	    File plantasXML = new File(carpetaPlantas, "plantas.xml");
	    File plantasDAT = new File(carpetaPlantas, "plantas.dat");
	    File plantasBajaXML = new File(carpetaPlantas, "plantasBaja.xml");
	    File plantasBajaDAT = new File(carpetaPlantas, "plantasbaja.dat");

	    System.out.println("Ingrese el ID de la planta a dar de alta:");
	    int idPlanta = entrada.nextInt();
	    entrada.nextLine();

	    //Le0 plantasBaja.xml para buscar la planta que indico el usuario
	    
	    ArrayList<PlantasBaja_Alta> listaBajasXML = new ArrayList<>();
	    PlantasBaja_Alta plantaAlta = null;

	    if (plantasBajaXML.exists() && plantasBajaXML.length() > 0) {
	        
	    	try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(plantasBajaXML))) {
	            
	        	listaBajasXML = (ArrayList<PlantasBaja_Alta>) decoder.readObject();

	            for (PlantasBaja_Alta p : listaBajasXML) {
	                
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

	    
	    //Elimino la planta que indico el usuario de listaBajasXML y actualizo plantasBaja.xml 
	    
	    listaBajasXML.remove(plantaAlta);
	    
	    try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(plantasBajaXML))) {
	        
	    	encoder.writeObject(listaBajasXML);
	    
	    } catch (IOException e) {
	        
	    	e.printStackTrace();
	    
	    }

	    //Elimino la planta que va a ser dada de alta de plantasbaja.dat
	    try {
	        
	    	File tempBajaDat = new File(carpetaPlantas, "tempbaja.dat");
	        try (RandomAccessFile raf = new RandomAccessFile(plantasBajaDAT, "r");
	             
	        		RandomAccessFile rafTemp = new RandomAccessFile(tempBajaDat, "rw")) {

	            while (raf.getFilePointer() < raf.length()) {
	                
	            	int codigo = raf.readInt();
	                float precio = raf.readFloat();
	                int stock = raf.readInt();

	                if (codigo != idPlanta) {
	                    
	                	rafTemp.writeInt(codigo);
	                    rafTemp.writeFloat(precio);
	                    rafTemp.writeInt(stock);
	                
	                }
	            
	            }
	        
	        }
	        
	        plantasBajaDAT.delete();
	        
	        tempBajaDat.renameTo(plantasBajaDAT);
	   
	    } catch (IOException e) {
	        
	    	e.printStackTrace();
	    
	    }

	 //Vuelvo a agregar la planta dada de alta a plantas.xml
	    
	    try {
	        
	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc;
	        Element root;

	        if (plantasXML.exists() && plantasXML.length() > 0) {
	            
	        	doc = builder.parse(plantasXML);
	            
	            root = (Element) doc.getElementsByTagName("plantas").item(0);
	        
	        } else {

	        	doc = builder.newDocument();
	            root = doc.createElement("plantas");
	            doc.appendChild(root);
	        
	        }

	        Element plantaElem = doc.createElement("planta");
	        Element codigo = doc.createElement("codigo");
	        codigo.setTextContent(String.valueOf(plantaAlta.getCodigo()));
	        Element nombre = doc.createElement("nombre");
	        nombre.setTextContent(plantaAlta.getNombre());
	        Element foto = doc.createElement("foto");
	        foto.setTextContent("X");
	        Element descripcion = doc.createElement("descripcion");
	        descripcion.setTextContent("Ninguna");

	        plantaElem.appendChild(codigo);
	        plantaElem.appendChild(nombre);
	        plantaElem.appendChild(foto);
	        plantaElem.appendChild(descripcion);
	        root.appendChild(plantaElem);

	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(new DOMSource(doc), new StreamResult(plantasXML));
	        
	        System.out.println("Planta agregada a plantas.xml");
	    
	    } catch (Exception e) {
	        
	    	e.printStackTrace();
	    
	    }

	    // actualizo la informacion en planta.dat
	    
	    try (RandomAccessFile raf = new RandomAccessFile(plantasDAT, "rw")) {
	        
	    	long pos = (plantaAlta.getCodigo() - 1) * (Integer.BYTES + Float.BYTES + Integer.BYTES);
	        raf.seek(pos);
	        raf.writeInt(plantaAlta.getCodigo());
	        raf.writeFloat(plantaAlta.getPrecio());
	        raf.writeInt(plantaAlta.getStock());
	        
	        System.out.println("Planta actualizada en plantas.dat con precio y stock originales.");
	    
	    } catch (IOException e) {
	        
	    	e.printStackTrace();
	    
	    }

	    System.out.println("Planta dada de alta correctamente: " + plantaAlta.getNombre());
	}


	public static void darBajaPlantas(Scanner entrada) {
		
		File carpetaPlantas = new File("PracticaFinalFicheros_NarváezLobatoJeremy/PLANTAS");
	    
		if (!carpetaPlantas.exists()) {
	        
			System.out.println("La carpeta de plantas no existe.");
	        
			return;
	    
		}

	    File plantasXML = new File(carpetaPlantas, "plantas.xml");
	    File plantasDAT = new File(carpetaPlantas, "plantas.dat");
	    File plantasBajaXML = new File(carpetaPlantas, "plantasBaja.xml");
	    File plantasBajaDAT = new File(carpetaPlantas, "plantasbaja.dat");

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
	            plantaSeleccionada.getPrecio(),
	            plantaSeleccionada.getStock()
	    
	    		);

	    try (RandomAccessFile raf = new RandomAccessFile(plantasDAT, "rw")) {
	        
	    	long pos = (plantaSeleccionada.getCodigo() - 1) * (Integer.BYTES + Float.BYTES + Integer.BYTES);
	        
	        raf.seek(pos);
	        raf.writeInt(plantaSeleccionada.getCodigo());
	        raf.writeFloat(0f);
	        raf.writeInt(0);
	        
	        System.out.println("Planta actualizada en plantas.dat (precio y stock a 0).");
	    
	    } catch (IOException e) {
	        
	    	e.printStackTrace();
	        
	        return;
	    
	    }


	    if (plantasXML.exists()) {
	        
	    	try {
	            
	        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(plantasXML);

	            NodeList listaPlantas = doc.getElementsByTagName("planta");
	            
	            for (int i = 0; i < listaPlantas.getLength(); i++) {
	                
	            	Element plantaElem = (Element) listaPlantas.item(i);
	                
	                int codigo = Integer.parseInt(plantaElem.getElementsByTagName("codigo").item(0).getTextContent());
	                
	                if (codigo == idPlanta) {
	                    
	                	plantaElem.getParentNode().removeChild(plantaElem);
	                    
	                    break;
	                
	                }
	            
	            }

	            
	            Transformer transformer = TransformerFactory.newInstance().newTransformer();
	            
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            
	            transformer.transform(new DOMSource(doc), new StreamResult(plantasXML));

	            
	            System.out.println("Planta eliminada de plantas.xml");
	        
	        } catch (Exception e) {
	            
	        	e.printStackTrace();
	        
	        }
	    
	    }
	    
	    ArrayList<PlantasBaja_Alta> listaBajasXML = new ArrayList<>();
	    
	    if (plantasBajaXML.exists() && plantasBajaXML.length() > 0) {

	    	try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(plantasBajaXML))) {
	            
	        	listaBajasXML = (ArrayList<PlantasBaja_Alta>) decoder.readObject();
	        
	        } catch (IOException e) {
	            
	        	e.printStackTrace();
	        
	        }
	    
	    }
	    
	    listaBajasXML.add(plantaBaja);
	    
	    try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(plantasBajaXML))) {
	        
	    	encoder.writeObject(listaBajasXML);
	        
	        System.out.println("Planta guardada en plantasBaja.xml");
	    
	    } catch (IOException e) {
	        
	    	e.printStackTrace();
	    
	    }

	    try (RandomAccessFile rafBaja = new RandomAccessFile(plantasBajaDAT, "rw")) {
	    	
	    	rafBaja.seek(rafBaja.length());
	        rafBaja.writeInt(plantaBaja.getCodigo());
	        rafBaja.writeFloat(plantaBaja.getPrecio());
	        rafBaja.writeInt(plantaBaja.getStock());
	        
	        System.out.println("Planta guardada en plantasbaja.dat");
	    
	    } catch (IOException e) {
	        
	    	e.printStackTrace();
	    
	    }
	
	}
	
}

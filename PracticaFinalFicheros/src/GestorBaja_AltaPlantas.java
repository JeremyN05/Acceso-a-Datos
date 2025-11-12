import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorBaja_AltaPlantas {

    private static final String BASE_DIR = "PracticaFinalFicheros_NarváezLobatoJeremy";
    private static final String PLANTAS_DIR = BASE_DIR + File.separator + "PLANTAS";
    private static final String BAJA_DIR = PLANTAS_DIR + File.separator + "BAJA";

    public static void darBajaPlantas(Scanner entrada) {
        
    	System.out.print("Ingrese el ID de la planta a dar de baja: ");
        int idPlanta = entrada.nextInt();
        entrada.nextLine();

        Plantas plantaBaja = GestorPlantas.buscaPlantaID(idPlanta);
       
        if (plantaBaja == null) {
           
        	System.out.println("❌ Planta no encontrada en activas.");
           
            return;
       
        }


        GestorPlantas.listaPlantas.remove(plantaBaja);
        GestorPlantas.guardarPlantasXML();
        GestorPlantas.guardarPlantasDat();

        File carpetaBajas = new File(BAJA_DIR);
        if (!carpetaBajas.exists()) { 
        	
        	carpetaBajas.mkdirs();

        }
        
        File archivoBaja = new File(carpetaBajas, "plantasBaja.xml");
        ArrayList<PlantasBaja_Alta> listaBajas = new ArrayList<>();

        if (archivoBaja.exists() && archivoBaja.length() > 0) {
            
        	try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(archivoBaja))) {
             
            	listaBajas = (ArrayList<PlantasBaja_Alta>) decoder.readObject();
           
            } catch (Exception e) {
               
            	e.printStackTrace();
           
            }
        
        }

        listaBajas.add(new PlantasBaja_Alta(
                plantaBaja.getCodigo(),
                plantaBaja.getNombre(),
                plantaBaja.getFoto(),
                plantaBaja.getDescripcion(),
                plantaBaja.getPrecio(),
                plantaBaja.getStock()
        
        		));

        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(archivoBaja))) {
          
        	encoder.writeObject(listaBajas);
        
        } catch (IOException e) {
           
        	e.printStackTrace();
        
        }

        System.out.println("Planta dada de baja y guardada en: " + archivoBaja.getPath());
    
    }

    public static void darAltaPlantas(Scanner entrada) {
        
    	File archivoBaja = new File(BAJA_DIR, "plantasBaja.xml");
       
        if (!archivoBaja.exists() || archivoBaja.length() == 0) {
           
        	System.out.println("❌ No hay plantas en baja para dar de alta.");
            
            return;
        
        }

        ArrayList<PlantasBaja_Alta> listaBajas;
       
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(archivoBaja))) {
            
        	listaBajas = (ArrayList<PlantasBaja_Alta>) decoder.readObject();
        
        } catch (Exception e) {
           
        	System.out.println("❌ Error al leer plantas en baja.");
            e.printStackTrace();
           
            return;
        
        }

        System.out.println("Plantas en baja:");
        
        for (PlantasBaja_Alta p : listaBajas) {
           
        	System.out.printf("%d - %s (Stock: %d, Precio: %.2f)\n", p.getCodigo(), p.getNombre(), p.getStock(), p.getPrecio());
       
        }

        System.out.print("Ingrese el ID de la planta a dar de alta: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        PlantasBaja_Alta seleccionada = null;
        
        for (PlantasBaja_Alta p : listaBajas) {
            
        	if (p.getCodigo() == id) {
               
            	seleccionada = p;
                
                break;
           
            }
        
        }

        if (seleccionada == null) {
           
        	System.out.println("❌ No se encontró ninguna planta con ese ID.");
            return;
        
        }

        Plantas nueva = new Plantas(
                seleccionada.getCodigo(),
                seleccionada.getNombre(),
                seleccionada.getFoto(),
                seleccionada.getDescripcion(),
                seleccionada.getPrecio(),
                seleccionada.getStock()
        
        		);

        GestorPlantas.listaPlantas.add(nueva);
        GestorPlantas.guardarPlantasXML();
        GestorPlantas.guardarPlantasDat();

        listaBajas.remove(seleccionada);
        
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(archivoBaja))) {
            encoder.writeObject(listaBajas);
        
        }catch(IOException e) {
        	
        	e.printStackTrace();
        	
        }

        System.out.println("Planta dada de alta correctamente: " + seleccionada.getNombre());
    }

}


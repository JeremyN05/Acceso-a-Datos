
import java.io.File;
import java.util.Scanner;

public class Main {

	static Scanner entrada = new Scanner(System.in);
	private static final String VERDE = "\u001B[32m";
	private static final String RESET = "\u001B[0m";
	private static final String CYAN = "\u001B[36m";
	private static final String VERDE_CLARITO = "\u001B[38;2;144;238;144m";
	
	private static void crearArbolFicheros() {
		
		 String baseDir = System.getProperty("user.dir") + File.separator + "PracticaFinalFicheros_NarváezLobatoJeremy";

		    String[] carpetas = {
		        
		    	baseDir + File.separator + "PLANTAS",
		        baseDir + File.separator + "EMPLEADOS",
		        baseDir + File.separator + "EMPLEADOS" + File.separator + "BAJA",
		        baseDir + File.separator + "TICKETS",
		        baseDir + File.separator + "DEVOLUCIONES"
		    
		    };

		    
		    File base = new File(baseDir);
		    
		    if (!base.exists()) {
		        
		    	base.mkdirs();
		    
		    }

		   
		    for (String carpeta : carpetas) {
		        
		    	File dir = new File(carpeta);
		        
		    	if (!dir.exists()) {
		           
		    		dir.mkdirs();
		        
		    	}
		    
		    }
		    
		    System.out.println("Estructura verificada y actualizada correctamente en: " + baseDir);
		
	}
	
	public static void main(String[] args) {
		
		crearArbolFicheros(); //Crea el arbol de fichero
		
		 // Compruebo que las carpetas existan antes de crear archivos
        File plantasDir = new File(System.getProperty("user.dir") + File.separator + "PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "PLANTAS");
        
        if (!plantasDir.exists()) {
        
        	plantasDir.mkdirs();
        	
        }

        File empleadosDir = new File(System.getProperty("user.dir") + File.separator + "PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "EMPLEADOS");
        
        if (!empleadosDir.exists()) {
        	
        	empleadosDir.mkdirs();
        	
        } 
		
		GestorEmpleados.EscribirEmpleado(); //Crea el fichero empleados.dat con sus datos
		GestorPlantas.CrearPlantas(); //Crea planta.xml con sus datos
		GestorPlantas.crearPlantasDat(18.5f, 100);
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.println(VERDE + "------------------------------------------------------------------------------PLANTAS------------------------------------------------------------------------------");
		System.out.printf("%-15s %-15s %-15s %-65s %-10s %-15s \n", "ID", "Nombre", "Foto", "Descripcion", "Precio", "Stock");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
		
		GestorPlantas.mostrarPlantas();
		System.out.println("\n");
		
		System.out.println(CYAN + "---------------------------------------EMPLEADOS---------------------------");
		System.out.printf("%-15s %-15s %-15s %-15s \n", "ID", "Nombre", "contraseña", "cargo");
		System.out.println("---------------------------------------------------------------------------" + RESET);
		GestorEmpleados.leerEmpleados(true);
		
		System.out.println("\n");
		
		System.out.println(VERDE_CLARITO + "----------------Inicio Sesión----------------" + RESET);
		InicioSesion.IniciarSesion(entrada);
		

	}

}

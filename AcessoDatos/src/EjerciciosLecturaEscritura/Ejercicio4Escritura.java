package EjerciciosLecturaEscritura;

import java.io.File;
import java.util.Scanner;


public class Ejercicio4Escritura {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		
		String fichero1;
		String fichero2;
		String ruta;
		
		System.out.println("Introduzca el nombre del primer fichero: ");
		fichero1 = entrada.nextLine();
		System.out.println("Introduzca el nombre del segundo fichero: ");
		fichero2 = entrada.nextLine();
		System.out.println("Introduzca el nombre de la ruta: ");
		ruta = entrada.nextLine();
		
		File directorio = new File(ruta);
		
		if(directorio.exists() && directorio.isDirectory()) {
			
			File archivo1 = new File(fichero1);
			File archivo2 = new File(fichero2);
			String ficheroNuevo = fichero1 + "_" + fichero2;
			
			if(archivo1.exists() && archivo1.isFile()) {
				
				if(archivo2.exists() && archivo2.isFile()) {
					
					
					
				}
				
			}
			
		}else {
			
			
			
		}
		
		entrada.close();

	}

}

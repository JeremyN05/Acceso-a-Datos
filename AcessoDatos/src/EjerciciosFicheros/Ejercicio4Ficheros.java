package EjerciciosFicheros;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4Ficheros {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		
		String directorio;
		String fichero;
		
		System.out.println("Introduzca el nombre del directorio a crear: ");
		
		directorio = entrada.nextLine();
		
		File directorios = new File(directorio);
		
		if(directorios.exists() && directorios.isDirectory()) {
			
			System.out.println("El directorio: " + directorio + " ya existe, introduzca otro nombre.");
			
		}else {
			
			System.out.println("El directorio: " + directorio + " se ah creado correctamente " + directorios.mkdir());
			
		}
		
		System.out.println("Introduzca el nombre del fichero a crear dentro del direcorio " + directorio);
		
		fichero = entrada.nextLine();
		
		File ficheros = new File(directorios, fichero);
		
		if(ficheros.exists()) {
			
			System.out.println("El fichero: " + fichero + " ya existe, introduzca otro nombre");
			
		}else {
			
			try {
				
				ficheros.createNewFile();
				
			}catch(IOException io){
				
				io.printStackTrace();
				
			}
			
			System.out.println("El fichero: " + fichero + " se ah creado correctamente dentro del directorio: " + directorio);
			
		}
		
		entrada.close();

	}

}

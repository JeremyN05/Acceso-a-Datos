package EjerciciosFicheros;

import java.io.File;
import java.util.Scanner;

public class Ejercicio1Ficheros {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		
		String directorio;
		
		System.out.println("Introduce el nombre del directorio para listar todos sus elementos: ");
		
		directorio = entrada.nextLine();
		
		File directorios = new File(directorio);
		
		if(directorios.exists() || directorios.isDirectory()) {
			
			String[] elementos = directorios.list();
			
			System.out.println("Contenido del directorio " + directorio + " es: ");
			
			if(elementos != null && elementos.length > 0) {
				
				for(String elemento : elementos) {
					
					System.out.println(elemento);
					
				}
				
			}
			
		}else {
			
			System.out.println("El directorio esta vacio o no existe.");
			
		}
		
		entrada.close();

	}

}

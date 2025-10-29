package EjerciciosFicheros;

import java.io.File;
import java.util.Scanner;

public class Ejercicio6Ficheros {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		
		String fichero;
		
		System.out.println("Introduce el nombre del fichero a crear: ");
		
		fichero = entrada.nextLine();
		
		File ficheros = new File(fichero);
		
		if(ficheros.exists()) {
			
			System.out.println("La ruta del fichero " + fichero + " es: " + ficheros.getAbsolutePath());
			
		}else {
			
			System.out.println("El fichero " + fichero + " no existe cambiando los permisos de lectura");
			
			ficheros.setReadable(false);
			
			System.out.println("El permiso de lectura se a modificado " + ficheros.canRead());
			
		}
		
		entrada.close();


	}

}

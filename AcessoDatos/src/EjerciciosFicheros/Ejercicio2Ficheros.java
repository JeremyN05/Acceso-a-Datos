package EjerciciosFicheros;

import java.io.File;
import java.util.Scanner;

public class Ejercicio2Ficheros {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		
		String fichero;
		
		System.out.println("Introduzca el nombre del fichero que quiere comprobar: ");
		
		fichero = entrada.nextLine();
		
		File ficheros = new File (fichero);
		
		if(ficheros.exists() && ficheros.isFile()) {
			
			System.out.println("El fichero: " + fichero + " si existe, borrando fichero.\n");
			
			if(ficheros.delete() == true) {
				
				System.out.println("El fichero: " + fichero + " se ha eliminado correctamente.");
				
			}
			
		}else {
			
			System.out.println("El fichero: " + fichero + " no existe");
			
		}
		
		entrada.close();


	}

}

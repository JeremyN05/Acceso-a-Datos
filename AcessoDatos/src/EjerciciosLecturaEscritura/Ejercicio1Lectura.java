package EjerciciosLecturaEscritura;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1Lectura {

	public static void main(String[] args) {

Scanner entrada = new Scanner(System.in);
		
		String nombreFichero;
		
		System.out.println("Introduzca el nombre del fichero de texto para leer su contenido sin espacios: ");
		
		nombreFichero = entrada.nextLine();
		
		File fichero = new File(nombreFichero);
		
		if (fichero.isFile()) {
			
			System.out.println("El fichero " + nombreFichero + " si es un fichero y su contenido es: ");
			
			try (FileReader fr = new FileReader(fichero)) {
				int caracter;
				while ((caracter = fr.read()) != -1) {
					if (caracter != ' ') { 
						System.out.print((char) caracter);
					}
				}
				System.out.println();
			} catch (IOException e) {
				System.out.println("Error al leer el fichero: " + e.getMessage());
			}
			
		}else {
			
			System.out.println(nombreFichero + " no es un fichero");
			
		}
		
		entrada.close();

	}

}

package EjerciciosLecturaEscritura;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2Lectura {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		
		String nombreFichero;
		
		System.out.println("Introduzca el nombre del fichero de texto para contar sus caracteres y vocales: ");
		
		nombreFichero = entrada.nextLine();
		
		File fichero = new File(nombreFichero);
		
		if (fichero.isFile()) {
			
			System.out.println(nombreFichero + " si es un fichero, procesando...");
			
			int numCaracteres = 0;
			int numVocales = 0;
			
			try (FileReader fr = new FileReader(fichero)) {
				int caracter;
				while ((caracter = fr.read()) != -1) {
					numCaracteres++;
					
					char c = Character.toLowerCase((char) caracter);
					if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
						numVocales++;
					}
				}
			} catch (IOException e) {
				System.out.println("Error al leer el fichero: " + e.getMessage());
			}
			
			System.out.println("Número total de caracteres: " + numCaracteres);
			System.out.println("Número total de vocales: " + numVocales);
			
		}else {
			
			System.out.println(nombreFichero + " no es un fichero");
			
		}
		
		entrada.close();

	}

}

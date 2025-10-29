package EjerciciosLecturaEscritura;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio2Escritura {

	public static void main(String[] args) {
			
		String nombreFichero = "registroDeUsuario.txt";
		Scanner entrada = new Scanner(System.in);
			
		try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero))) {
				
		String frase;
				
		System.out.println("Escribe frases para guardarlas en el fichero (escribe 'fin' para terminar):");
				
			while (true) {
				frase = entrada.nextLine();
					
			if (frase.equalsIgnoreCase("fin")) {
				break; // termina el bucle
			}
					
			pw.println(frase); // guarda la frase en el fichero
			}
				
				System.out.println("Se han guardado las frases en " + nombreFichero);
				
			} catch (IOException e) {
				System.out.println("Error al escribir en el fichero: " + e.getMessage());
			}
			
			entrada.close();

	}

}

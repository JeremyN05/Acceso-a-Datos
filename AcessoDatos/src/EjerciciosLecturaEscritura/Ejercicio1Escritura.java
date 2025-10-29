package EjerciciosLecturaEscritura;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ejercicio1Escritura {

	public static void main(String[] args) {


		String nombreFichero = "primos.txt";
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero))) {
			
			for (int i = 2; i <= 500; i++) {
				if (esPrimo(i)) {
					pw.println(i); // escribe el número primo en una línea
				}
			}
			
			System.out.println("Se ha creado el fichero " + nombreFichero + " con los números primos del 1 al 500.");
			
		} catch (IOException e) {
			System.out.println("Error al crear el fichero: " + e.getMessage());
		}
	}
	
	// Método auxiliar para comprobar si un número es primo
	public static boolean esPrimo(int numero) {
		if (numero < 2) return false;
		for (int i = 2; i <= Math.sqrt(numero); i++) {
			if (numero % i == 0) return false;
		}
		return true;

	}

}

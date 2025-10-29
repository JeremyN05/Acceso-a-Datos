package EjerciciosLecturaEscritura;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio3Escritura {

	public static void main(String[] args) {

		 Scanner entrada = new Scanner(System.in);

	        System.out.println("¿Cuántos números aleatorios positivos quieres generar?");
	        int cantidad = entrada.nextInt();
	        entrada.nextLine(); // limpiar buffer

	        System.out.println("Introduce la ruta o nombre del fichero donde guardarlos:");
	        String nombreFichero = entrada.nextLine();

	        Random random = new Random();

	        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero))) {

	            for (int i = 0; i < cantidad; i++) {
	                int numero = random.nextInt(1000) + 1; // aleatorio entre 1 y 1000
	                pw.println(numero);
	            }

	            System.out.println("Se han guardado " + cantidad + 
	                               " números aleatorios en el fichero " + nombreFichero);

	        } catch (IOException e) {
	            System.out.println("Error al escribir en el fichero: " + e.getMessage());
	        }

	        entrada.close();

	}

}

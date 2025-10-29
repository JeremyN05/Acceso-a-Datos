package EjerciciosLecturaEscritura;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3Lectura {

	public static void main(String[] args) {
		
		try {
            // Abrimos el fichero restaurantes.csv
            File archivo = new File("restaurantes.csv");
            Scanner lector = new Scanner(archivo);

            // Leemos la primera línea (los encabezados)
            String encabezado = lector.nextLine();
            String[] campos = encabezado.split(",");

            // Ahora procesamos cada línea de datos
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();

                // Dividir por comas que no estén dentro de comillas
                String[] valores = linea.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                // Quitar comillas si existen
                for (int i = 0; i < valores.length; i++) {
                    valores[i] = valores[i].replaceAll("^\"|\"$", "").trim();
                }

                // Mostrar en formato campo: valor
                for (int i = 0; i < campos.length; i++) {
                    System.out.print(campos[i] + ": " + valores[i]);
                    if (i < campos.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }

            lector.close();

        } catch (IOException e) {
            System.out.println("No se encontró el fichero restaurantes.csv");
        }

	}

}

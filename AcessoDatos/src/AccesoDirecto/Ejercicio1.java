package AccesoDirecto;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class Ejercicio1 {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		
		try {
			
			File fichero = new File("Fibonacci.bin");
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			
			int numeros;
			int a = 0;
			int b = 1;
			int siguiente = 0;
			int posicion = 0;
			
			System.out.println("Introduce el número de números a generar: ");
			
			numeros = entrada.nextInt();
			
			for(int i = 0; i < numeros; i++) {
				
				siguiente = a + b;
				
				System.out.print(siguiente + " ");
				
				raf.writeInt(siguiente);
				
				a = b;
				b = siguiente;
				
			}
			
			System.out.println("\n" + fichero + " se ha creado correctamente");
			System.out.println("Introduzca una posición a buscar: ");
			posicion = entrada.nextInt();
			
			raf.seek(posicion * 4);
			
			System.out.println(raf.readInt());
			
			raf.close();
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
		entrada.close();

	}

}

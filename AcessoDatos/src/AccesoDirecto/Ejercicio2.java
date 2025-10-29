package AccesoDirecto;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		
		int opcion = 0;
		
		do {
			
			System.out.println("---Menú---");
			System.out.println("1. --> Almacenar productos");
			System.out.println("2. --> Visualizar productos");
			System.out.println("3. --> Visualizar datos de un producto por ID");
			System.out.println("4. --> Borrar producto por ID");
			System.out.println("5. --> Modificar (cantidad y precio) de un producto");
			System.out.println("6. --> Salir");
			
			opcion = entrada.nextInt();
			
			switch (opcion) {
			case 1:
				
				almacenarProductos(entrada);
				break;
				
			case 2:
				
				
				break;
				
			case 3:
				
				
				break;
				
			case 4:
				
				
				break;
				
			case 5:
				
				
				break;
				
			case 6:
				
				System.out.println("Saliendo del programa");
				break;

			default:
				break;
			}
			
		} while (opcion < 6);
		
		entrada.close();

	}
	
	private static void almacenarProductos(Scanner entrada) {
		
		int numProductos;
		int id;
		int cantidad;
		int precio;
		
		System.out.println("¿Cuántos productos desea almacenar: ");
		
		numProductos = entrada.nextInt();
		
		try {
			
			File fichero = new File("productos.bin");
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			long numProducto = raf.length() /16;
			
			for(int i = 0; i < numProductos; i++) {
				
				id = (int) numProducto + 1;
				
				System.out.println("Indica la cantidad del producto: ");
				cantidad = entrada.nextInt();
				System.out.println("Indique el precio del producto: ");
				precio = entrada.nextInt();
				
				raf.seek(raf.length());
				raf.writeInt(id);
				raf.writeInt(cantidad);
				raf.writeInt(precio);
			
				numProducto++;
			}
			
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}

	}

}

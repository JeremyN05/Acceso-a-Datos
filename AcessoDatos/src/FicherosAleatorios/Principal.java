package FicherosAleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Principal {

	public static void main(String[] args) {

		int numeroLista; //4 bytes
		String nombre = "Pedro"; // un caracter son 2 bytes 2 x 20 = 40 bytes
		double notas; //8 bytes
		
		try{
			
			//Creamos el fichero
			File fichero = new File("datos.dat");
			fichero.createNewFile();
			
			//El segundo parametro indicamos si r- lectura, w-escritura o rw- lectura y escritura
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			
			//Alumnos a mano
			
			raf.writeInt(1); //4 bytes
			raf.writeChars(nombre); // 2 x 5 = 10 bytes
			raf.writeDouble(6.5); // 8 bytes
			
			//Imprimimos la posicion del puntero
			System.out.println(raf.getFilePointer());
			
			/*raf.seek(4); //saca el dato del 4 byte
			
			//lee los siguientes 4 bytes
			System.out.println(raf.readInt());*/
			
			raf.seek(0);
			String cadena = "";
			
			while(raf.getFilePointer() < raf.length()) {
				
				System.out.println(raf.readInt());
				
				for(int i = 0; i < nombre.length(); i++) {
					
					cadena += raf.readChar();
					
				}
				
				System.out.println(cadena);
				System.out.println(raf.readDouble());
				
			}
			
			raf.close();
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}


	}

}

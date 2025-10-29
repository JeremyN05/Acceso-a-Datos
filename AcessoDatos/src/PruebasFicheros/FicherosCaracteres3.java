package PruebasFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FicherosCaracteres3 {

	public static void main(String[] args) {

		//Dentro de new File se pone la ruta del archjvo o fichero
				File fichero = new File ("FicheroEjemplo1.txt");
				
				if(!fichero.exists()) {
					
					System.out.println("Nombre fichero " + fichero.getName());
					System.out.println("Ruta " + fichero.getPath());
					System.out.println("Ruta absoluta " + fichero.getAbsolutePath());
					System.out.println("Tamaño " + fichero.length());
					System.out.println("Permiso lectura " + fichero.canRead());
					System.out.println("Permiso de ejecución " + fichero.canExecute());
					System.out.println("Permiso escritura " + fichero.canWrite());
					
					try {
						
						//Crear el fichero
						fichero.createNewFile();
						
						BufferedWriter pw = new BufferedWriter(new FileWriter(fichero, true));
						
						for(int i = 0; i < 10; i++) {
						
							pw.write("Linea: " + i);
							pw.newLine();
						
						}
						
						pw.close();
						
						//Devuelve caracter a careacter 
						FileReader lector = new FileReader(fichero);
						
						//lleno el buffer de los caracteres y leo lineas
						BufferedReader buffer = new BufferedReader(lector);
						
						String linea;
						
						while((linea = buffer.readLine()) != null) {
							
							System.out.println(linea);
							
						}
						
					}catch(IOException e){
						
						e.getMessage();
						
					}finally {
						
						System.out.println("Adios");
						
					}
					
					
				}else {
					
					System.out.println("No existe el fichero");
					
					try {
						
						fichero.createNewFile();
						
					}catch(IOException io){
						
						io.printStackTrace();
						
					}
					
				}

	}

}

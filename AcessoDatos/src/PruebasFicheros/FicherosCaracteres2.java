package PruebasFicheros;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FicherosCaracteres2 {

	public static void main(String[] args) {

		//Dentro de new File se pone la ruta del archjvo o fichero
				File fichero = new File ("FicheroEjemplo.txt");
				
				if(!fichero.exists()) {
					
					try {
						
						//Crear el fichero
						fichero.createNewFile();
						
						System.out.println("Nombre fichero " + fichero.getName());
						System.out.println("Ruta " + fichero.getPath());
						System.out.println("Ruta absoluta " + fichero.getAbsolutePath());
						System.out.println("Tamaño " + fichero.length());
						System.out.println("Permiso lectura " + fichero.canRead());
						System.out.println("Permiso de ejecución " + fichero.canExecute());
						System.out.println("Permiso escritura " + fichero.canWrite());
						
						FileWriter escritura = new FileWriter(fichero); //BufferedWriter pw = new BuferredWriter(new FileWrite(Fichero, true);
						PrintWriter pw = new PrintWriter(escritura);
						
						for(int i = 0; i < 10; i++) {
						
							pw.println("Linea: " + i);
						
						}
						
						pw.close();
						
						/*
						//Devuelve caracter a careacter 
						FileReader lector = new FileReader(fichero);
						
						//lleno el buffer de los caracteres y leo lineas
						BufferedReader buffer = new BufferedReader(lector);
						
						String linea;
						
						while((linea = buffer.readLine()) != null) {
							
							System.out.println(linea);
							
						}
						*/
					}catch(IOException e){
						
						e.getMessage();
						
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

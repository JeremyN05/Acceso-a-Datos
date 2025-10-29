package EjerciciosFicheros;

import java.io.File;
import java.util.Scanner;

public class Ejercicio5Ficheros {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		
		String fichero;
		String respuesta;
		String permiso;
		
		System.out.println("Introduzca el nombre del fichero: ");
		
		fichero = entrada.nextLine();
		
		File ficheros = new File(fichero);
		
		if(ficheros.exists()) {
		
			if(ficheros.canRead() && ficheros.canWrite() && ficheros.canExecute()) {
				
				System.out.println("El fichero " + fichero + " tiene estos perimos: ");
				System.out.println("rwx");
				
				System.out.println("Desea modificar algún permiso ( si | no): ");
				
				respuesta = entrada.nextLine();
				
				if(respuesta.equals("si")) {
					
					System.out.println("Introduzca el permiso a cambiar (r -> lectura | w -> escritura | x -> ejecución)");
					
					permiso = entrada.nextLine();
					
					if(permiso.equals("r")) {
						
						ficheros.setReadable(false);
						System.out.println("El permiso de lectura del fichero " + fichero + " se ha modificado");
						System.out.println("Permiso de lectura " + ficheros.canRead());
						System.out.println("Permiso de escritura " + ficheros.canWrite());
						System.out.println("Permiso de ejecución " + ficheros.canExecute());
						
					}else if(permiso.equals("w")) {
						
						ficheros.setWritable(false);
						System.out.println("El permiso de escritura del fichero " + fichero + " se ha modificado");
						System.out.println("Permiso de lectura " + ficheros.canRead());
						System.out.println("Permiso de escritura " + ficheros.canWrite());
						System.out.println("Permiso de ejecución " + ficheros.canExecute());
						
					}else if(permiso.equals("x")) {
						
						ficheros.setExecutable(false);
						System.out.println("El permiso de ejecución del fichero " + fichero + " se ha modificado");
						System.out.println("Permiso de lectura " + ficheros.canRead());
						System.out.println("Permiso de escritura " + ficheros.canWrite());
						System.out.println("Permiso de ejecución " + ficheros.canExecute());
						
					}else {
						
						System.out.println("letra introducida no valida.");
						
					}
					
				}else if(respuesta.equals("no")){
					
					System.out.println("Programa terminado");
					
				}else {
					
					System.out.println("Error, introduzca si o no");
					
				}
				
			}else {
				
				System.out.println("_wx");
				
			}
		
		}else {
			
			System.out.println("El fichero " + fichero + " no existe");
			
		}
		
		entrada.close();

	}

}

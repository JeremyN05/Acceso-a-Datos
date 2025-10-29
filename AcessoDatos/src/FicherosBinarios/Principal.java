package FicherosBinarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Principal {
	
	public static void EscrituraPersonas (ArrayList<Persona> personas) {
		
		String fichero = "personas.dat";
		File ficherosEscritura = new File(fichero);
		
		if(!ficherosEscritura.exists()) {
			
			try {
				
				ficherosEscritura.createNewFile();
				
			}catch(IOException e) {
				
				e.printStackTrace();
				
			}
			
		}else {
			
			if(ficherosEscritura.isFile()) {
				
				try {
					
					//Lo abro para escritura y escribo un flujo de datos
					FileOutputStream fos = new FileOutputStream(ficherosEscritura);
					
					//El tipo de dato que tiene que transformar es un objeto en bytes
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					
					oos.writeObject(personas);
					
					oos.close();
					
				}catch(IOException e) {
					
					e.printStackTrace();
					
				}
				
				
			}else {
				
				System.out.println("Fichero es directorio y no se puede escribir");
				
			}
			
		}
		
	}
	
	public static void LecturaPersonas() {
		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.dat"));
			
			ArrayList<Persona> personas = (ArrayList<Persona>) ois.readObject();
			
			System.out.println("Las personas almacenadas en el fichero son: ");
			
			for(Persona p : personas) {
				
				System.out.println(p);
				
			}
			
		}catch(IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void EscrituraDatos() {
		
		File ficheroDatos = new File("datos.bin");
		
		try {
			
			if(!ficheroDatos.exists()) {
				
				ficheroDatos.createNewFile();
				
			}
			
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(ficheroDatos));
			
			dos.write(2);
			dos.writeDouble(1.2);
			dos.writeBoolean(false);
			dos.writeUTF("Hola como estas");
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static void LecturaDatos() {
		
		try {
			
			DataInputStream dis = new DataInputStream(new FileInputStream("datos.bin"));
			
			int entero = dis.readInt();
			double numero = dis.readDouble();
			boolean booleano = dis.readBoolean();
			String frase = dis.readUTF();
			
			System.out.println("Entero: " + entero + " numero: " + numero + " booleano: " + booleano + " frase: " + frase);
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}


	public static void main(String[] args) {

		ArrayList<Persona> personas = new ArrayList<>();
		
		personas.add(new Persona("Ana", 23));
		personas.add(new Persona("Juan", 19));
		personas.add(new Persona("Bea", 43));
		personas.add(new Persona("Fernando", 20));
		personas.add(new Persona("Luis", 63));

		EscrituraPersonas(personas);
		LecturaPersonas();
		EscrituraDatos();
		LecturaDatos();
	}

}

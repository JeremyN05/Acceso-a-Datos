
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorEmpleados {
	
	static Scanner entrada = new Scanner(System.in);
	
	public static void ComprobarEmpleado(String nombreUsuario, String contraseña) {
		
		ArrayList<Empleados> empleados = leerEmpleados() ;
		
		boolean encontrado = false;
		
		for (Empleados e : empleados) {
	        if (nombreUsuario.equals(e.getNombre()) && contraseña.equalsIgnoreCase(e.getContraseña())) {

	            System.out.println("Hola " + e.getNombre());
	            System.out.println("Iniciando sesión...");

	            if (e.getCargo().equalsIgnoreCase("vendedor")) {
	                Menu.mostrarMenuVendedores(e, entrada);
	            }

	            encontrado = true;
	            break; // 🔹 ya no hace falta seguir buscando
	        }
	    }

	    if (!encontrado) {
	        System.out.println("Error: empleado no encontrado");
	    }
		
	}
	
	public static void EscribirEmpleado(){
		
		ArrayList <Empleados> ListaEmpleados = new ArrayList <>();
		
		File fichero = new File("empleado.dat");
		
		if(!fichero.exists()) {
			
			try ( FileOutputStream FicheroEscritura = new FileOutputStream("empleado.dat");
		             ObjectOutputStream escritura = new ObjectOutputStream(FicheroEscritura)) {

		            	            
		            Empleados empleado1 = new Empleados(1452,"Teresa","asb123","vendedor");
		            Empleados empleado2 = new Empleados(0234,"Miguel Angel","123qwe","vendedor");
		            Empleados empleado3 = new Empleados(7532,"Natalia","xs21qw4","gestor");
		            
		            ListaEmpleados.add(empleado1);
		            ListaEmpleados.add(empleado2);
		            ListaEmpleados.add(empleado3);
		            
		            escritura.writeObject(ListaEmpleados);
		            

		            System.out.println("Objetos escritos correctamente en empleado.dat");

		        } catch (IOException i) {
		            
		        	i.printStackTrace();
		        
		        }
			
		}
		
	}
	
	public static void leerCadaEmpleado() {
		ArrayList<Empleados> listaEmpleados = leerEmpleados();

        // Imprimir los empleados leídos
        if (listaEmpleados != null) {
            for (Empleados empleado : listaEmpleados) {
                System.out.println(empleado);
            }
        }
	}
	
	public static ArrayList<Empleados> leerEmpleados() {
		
		ArrayList<Empleados> empleados = new ArrayList<Empleados>();
		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("empleado.dat"));
			
			empleados = (ArrayList<Empleados>) ois.readObject();
			
			ois.close();
			
			for(Empleados p : empleados) {
				
				System.out.println(p);
				
			}
			
		}catch(IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		return empleados;
		
	}

	
}

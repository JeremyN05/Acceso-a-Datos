
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GestorEmpleados {
	
	static Scanner entrada = new Scanner(System.in);
	
	public static void ComprobarEmpleado(int id, String contraseña) {

	    ArrayList<Empleados> empleados = leerEmpleados(false);
	    boolean encontrado = false;

	    for (Empleados e : empleados) {
	    	
	        if (id == e.getIdentificacion() && contraseña.equalsIgnoreCase(e.getContraseña())) {

	            System.out.println("Iniciando sesión...");
	            System.out.println("Bienvenido/a " + e.getNombre() + " (" + e.getCargo() + ")" + "\n");

	            if (e.getCargo().equalsIgnoreCase("vendedor")) {
	            	
	            	Sesion.setEmpleadoActual(e);
	                Menu.mostrarMenuVendedores(e, entrada);
	                
	            }

	            encontrado = true;
	            break;
	        
	        }   
	        
	    }

	    if (!encontrado) {
	        System.out.println("Error: empleado no encontrado, la contraseña o el usuario es incorrecto");
	    }
	}
	
	private static int IdAleatorio() {
		
		Random random = new Random();
		
		int numero = 1000 + random.nextInt(9000);
		return numero;
		
	}
	
	public static void EscribirEmpleado(){
		
		ArrayList <Empleados> ListaEmpleados = new ArrayList <>();
		
		File fichero = new File("PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "EMPLEADOS" + File.separator + "empleados.dat");
		
		if(!fichero.exists()) {
			
			try ( FileOutputStream FicheroEscritura = new FileOutputStream(fichero);
				     ObjectOutputStream escritura = new ObjectOutputStream(FicheroEscritura)) {

		            	            
		            Empleados empleado1 = new Empleados(1452,"Teresa","asb123","vendedor");
		            Empleados empleado2 = new Empleados(0234,"Miguel Angel","123qwe","vendedor");
		            Empleados empleado3 = new Empleados(7532,"Natalia","xs21qw4","gestor");
		            Empleados empleado4 = new Empleados(IdAleatorio(), "Juan", "Juan1902", "gestor");
		            Empleados empleado5 = new Empleados(IdAleatorio(), "Isabel", "723fgw", "vendedor");
		            Empleados empleado6 = new Empleados(IdAleatorio(), "Jose Luis", "a2us1", "gestor");
		            
		            ListaEmpleados.add(empleado1);
		            ListaEmpleados.add(empleado2);
		            ListaEmpleados.add(empleado3);
		            ListaEmpleados.add(empleado4);
		            ListaEmpleados.add(empleado5);
		            ListaEmpleados.add(empleado6);
		            
		            escritura.writeObject(ListaEmpleados);
		            

		            System.out.println("Objetos escritos correctamente en empleado.dat");

		        } catch (IOException i) {
		            
		        	i.printStackTrace();
		        
		        }
			
		}
		
	}
	
	public static void leerCadaEmpleado() {
		
	ArrayList<Empleados> listaEmpleados = leerEmpleados(true);

    // Imprimir los empleados leídos
    if (listaEmpleados != null) {
        
    	for (Empleados empleado : listaEmpleados) {
            
    		System.out.println(empleado);
        	
        	}
    	
    	}
	
	}
	
	public static ArrayList<Empleados> leerEmpleados(boolean mostrar) {
	    
		ArrayList<Empleados> empleados = new ArrayList<>();
		
		 // Ruta donde se debe crear empleados.dat en caso de que no exista
	    File fichero = new File("PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "EMPLEADOS" + File.separator + "empleados.dat");

	    // Comprobamos si el archivo existe
	    if (!fichero.exists()) {
	        System.out.println("❌ El archivo no existe: " + fichero.getAbsolutePath());
	        return empleados;
	    }

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
	       
			empleados = (ArrayList<Empleados>) ois.readObject();

	        if (mostrar) {
	           
	        	for (Empleados e : empleados) {
	               
	        		System.out.println(e);
	            
	        	}
	       
	        }

	    
		} catch (IOException | ClassNotFoundException e) {
	        
			e.printStackTrace();
	   
		}

	    return empleados;
	}

	
}

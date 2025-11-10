
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
	
	public static void mostrarEmpleadosBaja() {

	    File ficheroBaja = new File("PracticaFinalFicheros_NarváezLobatoJeremy"
	            + File.separator + "EMPLEADOS" + File.separator + "BAJA"
	            + File.separator + "empleadosBaja.dat");

	    if (!ficheroBaja.exists() || ficheroBaja.length() == 0) {
	        System.out.println("No hay empleados en baja.");
	        return;
	    }

	    ArrayList<Empleados> empleadosBaja = new ArrayList<>();
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBaja))) {
	        empleadosBaja = (ArrayList<Empleados>) ois.readObject();
	    } catch (Exception e) {
	        System.out.println("Error al leer empleados en baja.");
	        e.printStackTrace();
	        return;
	    }

	    System.out.println("----- Empleados en BAJA -----");
	    for (Empleados e : empleadosBaja) {
	        System.out.println("ID: " + e.getIdentificacion() +
	                           " | Nombre: " + e.getNombre() +
	                           " | Cargo: " + e.getCargo());
	    }
	    System.out.println("-----------------------------");
	}

	
	private static Empleados comprobarEmpleadoID(int id) {

	    ArrayList<Empleados> empleados = leerEmpleados(false);

	    for (Empleados e : empleados) {
	        
	    	if (e.getIdentificacion() == id) {
	            
	    		return e;
	        
	    	}
	    }

	    return null;
	}
	
	public static void recuperarEmpleadoBaja() {
	    
	    System.out.println("Introduce el ID del empleado a recuperar:");
	    int id = entrada.nextInt();
	    entrada.nextLine();

	   
	    File carpetaBaja = new File("PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "EMPLEADOS" + File.separator + "BAJA");
	    File ficheroBaja = new File(carpetaBaja, "empleadosBaja.dat");
	    File ficheroActivos = new File("PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "EMPLEADOS" + File.separator + "empleados.dat");

	    if (!ficheroBaja.exists() || ficheroBaja.length() == 0) {
	        
	    	System.out.println("No hay empleados dados de baja.");
	        return;
	    
	    }

	    ArrayList<Empleados> empleadosBaja = new ArrayList<>();
	    ArrayList<Empleados> empleadosActivos = new ArrayList<>();

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBaja))) {
	        
	    	empleadosBaja = (ArrayList<Empleados>) ois.readObject();
	    
	    } catch (Exception e) {
	        
	    	System.out.println("Error al leer empleados en baja.");
	        
	        e.printStackTrace();
	        
	        return;
	    }

	    // Leer activos
	    if (ficheroActivos.exists() && ficheroActivos.length() > 0) {
	        
	    	try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroActivos))) {
	            
	    		empleadosActivos = (ArrayList<Empleados>) ois.readObject();
	        
	    	} catch (Exception e) {
	           
	    		System.out.println("Error al leer empleados activos.");
	            e.printStackTrace();
	            
	            return;
	        
	    	}
	    
	    }
	    
	    Empleados empleadoSeleccionado = comprobarEmpleadoID(id);

	    if (empleadoSeleccionado == null) {
	        
	    	System.out.println("No se encontró ningún empleado con ese ID en la lista de bajas.");
	        return;
	    
	    }

	    empleadosBaja.remove(empleadoSeleccionado);
	    empleadosActivos.add(empleadoSeleccionado);


	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroBaja))) {
	        
	    	oos.writeObject(empleadosBaja);
	    
	    } catch (IOException e) {
	        
	    	System.out.println("Error al actualizar lista de bajas.");
	        e.printStackTrace();
	    
	    }

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroActivos))) {
	        
	    	oos.writeObject(empleadosActivos);
	        
	    	System.out.println("Empleado recontratado correctamente.");
	    
	    } catch (IOException e) {
	        
	    	System.out.println("Error al actualizar empleados activos.");
	        e.printStackTrace();
	    
	    }

	    System.out.println("\n--- Empleados activos actuales ---");
	    
	    for (Empleados e : empleadosActivos) {

	    	System.out.printf("%-10d %-15s %-10s %-10s\n", e.getIdentificacion(), e.getNombre(), e.getContraseña(), e.getCargo());
	    
	    }
	
	}

	
	public static void darBajaEmpleado() {
    
	    System.out.println("Introduce el ID del empleado a dar de baja:");
	    int id = entrada.nextInt();
	    entrada.nextLine();

	    
	    Empleados empleadoSeleccionado = comprobarEmpleadoID(id);

	    if (empleadoSeleccionado == null) {
	        
	    	System.out.println("Error: empleado no encontrado.");
	        
	    	return;
	    
	    }

	    File carpetaBaja = new File("PracticaFinalFicheros_NarváezLobatoJeremy" + File.separator + "EMPLEADOS" + File.separator + "BAJA");
	    
	    if (!carpetaBaja.exists()) {
	        
	    	carpetaBaja.mkdirs();
	    
	    }

	    File ficheroBaja = new File(carpetaBaja, "empleadosBaja.dat");

	    ArrayList<Empleados> empleadosBaja = new ArrayList<>();
	    
	    if (ficheroBaja.exists() && ficheroBaja.length() > 0) {
	        
	    	try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroBaja))) {
	            
	    		empleadosBaja = (ArrayList<Empleados>) ois.readObject();
	        
	    	} catch (Exception e) {
	            
	    		System.out.println("Error al leer empleados en baja.");
	        
	    	}
	    }


	    empleadosBaja.add(empleadoSeleccionado);

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroBaja))) {
	       
	    	oos.writeObject(empleadosBaja);
	        
	    	System.out.println("Empleado dado de baja correctamente.");

	    } catch (IOException e) {
	        
	    	System.out.println("Error al guardar empleados en baja.");
	        
	        e.printStackTrace();
	    
	    }

	    ArrayList<Empleados> empleadosActivos = leerEmpleados(false);
	    
	    if (empleadosActivos.removeIf(e -> e.getIdentificacion() == id)) {
	        
	    	File ficheroActivos = new File("PracticaFinalFicheros_NarváezLobatoJeremy"+ File.separator + "EMPLEADOS" + File.separator + "empleados.dat");

	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroActivos))) {
	            
	        	oos.writeObject(empleadosActivos);
	            
	        } catch (IOException e) {
	        	
	            System.out.println("Error al actualizar empleados activos.");
	            
	            e.printStackTrace();
	            
	        }
	        
	    }

	    mostrarEmpleadosBaja();
	    
	}
	
	public static void ComprobarEmpleado(int id, String contraseña) {

	    ArrayList<Empleados> empleados = leerEmpleados(false);
	    boolean encontrado = false;

	    for (Empleados e : empleados) {
	    	
	        if (id == e.getIdentificacion() && contraseña.equals(e.getContraseña())) {

	            System.out.println("Iniciando sesión...");
	            System.out.println("Bienvenido/a " + e.getNombre() + " (" + e.getCargo() + ")" + "\n");

	            if (e.getCargo().equalsIgnoreCase("vendedor")) {
	            	
	            	Sesion.setEmpleadoActual(e);
	                Menu.mostrarMenuVendedores(e, entrada);
	                
	            }else if(e.getCargo().equalsIgnoreCase("gestor")){
	            	
	            	Sesion.setEmpleadoActual(e);
	            	Menu.mostrarMenuGestores(e, entrada);
	            	
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
		            Empleados empleado4 = new Empleados(IdAleatorio(), "Juan", "Juan190", "gestor");
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

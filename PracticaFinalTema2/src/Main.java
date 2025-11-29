import java.sql.Connection;
import java.util.Scanner;

public class Main {
	
	private static Scanner entrada = new Scanner(System.in); 

	public static void main(String[] args) {
		
		 try {
		        Connection conexion = ConexionBBDD.obtenerConexion();
		        Datos_Iniciales.insertarJuguetesYEmpleados(conexion);

		        int opcion = 0;

		        do {
		        	
		        	System.out.println("--------------------------------------------------------");
		            System.out.println("---------------BIENVENIDO A LA JUGUETERIA---------------");
		            System.out.println("--------------------------------------------------------");

		            System.out.println("1. Menú juguetes");
		            System.out.println("2. Menú empleado");
		            System.out.println("3. Menú ventas");
		            System.out.println("4. Menú datos tienda");
		            System.out.println("5. Salir\n");

		            System.out.println("Indique el menú al que quiera acceder");
		            opcion = entrada.nextInt();
		            entrada.nextLine();

		        } while (opcion < 1 || opcion > 5);

		        switch (opcion) {

		            case 1:
		                MenuJuguete.mostrarMenuJuguetes(conexion, entrada);
		                break;

		            case 2:
		                MenuEmpleado.mostrarMenuEmpleado(conexion, entrada);
		                break;

		            case 3:
		                MenuVentas.mostrarMenuVentas(conexion);
		                break;
		                
		            case 4:
		                MenuDatosTienda.menuDatosTienda(conexion, entrada);
		                break;
		                
		            case 5:
		                System.out.println("Saliendo del programa...");
		                System.exit(0);
		                break;

		            default:
		                break;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

}

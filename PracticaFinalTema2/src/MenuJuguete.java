import java.sql.Connection;
import java.util.Scanner;

public class MenuJuguete {
	
	public static void mostrarMenuJuguetes(Connection conexion, Scanner entrada) {
		
		int opcionJuguetes = 0;
		
		System.out.println("                    MENÚ JUGUETES                    ");
		System.out.println("-----------------------------------------------------");
		
		do {
			
			System.out.println("1. Registrar un nuevo juguete.");
			System.out.println("2. Modificar los datos de los juguetes.");
			System.out.println("3. Eliminar juguetes");
			System.out.println("4. Salir.\n");
			
			System.out.println("Indique que desea hacer: ");
			opcionJuguetes = entrada.nextInt();
			entrada.nextLine();
			
		}while(opcionJuguetes < 1 || opcionJuguetes > 4);
		
		switch (opcionJuguetes) {
		
		case 1:
			
			GestorJuguetes.crearJuguete(conexion, entrada);
			break;
			
		case 2:
			
			GestorJuguetes.modificarJuguetes(conexion, entrada);
			break;
			
		case 3:
			
			GestorJuguetes.eliminarJuguete(conexion, entrada);
			break;
			
		case 4:
			
			System.out.println("Saliendo del programa....");
			break;

		default:
			break;
		}
		
	}
	
}

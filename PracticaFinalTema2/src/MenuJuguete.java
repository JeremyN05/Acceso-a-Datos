import java.sql.Connection;
import java.util.Scanner;

public class MenuJuguete {

	private static Scanner entrada = new Scanner(System.in);
	
	public static void mostrarMenuJuguetes(Connection conexion) {
		
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
	}
	
}

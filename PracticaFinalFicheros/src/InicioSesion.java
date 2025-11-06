import java.util.Scanner;

public class InicioSesion {

	public static Scanner entrada = new Scanner(System.in);
	static final String ROJO = "\u001B[31m";
	static final String RESET = "\u001B[0m";
	
	public static void IniciarSesion(Scanner entrada) {
		
		int id;
		String contraseña;
		String expRegPass = "^[A-Za-z0-9]{5,7}$"; 
		boolean valido = false;
		
		System.out.println("Ingrese el id de usuario: ");
		
		while (!entrada.hasNextInt()) {
            
			System.out.println(ROJO + "Error: el ID debe ser numérico." + RESET);
            entrada.nextLine();
            
            System.out.print("Vuelve a introducir el ID: ");
        }
		
		id = entrada.nextInt();
		entrada.nextLine();
		
		do {
		
			System.out.println("Ingrese la contraseña del usuario debe tener entre 5-7 caracteres: ");
			contraseña = entrada.nextLine();
		
			if(contraseña.matches(expRegPass)) {
				
				valido = true;
				
			}else {
				
                System.out.println(ROJO + "❌ Contraseña inválida. Debe tener entre 5 y 7 caracteres y solo letras o números." + RESET);
            
			}
		
		}while(!valido);
		
		GestorEmpleados.ComprobarEmpleado(id, contraseña);
		
	}
	
}

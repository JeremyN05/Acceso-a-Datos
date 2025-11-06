
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	static Scanner entrada = new Scanner(System.in);
	static final String VERDE = "\u001B[32m";
	static final String AZUL = "\u001B[34m";
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";
	static final String VERDE_CLARITO = "\u001B[38;2;144;238;144m";
	
	private static void crearArbolFicheros() {
		
		 // Carpeta base = directorio actual del proyecto en Eclipse
        String baseDir = System.getProperty("user.dir") + File.separator + "PracticaFinalFicheros_NarváezLobatoJeremy";

        // Rutas de carpetas
        String[] carpetas = {
            baseDir + File.separator + "PLANTAS",
            baseDir + File.separator + "EMPLEADOS",
            baseDir + File.separator + "EMPLEADOS" + File.separator + "BAJA",
            baseDir + File.separator + "TICKETS",
            baseDir + File.separator + "DEVOLUCIONES"
        };

        // Archivos a crear
        String[] archivos = {
            baseDir + File.separator + "PLANTAS" + File.separator + "plantas.xml",
            baseDir + File.separator + "PLANTAS" + File.separator + "plantasBaja.xml",
            baseDir + File.separator + "PLANTAS" + File.separator + "plantas.dat",
            baseDir + File.separator + "PLANTAS" + File.separator + "plantasbaja.dat",
            baseDir + File.separator + "EMPLEADOS" + File.separator + "empleados.dat",
            baseDir + File.separator + "EMPLEADOS" + File.separator + "BAJA" + File.separator + "empleadosBaja.dat",
            baseDir + File.separator + "TICKETS" + File.separator + "0.txt",
            baseDir + File.separator + "DEVOLUCIONES" + File.separator + "0.txt"
        };

        File base = new File(baseDir);
        if (base.exists()) {
            System.out.println("La carpeta base ya existe: " + baseDir);
        } else {
            System.out.println("Creando carpeta base...");
            base.mkdirs();
        }
        
        for (String carpeta : carpetas) {
            File dir = new File(carpeta);
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("📁 Carpeta creada: " + dir.getPath());
            }
        }
        
        for (String rutaArchivo : archivos) {
            File archivo = new File(rutaArchivo);
            try {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo creado: " + archivo.getPath());
                }
            } catch (IOException e) {
                System.err.println("Error al crear: " + archivo.getPath());
                e.printStackTrace();
            }
        }
        
        System.out.println("\n Estructura verificada y actualizada correctamente en: " + baseDir);
		
	}
	
	public static void main(String[] args) {
		
		crearArbolFicheros();
		GestorEmpleados.EscribirEmpleado();
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.println(VERDE + "------------------------------------------------------------------------------PLANTAS------------------------------------------------------------------------------");
		System.out.printf("%-15s %-15s %-15s %-65s %-10s %-15s \n", "ID", "Nombre", "Foto", "Descripcion", "Precio", "Stock");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
		
		GestorPlantas.mostrarPlantas();
		System.out.println("\n");
		
		System.out.println(CYAN + "---------------------------------------EMPLEADOS---------------------------");
		System.out.printf("%-15s %-15s %-15s %-15s \n", "ID", "Nombre", "contraseña", "cargo");
		System.out.println("---------------------------------------------------------------------------" + RESET);
		GestorEmpleados.leerEmpleados(true);
		
		System.out.println("\n");
		
		System.out.println(VERDE_CLARITO + "----------------Inicio Sesión----------------" + RESET);
		InicioSesion.IniciarSesion(entrada);
		

	}

}

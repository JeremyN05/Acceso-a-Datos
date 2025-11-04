
public class Sesion {

	private static Empleados empleadoActual;

	public static void setEmpleadoActual(Empleados empleado) {
	        
		empleadoActual = empleado;
	   
	}

	public static Empleados getEmpleadoActual() {
	        
		return empleadoActual;
	    
	}
 
	public static void cerrarSesion() {
	       
		empleadoActual = null;
	        
		System.out.println("Sesión cerrada correctamente.");
	   
	}
}

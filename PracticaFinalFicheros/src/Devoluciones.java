import java.time.LocalDateTime;

public class Devoluciones {

	private int idDevolucion; 
    private int numeroTicket; 
    private int idProducto;    
    private int cantidad;      
    private LocalDateTime fechaDevolucion;
    
	public Devoluciones(int idDevolucion, int numeroTicket, int idProducto, int cantidad,
			LocalDateTime fechaDevolucion) {
		super();
		this.idDevolucion = idDevolucion;
		this.numeroTicket = numeroTicket;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.fechaDevolucion = fechaDevolucion;
	}

	public int getIdDevolucion() {
		return idDevolucion;
	}

	public void setIdDevolucion(int idDevolucion) {
		this.idDevolucion = idDevolucion;
	}

	public int getNumeroTicket() {
		return numeroTicket;
	}

	public void setNumeroTicket(int numeroTicket) {
		this.numeroTicket = numeroTicket;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
	public String toString() {
		return "Devoluciones [idDevolucion=" + idDevolucion + ", numeroTicket=" + numeroTicket + ", idProducto="
				+ idProducto + ", cantidad=" + cantidad + ", fechaDevolucion=" + fechaDevolucion + "]";
	}
	
}

import java.time.LocalDateTime;

public class Ticket {

	 private int codigoEmpleado;
	 private String nombreEmpleado;
	 private LocalDateTime fechaVenta;
	 private int codigoProducto;
	 private int unidades;
	 private float precioUnitario;
	 private float total;
	 
	public Ticket(int codigoEmpleado, String nombreEmpleado, LocalDateTime fechaVenta, int codigoProducto, int unidades,
			float precioUnitario, float total) {
		super();
		this.codigoEmpleado = codigoEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.fechaVenta = fechaVenta;
		this.codigoProducto = codigoProducto;
		this.unidades = unidades;
		this.precioUnitario = precioUnitario;
		this.total = total;
	}

	public int getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(int codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Ticket [codigoEmpleado=" + codigoEmpleado + ", nombreEmpleado=" + nombreEmpleado + ", fechaVenta="
				+ fechaVenta + ", codigoProducto=" + codigoProducto + ", unidades=" + unidades + ", precioUnitario="
				+ precioUnitario + ", total=" + total + "]";
	}
	
}

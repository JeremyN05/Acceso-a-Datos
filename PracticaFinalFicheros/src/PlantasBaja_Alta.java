
public class PlantasBaja_Alta {

	private int codigo;
	private String nombre;
	private float precio;
	private int stock;
	
	public PlantasBaja_Alta(int codigo, String nombre, float precio, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return String.format( "%-15s %-15s %15.2f %8d", codigo, nombre, precio, stock);
	}
	
}

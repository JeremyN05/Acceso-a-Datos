
import java.io.Serializable;

public class Plantas implements Serializable {

	private int codigo;
	private String nombre;
	private String foto;
	private String descripcion;
	private float precio;
	private int stock;

	public Plantas(int codigo, String nombre, String foto, String descripcion, float precio, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.foto = foto;
		this.descripcion = descripcion;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		return String.format( "%-15s %-15s %-15s %-55s %15.2f %8d", codigo, nombre, foto, descripcion, precio, stock);
	}
	
}

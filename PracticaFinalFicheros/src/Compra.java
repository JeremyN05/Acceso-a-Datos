
public class Compra {

	int id;
	int cantidad;
	
	public Compra(int id, int cantidad) {
		super();
		this.id = id;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", cantidad=" + cantidad + "]";
	}
	
}

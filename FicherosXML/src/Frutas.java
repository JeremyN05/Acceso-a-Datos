import java.util.Arrays;

public class Frutas {

	String nombre;
	String tipo;
	String color;
	String origen;
	String precio;
	String temporada;
	String[] nutriente;
	
	public Frutas(String nombre, String tipo, String color, String origen, String precio, String temporada,
			String[] nutriente) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.color = color;
		this.origen = origen;
		this.precio = precio;
		this.temporada = temporada;
		this.nutriente = nutriente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public String[] getNutriente() {
		return nutriente;
	}

	public void setNutriente(String[] nutriente) {
		this.nutriente = nutriente;
	}

	@Override
	public String toString() {
		return "Frutas [nombre=" + nombre + ", tipo=" + tipo + ", color=" + color + ", origen=" + origen + ", precio="
				+ precio + ", temporada=" + temporada + ", nutriente=" + Arrays.toString(nutriente) + "]";
	}
	
}

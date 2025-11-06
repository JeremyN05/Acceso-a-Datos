import java.util.ArrayList;
import java.util.List;

public class ListaTicket {
	
	private List<Ticket> lineas;
    private float totalGeneral; 
	
    public ListaTicket() {
        this.lineas = new ArrayList<>();
        this.totalGeneral = 0;
    }
    
    public void agregarLinea(Ticket linea) {
        lineas.add(linea);
        totalGeneral += linea.getTotal();
    }

    public List<Ticket> getLineas() {
        return lineas;
    }

    public float getTotalGeneral() {
        return totalGeneral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=============== TICKET ===============\n");
        for (Ticket t : lineas) {
            sb.append(t.toString()).append("\n--------------------------------------\n");
        }
        sb.append(String.format("TOTAL FINAL: %.2f €\n", totalGeneral));
        sb.append("======================================");
        return sb.toString();
    }

}

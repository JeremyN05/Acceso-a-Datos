import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Estadisticas {

    public static void mostrarEstadisticas() {
        String rutaTickets = "PracticaFinalFicheros_NarváezLobatoJeremy/TICKETS";
        File carpetaTickets = new File(rutaTickets);

        if (!carpetaTickets.exists() || carpetaTickets.listFiles() == null) {
            System.out.println("No hay tickets registrados.");
            return;
        }

        double totalRecaudado = 0;
        Map<Integer, Integer> ventasPorPlanta = new HashMap<>();

        File[] archivos = carpetaTickets.listFiles();
        for (File ticketFile : archivos) {
            if (!ticketFile.isFile() || !ticketFile.getName().endsWith(".txt")) {
                continue;
            }

            try {
                int idTicket = Integer.parseInt(ticketFile.getName().replace(".txt", ""));
                ArrayList<Ticket> lineas = GestorDevoluciones.obtenerLineas(idTicket);

                for (Ticket t : lineas) {
                    totalRecaudado += t.getTotal();
                    ventasPorPlanta.put(t.getCodigoProducto(),
                            ventasPorPlanta.getOrDefault(t.getCodigoProducto(), 0) + t.getUnidades());
                }

            } catch (NumberFormatException e) {
                System.out.println("Archivo ignorado: " + ticketFile.getName());
            }
        }

        System.out.println("====================================");
        System.out.printf("TOTAL RECAUDADO: %.2f €\n", totalRecaudado);

        if (ventasPorPlanta.isEmpty()) {
            System.out.println("No se registraron ventas.");
        } else {
            // Buscar planta más vendida
            int maxUnidades = 0;
            int codigoPlantaMasVendida = 0;

            for (Map.Entry<Integer, Integer> entry : ventasPorPlanta.entrySet()) {
                if (entry.getValue() > maxUnidades) {
                    maxUnidades = entry.getValue();
                    codigoPlantaMasVendida = entry.getKey();
                }
            }

            Plantas planta = GestorPlantas.buscaPlantaID(codigoPlantaMasVendida);
            if (planta != null) {
                System.out.println("PLANTA MÁS VENDIDA: " + planta.getNombre() + " (" + maxUnidades + " unidades)");
            } else {
                System.out.println("PLANTA MÁS VENDIDA: Código " + codigoPlantaMasVendida + " (" + maxUnidades + " unidades)");
            }
        }

        System.out.println("====================================");
    }
}


package micromantenimiento.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monopatin {
    private int id_monopatin;
    private String ubicacionActual;
    private Boolean estaActivo;
    private Boolean habilitado;
    private Double kilometrosTotales;
    private int tiempo_uso_total;
}

package micromonopatin.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MonopatinDTO {
    private int id_monopatin;
    private Boolean estaActivo;
    private Boolean habilitado;
    private Double kilometrosTotales;
    private int tiempo_uso_total;
    private int ubicacionX;
    private int ubicacionY;
}

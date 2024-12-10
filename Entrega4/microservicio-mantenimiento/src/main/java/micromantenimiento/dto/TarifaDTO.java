package micromantenimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TarifaDTO {
    private int id;
    private double precioTarifa;
    private double precioTarifaExtra;
    private LocalDate fecha;
}
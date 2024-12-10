package micromantenimiento.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Omite los campos con valor null
public class ReporteDTO {
    private int idMonopatin;
    private double kmRecorridos;
    private long tiempoPausa;
    private long tiempoSinPausa;
    public ReporteDTO() {
        this.kmRecorridos = -1;
        this.tiempoPausa = -1;
        this.tiempoSinPausa = -1;
    }
    public Double getKmRecorridos() {
        if(kmRecorridos == -1)
            return null;
        return kmRecorridos;
    }
    public Long getTiempoPausa() {
        if(tiempoPausa == -1)
            return null;
        return tiempoPausa;
    }
    public Long getTiempoSinPausa() {
        if (tiempoSinPausa == -1)
            return null;
        return tiempoSinPausa;
    }
}

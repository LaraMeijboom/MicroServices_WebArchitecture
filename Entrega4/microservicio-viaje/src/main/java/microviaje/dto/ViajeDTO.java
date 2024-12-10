package microviaje.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {
    private long idViaje;
    private long idUsuario;
    private long idMonopatin;
    private long idParada;
    private Date fechaInicio;
    private Date fechaFin;
    private float kmRecorridos;
    private float costo;

    public ViajeDTO(int idMonopatin) {
        this.idMonopatin = idMonopatin;
    }

}

package micromantenimiento.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viaje {
    
    private int idViaje;
    private int idUsuario;
    private int idMonopatin;
    private int idParada;
    private Date fechaInicio;
    private Date fechaFin;
    private float kmRecorridos;
    private float costo;

}

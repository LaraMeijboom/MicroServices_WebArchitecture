package micromantenimiento.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {

    @Id
    private long idReporte;
    private long idMonopatin;
    private double kmRecorridos;
    private int tiempoPausa;
    private int tiempoSinPausa;

}

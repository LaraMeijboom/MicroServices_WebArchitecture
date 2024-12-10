package micromonopatin.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class  Monopatin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_monopatin;
    @NonNull
    private Boolean estaActivo;
    @NonNull
    private Boolean habilitado;
    @NonNull
    private Double kilometrosTotales;
    private int tiempo_uso_total;
    private int ubicacionX;
    private int ubicacionY;
}

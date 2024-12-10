package micromonopatin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Parada{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_parada;
    private Boolean permitida;
    private int ubicacionX;
    private int ubicacionY;
}
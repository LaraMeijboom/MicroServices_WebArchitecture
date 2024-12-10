package micromonopatin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParadaDTO{
    private int id_parada;
    private Boolean permitida;
    private int ubicacionX;
    private int ubicacionY;
}

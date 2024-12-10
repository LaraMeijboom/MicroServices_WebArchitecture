package microviaje.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PausaDTO {
    private long idPausa;
    private long idViaje;
    private long tiempo;

}



package micromantenimiento.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class MantenimientoDTO {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private int idMonopatin;
    @Getter @Setter
    private Date fechaInicio;
    @Getter @Setter
    private Date fechaFin;
    public MantenimientoDTO() {}
    public MantenimientoDTO(int id,int idMonopatin, Date fechaInicio, Date fechaFin) {
        this.idMonopatin = idMonopatin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.id = id;
    }
}

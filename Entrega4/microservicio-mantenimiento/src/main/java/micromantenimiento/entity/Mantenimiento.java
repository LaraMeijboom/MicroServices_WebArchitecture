package micromantenimiento.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idMonopatin;
    @NonNull
    private Date fechaInicio;
    private Date fechaFin;

    public Mantenimiento(int idMonopatin, Date fechaInicio) {
        this.idMonopatin = idMonopatin;
        this.fechaInicio = fechaInicio;
        this.fechaFin=null;
    }
}

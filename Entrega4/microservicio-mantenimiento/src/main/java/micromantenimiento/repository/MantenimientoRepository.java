package micromantenimiento.repository;
import micromantenimiento.dto.MantenimientoDTO;
import micromantenimiento.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer> {

    @Modifying
    @Query("UPDATE Mantenimiento m SET m.idMonopatin = :idMonopatin, m.fechaInicio = :fechaInicio, m.fechaFin = :fechaFin WHERE m.id = :id")
    public void updateMantenimiento(@Param("idMonopatin") int idMonopatin, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("id") int id);

    @Query("SELECT new micromantenimiento.dto.MantenimientoDTO(m.id,m.idMonopatin,m.fechaInicio,m.fechaFin) FROM Mantenimiento m WHERE m.id = :id")
    public MantenimientoDTO getMantenimientoById(@Param("id") int id);

    @Query("SELECT new micromantenimiento.dto.MantenimientoDTO(m.id,m.idMonopatin,m.fechaInicio,m.fechaFin) FROM Mantenimiento m")
    List<MantenimientoDTO> getMantenimientos();

    @Modifying
    @Query("UPDATE Mantenimiento m SET m.fechaFin = :fechaFin WHERE m.id = :id")
    public void finalizarMantenimiento(@Param("fechaFin") Date fechaFin, @Param("id") int id);
}
package micromantenimiento.repository;

import micromantenimiento.dto.TarifaDTO;
import micromantenimiento.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {

    @Modifying
    @Query("DELETE FROM Tarifa a WHERE a.id = :id")
    void deleteTarifa(@Param("id") int id);

    @Modifying
    @Query("UPDATE Tarifa a SET a.precioTarifa = :precioTarifa")
    public void updatePrecioTarifa(@Param("precioTarifa") double precioTarifa);

    @Modifying
    @Query("UPDATE Tarifa a SET a.precioTarifaExtra = :precioTarifaExtra")
    public void updatePrecioTarifaExtra(@Param("precioTarifaExtra") double precioTarifaExtra);

    @Query("SELECT new micromantenimiento.dto.TarifaDTO(t.id, t.precioTarifa, t.precioTarifaExtra, t.fecha) FROM Tarifa t WHERE t.id = :id")
    public TarifaDTO getTarifaById(@Param("id") int id);

    @Query("SELECT new micromantenimiento.dto.TarifaDTO(t.id,t.precioTarifa,t.precioTarifaExtra, t.fecha) FROM Tarifa t")
    List<TarifaDTO> getTarifas();

    @Query("SELECT new micromantenimiento.dto.TarifaDTO(t.id,t.precioTarifa,t.precioTarifaExtra, t.fecha) FROM Tarifa t WHERE t.fecha < :fechaActual ORDER BY t.fecha DESC LIMIT 1")
    TarifaDTO getTarifaActual(@Param("fechaActual") LocalDate fechaActual);
}

package micromonopatin.repository;

import micromonopatin.dto.MonopatinDTO;
import micromonopatin.dto.ParadaDTO;
import micromonopatin.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface monopatinRepository  extends JpaRepository<Monopatin,Integer> {

    @Query("SELECT new micromonopatin.dto.MonopatinDTO(m.id_monopatin, m.estaActivo, m.habilitado, m.kilometrosTotales, m.tiempo_uso_total, m.ubicacionX, m.ubicacionY) FROM Monopatin m WHERE m.id_monopatin = :id")
    MonopatinDTO getMonopatinById(@Param("id") int id);

    @Query("SELECT new micromonopatin.dto.MonopatinDTO(m.id_monopatin, m.estaActivo, m.habilitado, m.kilometrosTotales, m.tiempo_uso_total, m.ubicacionX, m.ubicacionY) FROM Monopatin m WHERE m.id_monopatin = :id_monopatin")
    MonopatinDTO getMonopatin(@Param("id_monopatin") int id_monopatin);

    @Modifying
    @Query("UPDATE Monopatin m SET m.ubicacionX = :ubicacionX, m.ubicacionY = :ubicacionY, m.estaActivo = :estaActivado, m.habilitado = :habilitado, m.kilometrosTotales = :kilometrosTotales, m.tiempo_uso_total = :tiempo_uso_total WHERE m.id_monopatin = :id_monopatin")
    void updateMonopatin(@Param("ubicacionX") int ubicacionX,@Param("ubicacionY") int ubicacionY, @Param("estaActivado") boolean estaActivado, @Param("habilitado") boolean habilitado, @Param("kilometrosTotales") double kilometrosTotales, @Param("tiempo_uso_total") int tiempo_uso_total, @Param("id_monopatin") int id_monopatin);

    @Modifying
    @Query("DELETE FROM Monopatin m WHERE m.id_monopatin = :id_monopatin")
    void deleteMonopatin(@Param("id_monopatin") int id_monopatin);

    @Query("SELECT new micromonopatin.dto.MonopatinDTO(m.id_monopatin, m.estaActivo, m.habilitado, m.kilometrosTotales, m.tiempo_uso_total, m.ubicacionX, m.ubicacionY) FROM Monopatin m")
    List<MonopatinDTO> getMonopatines();

    @Modifying
    @Query("UPDATE Monopatin m SET m.habilitado = :habilitado WHERE m.id_monopatin = :id_monopatin")
    void updateHabilitadoMonopatin(@Param("id_monopatin") int id,@Param("habilitado") boolean valor);

    @Query("SELECT new micromonopatin.dto.MonopatinDTO(m.id_monopatin, m.estaActivo, m.habilitado, m.kilometrosTotales, m.tiempo_uso_total, m.ubicacionX, m.ubicacionY) FROM Monopatin m order by m.kilometrosTotales")
    List<MonopatinDTO> getReporteMonopatinesPorKilometro();

    @Query("SELECT " +
            "SUM(CASE WHEN m.habilitado = true THEN 1 ELSE 0 END) AS enOperacion, " +
            "SUM(CASE WHEN m.habilitado = false THEN 1 ELSE 0 END) AS enMantenimiento " +
            "FROM Monopatin m")
    List<Object[]> getMonopatinesEnOperacion();

    @Query("SELECT new micromonopatin.dto.MonopatinDTO(m.id_monopatin, m.estaActivo, m.habilitado, m.kilometrosTotales, m.tiempo_uso_total, m.ubicacionX, m.ubicacionY) " +
            "FROM Monopatin m " +
            "WHERE SQRT(POWER(m.ubicacionX - :posX, 2) + POWER(m.ubicacionY - :posY, 2)) <= :radio " +
            "AND m.estaActivo = false AND m.habilitado = true")
    List<MonopatinDTO> getMonopatinesCercanos(int posX, int posY, int radio);
}
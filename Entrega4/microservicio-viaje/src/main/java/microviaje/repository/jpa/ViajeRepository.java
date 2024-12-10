package microviaje.repository.jpa;

import microviaje.dto.ViajeDTO;
import microviaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT new microviaje.dto.ViajeDTO(v.idViaje,v.idUsuario, v.idMonopatin, v.idParada, v.fechaInicio, v.fechaFin, v.kmRecorridos, v.costo) FROM Viaje v WHERE v.idViaje = :idViaje")
    ViajeDTO getViajeById(@Param("idViaje") Long idViaje);

    @Query("SELECT new microviaje.dto.ViajeDTO(v.idViaje,v.idUsuario, v.idMonopatin, v.idParada, v.fechaInicio, v.fechaFin, v.kmRecorridos, v.costo) FROM Viaje v")
    List<ViajeDTO> getViajes();

    @Query("SELECT sum(p.tiempo) FROM Viaje v JOIN Pausa p on (v.idViaje = p.idViaje) WHERE v.idMonopatin = :id")
    Long getTiempoPausaMonopatin(@Param("id") int id);

    @Query("SELECT new microviaje.dto.ViajeDTO(v.idViaje, v.idUsuario, v.idMonopatin, v.idParada, v.fechaInicio, v.fechaFin, v.kmRecorridos, v.costo) " +
            "FROM Viaje v " +
            "WHERE FUNCTION('YEAR', v.fechaInicio) = :anio " +
            "GROUP BY v.idMonopatin " +
            "HAVING COUNT(v.idViaje) > :cant")
    List<ViajeDTO> getMonopatinesMasViajesPorAnio(@Param("cant") int cant,@Param("anio") int anio);

    @Query("SELECT SUM(v.costo) " +
            "FROM Viaje v " +
            "WHERE FUNCTION('YEAR', v.fechaInicio) = :anio " +
            "AND FUNCTION('MONTH', v.fechaInicio) BETWEEN :mes1 AND :mes2")
    Integer getFacturadoEntre(@Param("anio")int anio,@Param("mes1") int mes1,@Param("mes2") int mes2);

    @Modifying
    @Query("UPDATE Viaje v SET v.costo = :costo, v.fechaInicio = :fechaInicio, v.fechaFin = :fechaFin, v.idMonopatin = :idMonopatin, v.idParada = :idParada, v.kmRecorridos = :kmRecorridos, v.idUsuario = :idUsuario WHERE v.idViaje = :idViaje")
    void updateViaje(@Param("costo") float costo,@Param("fechaInicio") Date fechaInicio,@Param("fechaFin") Date fechaFin,@Param("idMonopatin") int idMonopatin,@Param("idParada") int idParada,@Param("kmRecorridos") float kmRecorridos, @Param("idUsuario") int idUsuario,@Param("idViaje") long idViaje);
}

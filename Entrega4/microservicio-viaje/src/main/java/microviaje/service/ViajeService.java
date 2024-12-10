package microviaje.service;

import jakarta.transaction.Transactional;
import microviaje.dto.ViajeDTO;
import microviaje.entity.Viaje;
import org.entrega4.microservicioviaje.junit.ViajeVacioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microviaje.repository.jpa.ViajeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    public Viaje saveViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    public ViajeDTO getViajeById(Long idViaje) {
        return viajeRepository.getViajeById(idViaje);
    }

    public List<ViajeDTO> getAllViajes() {
        return viajeRepository.getViajes();
    }

    public List<ViajeDTO> getAllViajesTest() throws ViajeVacioException {

        List<ViajeDTO> lista = new ArrayList<>();

        if (lista.isEmpty()) throw new ViajeVacioException("NO EXISTEN VIAJES");
        return null;
    }

    public void deleteViaje(Long idViaje) {
        viajeRepository.deleteById(idViaje);
    }

    public Long getTiempoPausaMonopatin(int id) {
        return viajeRepository.getTiempoPausaMonopatin(id);
    }

    public List<ViajeDTO> getMonopatinesMasViajesPorAnio(int cant, int anio) {
        return viajeRepository.getMonopatinesMasViajesPorAnio(cant, anio);
    }


    public Integer getFacturadoEntre(int anio, int mes1, int mes2) {
        return viajeRepository.getFacturadoEntre(anio, mes1, mes2);
    }

    public void updateViaje(Viaje viaje, long idViaje) {
        viajeRepository.updateViaje(viaje.getCosto(), viaje.getFechaInicio(), viaje.getFechaFin(), viaje.getIdMonopatin(), viaje.getIdParada(), viaje.getKmRecorridos(), viaje.getIdUsuario(),idViaje);
    }
}

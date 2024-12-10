package micromonopatin.service;

import jakarta.transaction.Transactional;
import micromonopatin.dto.MonopatinDTO;
import micromonopatin.dto.ParadaDTO;
import micromonopatin.entity.Monopatin;
import micromonopatin.repository.monopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class monopatinService{

    @Autowired
    private monopatinRepository mr;



    public MonopatinDTO getMonopatin(int id_monopatin) {
        return mr.getMonopatin(id_monopatin);
    }

    public MonopatinDTO getMonopatinById(int idMonopatin){
        return mr.getMonopatinById(idMonopatin);
    }

    public Monopatin addMonopatin(Monopatin monopatin){
        return mr.save(monopatin);
    }

    public void updateMonopatin(Monopatin m){
        mr.updateMonopatin(m.getUbicacionX(), m.getUbicacionY(),  m.getEstaActivo(), m.getHabilitado(), m.getKilometrosTotales(), m.getTiempo_uso_total(), m.getId_monopatin());
    }

    public void deleteMonopatin(int id_monopatin) {
        mr.deleteMonopatin(id_monopatin);
    }

    public List<MonopatinDTO> getMonopatines() {
        return mr.getMonopatines();
    }

    public void updateHabilitadoMonopatin(int id, boolean valor) {
        mr.updateHabilitadoMonopatin(id, valor);
    }

    public List<MonopatinDTO> getReporteMonopatinesPorKilometro() { return mr.getReporteMonopatinesPorKilometro();}

    public Map<String, Long> getMonopatinesEnOperacion() {
        Object[] resultado = mr.getMonopatinesEnOperacion().get(0);

        Map<String, Long> estadoMonopatines = new HashMap<>();
        estadoMonopatines.put("EnOperacion", ((Number) resultado[0]).longValue());
        estadoMonopatines.put("EnMantenimiento", ((Number) resultado[1]).longValue());

        return estadoMonopatines;
    }

    public List<MonopatinDTO> getMonopatinesCercanos(int posX, int posY, int radio) {
        return mr.getMonopatinesCercanos(posX, posY, radio);
    }
}
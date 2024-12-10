package micromonopatin.service;

import jakarta.transaction.Transactional;
import micromonopatin.dto.MonopatinDTO;
import micromonopatin.dto.ParadaDTO;
import micromonopatin.entity.Parada;
import micromonopatin.repository.paradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class paradaService{
    @Autowired
    private paradaRepository pr;

    public ParadaDTO getParada(int id_parada) {
        return pr.getParada(id_parada);
    }

    public List<ParadaDTO> getParadas() {
        return pr.getParadas();
    }

    public Parada addParada(Parada parada){
        return pr.save(parada);
    }

    public void updateParada(Parada parada) {
        pr.updateParada(parada.getUbicacionX(), parada.getUbicacionY(), parada.getPermitida(),parada.getId_parada());
    }

    public void deleteParada(int id_parada) {
        pr.deleteParada(id_parada);
    }

    public List<MonopatinDTO> getMonopatinesParada(int posX, int posY) { return pr.getMonopatinesParada(posX, posY); }
}
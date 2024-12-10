package microviaje.service;

import microviaje.dto.PausaDTO;
import microviaje.entity.Pausa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microviaje.repository.mongodb.PausaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PausaService {

    @Autowired
    private PausaRepository PausaRepository;

    public List<PausaDTO> getPausas(){
        return PausaRepository.findAll().stream()
                .map(p -> new PausaDTO(p.getIdPausa(), p.getIdViaje(), p.getTiempo()))
                .collect(Collectors.toList());

    }

    public Pausa addPausa(Pausa pausa) {
        return PausaRepository.save(pausa);
    }
}

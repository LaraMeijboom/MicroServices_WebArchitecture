package micromantenimiento.service;
import jakarta.transaction.Transactional;
import micromantenimiento.dto.MantenimientoDTO;
import micromantenimiento.entity.Mantenimiento;
import micromantenimiento.feignClients.ClientMonopatin;
import micromantenimiento.models.Monopatin;
import micromantenimiento.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private ClientMonopatin clientMonopatin;

    public void finalizarMantenimiento(int idMantenimiento, Date fechaFin){
        Mantenimiento m = mantenimientoRepository.getById(idMantenimiento);
        clientMonopatin.updateMonopatinHabilitado(m.getIdMonopatin(), true);
        mantenimientoRepository.finalizarMantenimiento(fechaFin, idMantenimiento);
    }


    public Mantenimiento addMantenimiento(Mantenimiento m){
        Monopatin monopatin = clientMonopatin.getMonopatin(m.getIdMonopatin());
        if(monopatin != null){
            if(m.getFechaFin()==null){
                clientMonopatin.updateMonopatinHabilitado(m.getIdMonopatin(), false);
            }
            return mantenimientoRepository.save(m);
        }
        throw new RuntimeException("Monopatin invalido");
    }

    public void deleteMantenimiento(int idMantenimiento){
        mantenimientoRepository.deleteById(idMantenimiento);
    }

    public void updateMantenimiento(Mantenimiento m){
        mantenimientoRepository.updateMantenimiento(m.getIdMonopatin(),m.getFechaInicio(),m.getFechaFin(),m.getId());
    }

    public MantenimientoDTO getMantenimientoById(int idMantenimiento){
        return mantenimientoRepository.getMantenimientoById(idMantenimiento);
    }

    public List<MantenimientoDTO> getMantenimientos() {
        return mantenimientoRepository.getMantenimientos();
    }
}

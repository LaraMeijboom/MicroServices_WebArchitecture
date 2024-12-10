package micromantenimiento.service;

import jakarta.transaction.Transactional;
import micromantenimiento.dto.ReporteDTO;
import micromantenimiento.feignClients.ClientMonopatin;
import micromantenimiento.feignClients.ClientViaje;
import micromantenimiento.models.Monopatin;
import micromantenimiento.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ClientMonopatin clientMonopatin;

    @Autowired
    private ClientViaje clientViaje;

    public List<ReporteDTO> getReporteCompleto(){
        List<Monopatin> monopatinesPorKilometro = clientMonopatin.getReporteMonopatinesPorKilometro();
        List<ReporteDTO> reportes = new ArrayList<>();
        for(Monopatin m : monopatinesPorKilometro){
            ReporteDTO r = new ReporteDTO();
            r.setIdMonopatin(m.getId_monopatin());
            r.setTiempoSinPausa(m.getTiempo_uso_total());
            r.setKmRecorridos(m.getKilometrosTotales());
            Long tiempoPausa = clientViaje.getTiempoPausaMonopatin(m.getId_monopatin());
            if(tiempoPausa == null){
                r.setTiempoPausa(m.getTiempo_uso_total());
            }else{
                r.setTiempoPausa(m.getTiempo_uso_total()+clientViaje.getTiempoPausaMonopatin(m.getId_monopatin()));
            }
            reportes.add(r);
        }
        return reportes;
    }


    public List<ReporteDTO> getReporteMonopatinesPorKilometro() {
        List<Monopatin> monopatines = clientMonopatin.getReporteMonopatinesPorKilometro();
        List<ReporteDTO> reporteDTOs = new ArrayList<ReporteDTO>();
        for (Monopatin monopatin : monopatines) {
            ReporteDTO reporteDTO = new ReporteDTO();
            reporteDTO.setIdMonopatin(monopatin.getId_monopatin());
            reporteDTO.setKmRecorridos(monopatin.getKilometrosTotales());
            reporteDTOs.add(reporteDTO);
        }
        return reporteDTOs;
    }

    public List<ReporteDTO> getReporteMonopatinesPorTiempoPausa(){
        List<Monopatin> monopatines = clientMonopatin.getMonopatines();
        List<ReporteDTO> reportes = new ArrayList<ReporteDTO>();
        for(Monopatin monopatin : monopatines){
            ReporteDTO reporte = new ReporteDTO();
            reporte.setIdMonopatin(monopatin.getId_monopatin());
            reporte.setTiempoPausa(monopatin.getTiempo_uso_total()+clientViaje.getTiempoPausaMonopatin(monopatin.getId_monopatin()));
            reportes.add(reporte);
        }
        reportes.sort(Comparator.comparing(ReporteDTO::getTiempoPausa));
        return reportes;
    }

    public List<ReporteDTO> getReporteMonopatinesPorTiempoSinPausa(){
        List<Monopatin> monopatines = clientMonopatin.getMonopatines();
        List<ReporteDTO> reporteDTOs = new ArrayList<>();
        for (Monopatin monopatin : monopatines) {
            ReporteDTO reporteDTO = new ReporteDTO();
            reporteDTO.setIdMonopatin(monopatin.getId_monopatin());
            reporteDTO.setTiempoSinPausa(monopatin.getTiempo_uso_total());
            reporteDTOs.add(reporteDTO);
        }
        reporteDTOs.sort(Comparator.comparing(ReporteDTO::getTiempoSinPausa));
        return reporteDTOs;
    }
}

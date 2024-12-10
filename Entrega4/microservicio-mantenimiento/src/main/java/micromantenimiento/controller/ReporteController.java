package micromantenimiento.controller;


import micromantenimiento.dto.ReporteDTO;
import micromantenimiento.models.Monopatin;
import micromantenimiento.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/monopatines/kilometros/tiempos/{quieroTiempos}")
    public ResponseEntity<List<ReporteDTO>> getReporteMonopatinesPorKilometro(@PathVariable boolean quieroTiempos) {
        try {
            if(quieroTiempos){
                return ResponseEntity.ok(reporteService.getReporteCompleto());
            }else{
                return ResponseEntity.ok(reporteService.getReporteMonopatinesPorKilometro());
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/monopatines/tiempoPausa")
    public ResponseEntity<List<ReporteDTO>> getReporteMonopatinesPorTiempoPausa() {
        try {
            return new ResponseEntity<>(reporteService.getReporteMonopatinesPorTiempoPausa(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/monopatines/tiempoSinPausa")
    public ResponseEntity<List<ReporteDTO>> getReporteMonopatinesPorTiempoSinPausa() {
        try {
            if (reporteService.getReporteMonopatinesPorTiempoSinPausa() != null) {
                List<ReporteDTO> rta = reporteService.getReporteMonopatinesPorTiempoSinPausa();
                return new ResponseEntity<>(rta, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

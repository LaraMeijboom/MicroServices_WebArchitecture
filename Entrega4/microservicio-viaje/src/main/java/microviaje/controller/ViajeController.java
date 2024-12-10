package microviaje.controller;
import microviaje.dto.ViajeDTO;
import microviaje.entity.Viaje;
import org.entrega4.microservicioviaje.junit.ViajeVacioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import microviaje.service.ViajeService;

import java.util.List;

@RestController
@RequestMapping("api/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    // Crear o actualizar un viaje
    @PostMapping
    public ResponseEntity<Viaje> saveViaje(@RequestBody Viaje viaje) {
        Viaje savedViaje = viajeService.saveViaje(viaje);
        return ResponseEntity.ok(savedViaje);
    }

    @PutMapping("/id/{idViaje}")
    public ResponseEntity<String> updateViaje(@PathVariable long idViaje, @RequestBody Viaje viaje) {
        try {
            if (viajeService.getViajeById(idViaje) != null){
                viajeService.updateViaje(viaje, idViaje);
                return ResponseEntity.ok("Viaje actualizado");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el viaje"+ e.getMessage());
        }
    }

    // Obtener todos los viajes
    @GetMapping
    public ResponseEntity<List<ViajeDTO>> getAllViajes() throws ViajeVacioException {
        List<ViajeDTO> viajes = viajeService.getAllViajes();
        List<ViajeDTO> viajesTest = viajeService.getAllViajesTest();

        if (viajesTest.isEmpty()) {
            throw new ViajeVacioException("No hay viajes disponibles");
        }
        return ResponseEntity.ok(viajes);
    }



    // Obtener un viaje por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ViajeDTO> getViajeById(@PathVariable Long id) {
        ViajeDTO viajeDTO = viajeService.getViajeById(id);
        if (viajeDTO != null) {
            return ResponseEntity.ok(viajeDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/idMonopatin/{id}/tiempoSinPausa")
    public ResponseEntity<Long> getTiempoPausaMonopatin(@PathVariable int id) {
        try{
            Long res = viajeService.getTiempoPausaMonopatin(id);
            if (res == null) {
                res = 0L;
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Eliminar un viaje por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaje(@PathVariable Long id) {
        if (viajeService.getViajeById(id)!=null) {
            viajeService.deleteViaje(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/monopatinesMasViajesPorAnio/cant/{cant}/anio/{anio}")
    public ResponseEntity<List<ViajeDTO>> getMonopatinesMasViajesPorAnio(@PathVariable int cant, @PathVariable int anio) {
        return ResponseEntity.ok(viajeService.getMonopatinesMasViajesPorAnio(cant, anio));
    }

    @GetMapping("/totalFacturadoEntre/anio/{anio}/mes1/{mes1}/mes2/{mes2}")
    public ResponseEntity<Integer> getFacturadoEntre(@PathVariable int anio, @PathVariable int mes1, @PathVariable int mes2) {
        return  ResponseEntity.ok(viajeService.getFacturadoEntre(anio,mes1,mes2));
    }
}

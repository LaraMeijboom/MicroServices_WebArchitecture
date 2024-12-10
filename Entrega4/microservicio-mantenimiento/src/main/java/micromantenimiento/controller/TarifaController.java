package micromantenimiento.controller;

import micromantenimiento.dto.TarifaDTO;
import micromantenimiento.entity.Tarifa;
import micromantenimiento.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tarifas")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    @PostMapping
    public ResponseEntity<Tarifa> addTarifa(@RequestBody Tarifa tarifa) {
        try {
            tarifaService.addTarifa(tarifa);
            return new ResponseEntity<>(tarifa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Tarifa> updateTarifa(@PathVariable int id, @RequestBody Tarifa tarifa) {
        try {
            if (tarifaService.getTarifaById(id) != null) {
                tarifaService.updateTarifa(tarifa);
                return new ResponseEntity<>(tarifa, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/setPrecioTarifa/id/{idTarifa}")
    public ResponseEntity<String> setPrecioTarifa(@PathVariable int idTarifa, @RequestBody int precio) {
        try {
            if (tarifaService.getTarifaById(idTarifa) != null) {
                tarifaService.setPrecioTarifa(precio);
                return ResponseEntity.ok("Precio Tarifa Extra actualizada con éxito");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarifa no encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el precio Tarifa.");
        }
    }

    @PutMapping("/setPrecioTarifaExtra/id/{idTarifa}")
    public ResponseEntity<String> setPrecioTarifaExtra(@PathVariable int idTarifa, @RequestBody int precio) {
        try {
            if (tarifaService.getTarifaById(idTarifa) != null) {
                tarifaService.setPrecioTarifaExtra(precio);
                return ResponseEntity.ok("Precio Tarifa actualizado con éxito");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarifa no encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el precio Tarifa.");
        }
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteTarifa(@PathVariable int id) {
        try {
            if (tarifaService.getTarifaById(id) != null) {
                tarifaService.deleteTarifa(id);
                return ResponseEntity.ok("Tarifa eliminado con éxito");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarifa no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la Tarifa.");
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TarifaDTO> getTarifaById(@PathVariable int id) {
        try {
            if (tarifaService.getTarifaById(id) != null) {
                TarifaDTO a = tarifaService.getTarifaById(id);
                return new ResponseEntity<>(a, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<TarifaDTO>> getTarifas() {
        List<TarifaDTO> Tarifas = tarifaService.getTarifas();
        return ResponseEntity.ok(Tarifas);
    }

    @GetMapping("/actual")
    public ResponseEntity<TarifaDTO> getTarifaActual() {
        TarifaDTO tarifaActual = tarifaService.getTarifaActual();
        return ResponseEntity.ok(tarifaActual);
    }
}

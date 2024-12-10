package micromantenimiento.controller;

import micromantenimiento.dto.MantenimientoDTO;
import micromantenimiento.entity.Mantenimiento;
import micromantenimiento.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/mantenimientos")
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;


    @PutMapping("/id/{id}/finalizar")
    public ResponseEntity<String> finalizarMantenimiento(@PathVariable int id,@RequestBody Date fechaFin){
        try{
        if(mantenimientoService.getMantenimientoById(id) != null) {
            mantenimientoService.finalizarMantenimiento(id, fechaFin);
            return ResponseEntity.ok("Mantenimiento finalizado con éxito");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El mantenimiento no fue encontrado");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al finalizar el mantenimiento");
        }
    }

    @PostMapping
    public ResponseEntity<String> addMantenimiento(@RequestBody Mantenimiento mantenimiento) {
        try{
                mantenimientoService.addMantenimiento(mantenimiento);
                return new ResponseEntity<>("Exito", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Mantenimiento> updateMantenimiento(@PathVariable int id,@RequestBody Mantenimiento mantenimiento) {
        try {
            if (mantenimientoService.getMantenimientoById(id) != null) {
                mantenimientoService.updateMantenimiento(mantenimiento);
                return new ResponseEntity<>(mantenimiento, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/id/{idMantenimiento}")
    public ResponseEntity<String> deleteMantenimiento(@PathVariable int idMantenimiento){
        try {
            if (mantenimientoService.getMantenimientoById(idMantenimiento) != null) {
                mantenimientoService.deleteMantenimiento(idMantenimiento);
                return ResponseEntity.ok("Mantenimiento eliminado con éxito");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mantenimiento no encontrado.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el mantenimiento." + e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MantenimientoDTO> getMantenimientoById(@PathVariable int id){
        try {
            if (mantenimientoService.getMantenimientoById(id) != null) {
                MantenimientoDTO c = mantenimientoService.getMantenimientoById(id);
                return new ResponseEntity<>(c, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<MantenimientoDTO>> getMantenimientos(){
        List<MantenimientoDTO> mantenimientos = mantenimientoService.getMantenimientos();
        return ResponseEntity.ok(mantenimientos);
    }

}
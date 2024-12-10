package microviaje.controller;

import microviaje.dto.PausaDTO;
import microviaje.entity.Pausa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import microviaje.service.PausaService;

import java.util.List;

@RestController
@RequestMapping("/api/pausas")
public class PausaController {

    @Autowired
    private PausaService pausaService;

    @GetMapping()
    public ResponseEntity<List<PausaDTO>> getPausas() {
        return ResponseEntity.ok(pausaService.getPausas());
    }

    @PostMapping()
    public ResponseEntity<Pausa> addPausa(@RequestBody Pausa pausa) {
        Pausa p = pausaService.addPausa(pausa);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }
}
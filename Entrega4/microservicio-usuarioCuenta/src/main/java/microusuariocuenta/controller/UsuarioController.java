package microusuariocuenta.controller;

import microusuariocuenta.services.UsuarioService;
import microusuariocuenta.dto.UsuarioDTO;
import microusuariocuenta.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.addUsuario(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable int id){
        try {
            if (usuarioService.getUsuarioById(id) != null) {
                usuarioService.deleteUsuario(id);
                return ResponseEntity.ok("Usuario eliminado con éxito");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario.");
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        try{
            if(usuarioService.getUsuarioById(id) != null){
                usuarioService.updateUsuario(usuario);
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable int id) {
        try {
            if (usuarioService.getUsuarioById(id) != null) {
                return new ResponseEntity<>(usuarioService.getUsuarioById(id), HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
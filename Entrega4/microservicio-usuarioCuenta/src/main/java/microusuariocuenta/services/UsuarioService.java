package microusuariocuenta.services;

import jakarta.transaction.Transactional;
import microusuariocuenta.dto.UsuarioDTO;
import microusuariocuenta.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microusuariocuenta.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(int idUsuario) {
        usuarioRepository.deleteUsuario(idUsuario);
    }

    public void updateUsuario(Usuario u) {
        usuarioRepository.updateUsuario(u.getNombre(), u.getNombreUsuario(), u.getApellido(), u.getEmail(), u.getTelefono());
    }

    public UsuarioDTO getUsuarioById(int id){
        return usuarioRepository.getUsuarioById(id);
    }
}

package microusuariocuenta.dto;

public class UsuarioDTO {
    private int id;
    private String nombre;
    private String nombreUsuario;
    private String apellido;
    private String email;
    private String telefono;

    public UsuarioDTO(int id, String nombre, String nombreUsuario, String apellido, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
}

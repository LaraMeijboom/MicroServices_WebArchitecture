package microusuariocuenta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    @Column(unique = true)
    private String nombreUsuario;
    private String apellido;
    @Column(unique = true)
    private String email;
    private String telefono;
    @ManyToMany
    private List<Cuenta> cuentas;

}// el mapedby va en una sola entidad gracuas
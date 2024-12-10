package microusuariocuenta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CuentaDTO {
    private int id;
    private double saldo;
    private Date fechaAlta;
    private int idMercadoPago;
    private boolean anulada;
}

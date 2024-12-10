package microusuariocuenta.services;

import jakarta.transaction.Transactional;
import microusuariocuenta.dto.CuentaDTO;
import microusuariocuenta.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import microusuariocuenta.repositories.CuentaRepository;

import java.util.List;

@Service
@Transactional
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta addCuenta(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(int idCuenta){
        cuentaRepository.deleteCuenta(idCuenta);
    }

    public void updateCuenta(Cuenta c){
        cuentaRepository.updateCuenta(c.getSaldo(), c.getFechaAlta(), c.getIdMercadoPago());
    }

    public CuentaDTO getCuentaById(int idCuenta){
        return cuentaRepository.getCuentaById(idCuenta);
    }

    public List<CuentaDTO> getCuentas() {
        return cuentaRepository.getCuentas();
    }

    public void anularCuenta(int idCuenta){
        cuentaRepository.anularCuenta(idCuenta);
    }

    public void desanularCuenta(int idCuenta){
        cuentaRepository.desanularCuenta(idCuenta);
    }
}
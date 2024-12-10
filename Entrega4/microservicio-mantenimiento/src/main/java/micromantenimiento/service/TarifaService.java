package micromantenimiento.service;

import jakarta.transaction.Transactional;
import micromantenimiento.dto.TarifaDTO;
import micromantenimiento.entity.Tarifa;
import micromantenimiento.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public void setPrecioTarifa(double precioTarifa) {
        tarifaRepository.updatePrecioTarifa(precioTarifa);
    }

    public void setPrecioTarifaExtra(double precioTarifaExtra) {
        tarifaRepository.updatePrecioTarifaExtra(precioTarifaExtra);
    }

    // Método para agregar un nuevo tarifa
    public Tarifa addTarifa(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    // Método para actualizar un tarifa
    public Tarifa updateTarifa(Tarifa tarifa) {
        if (tarifaRepository.existsById(tarifa.getId())) {
            return tarifaRepository.save(tarifa);
        } else {
            return null; // Puede lanzar una excepción si prefieres manejarlo de otra manera
        }
    }

    // Método para eliminar un tarifa
    public void deleteTarifa(int id) {
        if (tarifaRepository.existsById(id)) {
            tarifaRepository.deleteById(id);
        }
    }

    public TarifaDTO getTarifaById(int id) {
        return tarifaRepository.getTarifaById(id);
    }

    public List<TarifaDTO> getTarifas() {
        return tarifaRepository.getTarifas();
    }

    public TarifaDTO getTarifaActual() {
        LocalDate fechaActual = LocalDate.now();
        return tarifaRepository.getTarifaActual(fechaActual);
    }
}

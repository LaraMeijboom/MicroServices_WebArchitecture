package micromantenimiento.feignClients;

import micromantenimiento.models.Monopatin;
import micromantenimiento.models.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservicio-viaje")
public interface ClientViaje{

    @GetMapping("api/viaje")
    List<Viaje> getViajes();

    @GetMapping("api/viajes/idMonopatin/{id_monopatin}/tiempoSinPausa")
    Long getTiempoPausaMonopatin(@PathVariable("id_monopatin") int id_monopatin);
}

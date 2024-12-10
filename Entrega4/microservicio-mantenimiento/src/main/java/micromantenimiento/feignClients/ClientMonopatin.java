package micromantenimiento.feignClients;

import micromantenimiento.models.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "microservicio-monopatin")
public interface ClientMonopatin {

    @GetMapping("api/monopatines")
    List<Monopatin> getMonopatines();

    @Modifying
    @PutMapping("api/monopatines/id/{id_monopatin}/habilitado/{valor}")
    void updateMonopatinHabilitado(@PathVariable int id_monopatin, @PathVariable boolean valor);

    @GetMapping("api/monopatines/id/{id_monopatin}")
    Monopatin getMonopatin(@PathVariable("id_monopatin") int id_monopatin);

    @GetMapping("api/monopatines/reporte/kilometros")
    List<Monopatin> getReporteMonopatinesPorKilometro();

}

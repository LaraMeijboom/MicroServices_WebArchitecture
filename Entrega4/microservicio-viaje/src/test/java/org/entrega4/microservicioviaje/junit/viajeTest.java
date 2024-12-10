package org.entrega4.microservicioviaje.junit;

import microviaje.controller.ViajeController;
import microviaje.dto.ViajeDTO;
import microviaje.entity.Viaje;
import microviaje.service.ViajeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class viajeTest {

    @Test
    void viajeVacioTest() {
        ViajeService viajeService = new ViajeService();

        ViajeController viajeController = new ViajeController();

        assertThrows(ViajeVacioException.class, viajeService::getAllViajesTest);
    }

}
